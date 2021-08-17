package com.yule.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.CaptchaSessionConst;
import com.yule.constant.CommentConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.DoMainConst;
import com.yule.constant.FileUploadConst;
import com.yule.constant.JSONConst;
import com.yule.constant.OrdersConst;
import com.yule.constant.ScoreConst;
import com.yule.constant.TimeConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.mail.user.UserInsertOrdersMail;
import com.yule.mongo.orders.service.IOrdersMongo;
import com.yule.mongo.orders.service.IUserScoreMongo;
import com.yule.mongo.pojo.Orders;
import com.yule.mongo.pojo.UserScore;
import com.yule.orders.service.IProductService;
import com.yule.orders.service.IUserLoginService;
import com.yule.orders.vo.UserAreaVO;
import com.yule.param.OrdersParam;
import com.yule.pojo.AreaProvince;
import com.yule.redis.constant.RedisConst;
import com.yule.redis.util.JedisUtil;
import com.yule.util.DateUtil;
import com.yule.util.EncryptUtil;
import com.yule.util.IPAddressUtil;
import com.yule.util.OrdersDateUtil;
import com.yule.util.SendMailRunnableUtil;
import com.yule.vo.IPAddressVO;
import com.yule.vo.OrdersProductVO;
import com.yule.vo.UserLoginVO;
import com.yule.weixin.constant.FontColorConst;
import com.yule.weixin.templateMessage.NewOrdersTemplateMessage;

@Controller
@RequestMapping("/orders")
public class OrdersAction extends  BaseAction{

	@Autowired
	private IOrdersMongo ordersMongoImpl;
	
	@Autowired
	private IProductService productServiceImpl;
	
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;

	@Autowired
	private IUserLoginService userLoginServiceImpl;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findOrders",method = RequestMethod.GET)
	public String findOrders(String detail_to_order) throws Exception {
		try {
			String key = RedisConst.DETAIL_TO_ORDER + detail_to_order;
			UserLoginVO userLoginVO =getUserLoginVO();
			if (null==userLoginVO) {
				return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
			}
			OrdersProductVO ordersProductVO = null;
			if(JedisUtil.getInstance().exists(key)){
				ordersProductVO= (OrdersProductVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),OrdersProductVO.class);
				ordersProductVO.setCompany_face(FileUploadConst.COMPANY_IMAGE_PATH+ordersProductVO.getCompany_id()+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
				ordersProductVO.setProduct_face(FileUploadConst.COMPANY_IMAGE_PATH+ordersProductVO.getCompany_id()+FileUploadConst.PRODUCT+ordersProductVO.getProduct_id()+"/"+FileUploadConst.PX_150_150+FileUploadConst.PRODUCT_FACE+FileUploadConst.IMAGE_TYPE);
				int user_score= this.userScoreMongoImpl.findUserScore(userLoginVO.getId());
				UserAreaVO userAreaVO = this.userLoginServiceImpl.findUserLoginVOById(userLoginVO.getId());
				Object attribute = session.getAttribute("orders_message");
				if(attribute != null){
					request.setAttribute("orders_message", attribute.toString());
					session.removeAttribute("orders_message");
				}
				String userOrderKey = RedisConst.USER_ORDERS+userLoginVO.getId();
				if(JedisUtil.getInstance().exists(userOrderKey)){
					String orderNum = JedisUtil.getInstance().get(userOrderKey);
					int orderNumInt = Integer.parseInt(orderNum);
					if (orderNumInt>=3) {
						request.setAttribute("captchaHtml", "取消订单太频繁了 ，请输入验证码</br><input datatype=\"captcha\" nullmsg=\"请输入验证码\" type=\"text\" id=\"captcha\" name=\"captcha\"    /><img alt=\"验证码\" title=\"验证码\"   style=\"cursor: pointer;\"  data-src=\"/captcha.jpg?sessionid=<%=request.getSession().getId()%>\" src=\"/captcha.jpg?sessionid=<%=request.getSession().getId()%>\" />");
					}
				}
				request.setAttribute("userLoginVO", userLoginVO);
				request.setAttribute("userAreaVO", userAreaVO);
				request.setAttribute("detail_to_order", detail_to_order);
				request.setAttribute("user_score", user_score);
				request.setAttribute("ordersProductVO", ordersProductVO);
				return "/pc/index";
			}
		} catch (Exception e) {
			new YuleException("查看订单错误",e);
			throw e;
		}finally{
			System.gc();
		}
		return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
	}
	/**
	 * 添加订单
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertOrders",method = RequestMethod.POST)
	public String insertOrders(@ModelAttribute("ordersParam")OrdersParam ordersParam,@RequestParam(value="t",required=false)String t, @RequestParam(value="captcha",required=false) String captcha) throws Exception {
		try {
			String key = RedisConst.DETAIL_TO_ORDER + ordersParam.getDetail_to_order();
			UserLoginVO userLoginVO = getUserLoginVO();
			String userId = userLoginVO.getId();
			String company_id = null;
//			UserLoginVO userLoginVO = this.userLoginServiceImpl.findUserLoginVOById(user_id);
//			if (null==userLoginVO||StringUtils.isEmpty(t) || !session.getId().equals(t)) {
//				return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
//			}
			//判断
			if(JedisUtil.getInstance().exists(key)){
				String userOrderKey = RedisConst.USER_ORDERS+userId;
				if(JedisUtil.getInstance().exists(userOrderKey)){
					String orderNum = JedisUtil.getInstance().get(userOrderKey);
					int orderNumInt = Integer.parseInt(orderNum);
					if (orderNumInt>=3) {
						Object code = session.getAttribute(CaptchaSessionConst.ORDERS_CAPTCHA_KEY);
						if (StringUtils.isEmpty(captcha)||!code.toString().equals(EncryptUtil.encryptToMD5(captcha.toLowerCase()))) {
							session.setAttribute("orders_message", "验证码不正确");
							return ActionReturnConst.REDIRECT+"/orders/findOrders.do?detail_to_order="+ordersParam.getDetail_to_order();
						}
					}
				}
				Orders orders =new Orders();
				OrdersProductVO ordersProductVO= (OrdersProductVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),OrdersProductVO.class);
				//判断有房无房				
				company_id = ordersProductVO.getCompany_id();
				int status = this.productServiceImpl.findProductstatusById(ordersProductVO.getProduct_id());
				if (status==0) {
					//企业信息
					orders.setCompany_id(ordersProductVO.getCompany_id());
					orders.setCompany_name(ordersProductVO.getCompany_name());
					orders.setCompany_area_city_id(ordersProductVO.getCompany_area_city_id());
					orders.setCompany_area_city_name(ordersProductVO.getCompany_area_city_name());
					if(null!=ordersProductVO.getCompany_area_city_id()&&ordersProductVO.getCompany_area_city_id().length()==6){
						String area_province_id = ordersProductVO.getCompany_area_city_id().substring(0, 3)+"000";
						orders.setCompany_area_province_id(area_province_id);
						List<AreaProvince> areaProvinces = new ArrayList<AreaProvince>();
						if(JedisUtil.getInstance().exists(RedisConst.AREA_PROVINCE)){
							areaProvinces.addAll(JSONArray.toList(JSONArray.fromObject(JedisUtil.getInstance().get(RedisConst.AREA_PROVINCE)),new AreaProvince(),JSONConst.JSON_CONFIG));
						}
						String province_name  = null; 
						if (areaProvinces.size()>0) {
							for (AreaProvince areaProvince : areaProvinces) {
								if (area_province_id.equals(areaProvince.getId())) {
									province_name = areaProvince.getName();
									break;
								}
							}
						areaProvinces.clear();
						orders.setCompany_area_province_name(province_name);
						}
						areaProvinces = null;
					}
					//产品信息
					orders.setProduct_id(ordersProductVO.getProduct_id());
					orders.setProduct_name(ordersProductVO.getProduct_name());
					//用户信息
					orders.setCustomer_mail(ordersParam.getCustomer_mail());
					orders.setUser_id(userId);
					orders.setCustomer_name(ordersParam.getCustomer_name());
					orders.setCustomer_phone(ordersParam.getCustomer_phone());
					orders.setDesc(ordersParam.getDesc());
					if (StringUtils.isEmpty(orders.getUser_id())||StringUtils.isEmpty(orders.getCustomer_name())||StringUtils.isEmpty(orders.getCustomer_phone())) {
						session.setAttribute("orders_message", "用户信息不完整");
						return ActionReturnConst.REDIRECT+"/orders/findOrders.do?detail_to_order="+ordersParam.getDetail_to_order();
					}
					if (StringUtils.isEmpty(orders.getCompany_id())||StringUtils.isEmpty(orders.getCompany_name())||StringUtils.isEmpty(orders.getProduct_id())||StringUtils.isEmpty(orders.getProduct_name())) {
						session.setAttribute("orders_message", "娱乐场所信息不完整");
						return ActionReturnConst.REDIRECT+"/orders/findOrders.do?detail_to_order="+ordersParam.getDetail_to_order();
					}
					String estimateArriveTime  = ordersParam.getEstimate_arrive_time()+" "+ordersParam.getLast_arrive_time();
					//判断相差时间
					Long time = OrdersDateUtil.compareTime(estimateArriveTime);
					if (time==null||time<0||time>TimeConst.THREE_DAY) {
						session.setAttribute("orders_message", "预计到达时间有误时间范围异常");
						return ActionReturnConst.REDIRECT+"/orders/findOrders.do?detail_to_order="+ordersParam.getDetail_to_order();
					}
					orders.setEstimate_arrive_time(DateUtil.StringToDate(estimateArriveTime));
					orders.setUser_area_city_name(ordersParam.getUser_area_city_name());
					orders.setUser_area_city_id(ordersParam.getUser_area_city_id());
					orders.setLast_arrive_time(ordersParam.getLast_arrive_time());
					if (StringUtils.isEmpty(orders.getUser_area_city_id())||StringUtils.isEmpty(orders.getUser_area_city_name())) {
						//用户城市信息不存在则根据ip获取城市信息
						String ip = IPAddressUtil.getRemortIP(request);
						if(ip!=null){
							IPAddressVO addressVO= IPAddressUtil.getAddresses(ip);
							orders.setUser_area_city_name(addressVO.getCity());
							orders.setUser_area_city_id(addressVO.getCity_id());
						}else{
							orders.setUser_area_city_name("西安");
							orders.setUser_area_city_id("610100");
						}
/*						orders.setUser_area_city_id("610100");
						orders.setUser_area_city_name("西安");*/
					}
					if (ordersParam.getUser_score()!=null) {
						//用户积分 大于100 且小于 自己积分 
						int score= this.userScoreMongoImpl.findUserScore(userId);
						if(ordersParam.getUser_score()>=100&&ordersParam.getUser_score()<=score){
							orders.setUser_score(ordersParam.getUser_score());
							UserScore userScore = new UserScore();
							userScore.setScore(ordersParam.getUser_score());
							userScore.setStatus(ScoreConst.STATUS_OUT);
							userScore.setType(ScoreConst.TYPE_TWO);
							userScore.setUser_id(userId);
							this.userScoreMongoImpl.insertUserScore(userScore);
						}else{
							session.setAttribute("orders_message", "积分错误,请输入正确的积分(100起步,并且不小于自己积分)");
							return ActionReturnConst.REDIRECT+"/orders/findOrders.do?detail_to_order="+ordersParam.getDetail_to_order();
						}
					}
					orders.setStatus(OrdersConst.ORDERS_STATUS_ZERO);
					orders.setIs_delete(DeleteConst.IS_DELETE_TRUE);
					orders.setIs_comment(CommentConst.IS_COMMENT_FALSE);
					orders.setCustomer_mail(ordersParam.getCustomer_mail());
					orders.setUser_openid(userLoginVO.getOpenid());
//					orders.setCompany_openid(company_openid);
					ordersMongoImpl.insertOrders(orders);
					if (!StringUtils.isEmpty(ordersParam.getCustomer_mail())) {
						UserInsertOrdersMail insertOrdertsMail =new UserInsertOrdersMail();
						insertOrdertsMail.setCompany_face(FileUploadConst.COMPANY_IMAGE_PATH+ordersProductVO.getCompany_id()+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
						insertOrdertsMail.setCompany_name(ordersProductVO.getCompany_name());
						insertOrdertsMail.setName(ordersParam.getCustomer_name());
						insertOrdertsMail.setNum(orders.getOrder_num());
						insertOrdertsMail.setCompany_id(ordersProductVO.getCompany_id());
						insertOrdertsMail.setCreate_time(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
						insertOrdertsMail.setProduct_name(ordersProductVO.getProduct_name());
						insertOrdertsMail.setEstimate_arrive_time(ordersParam.getEstimate_arrive_time());
						insertOrdertsMail.setDetal_address(ordersProductVO.getCompany_area_address());
						insertOrdertsMail.setCreate_time(DateUtil.DateToString(orders.getCreate_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
						SendMailRunnableUtil.sendMail(insertOrdertsMail, ordersParam.getCustomer_mail());
					}
					NewOrdersTemplateMessage newOrdersTemplateMessage = new NewOrdersTemplateMessage();
					newOrdersTemplateMessage.setFirst("新订单通知,点击查看详情,请您及时安排!");
					newOrdersTemplateMessage.setProductType(orders.getCompany_name());
					newOrdersTemplateMessage.setName(orders.getProduct_name());
					newOrdersTemplateMessage.setNumber("1");
					newOrdersTemplateMessage.setRemark("使用积分"+orders.getUser_score());
					newOrdersTemplateMessage.setTouser("oW-Ras_-uuHKqARxO8R2wNVweDPI");//企业的openId
					newOrdersTemplateMessage.setUrl("http://www.yuleing.com");
					newOrdersTemplateMessage.setTopcolor(FontColorConst.COLOR_5b1750);
					String okey = RedisConst.COMPANY_ORDERS_NUM + ordersProductVO.getCompany_id();
					if (JedisUtil.getInstance().exists(okey)) {
						JedisUtil.getInstance().set(okey, String.valueOf(Integer.parseInt(JedisUtil.getInstance().get(okey))+1));
					}
					
					JedisUtil.getInstance().del(key);
					request.setAttribute("company_face", FileUploadConst.COMPANY_IMAGE_PATH+ordersProductVO.getCompany_id()+"/"+FileUploadConst.PX_200_150+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
					request.setAttribute("product_face", FileUploadConst.COMPANY_IMAGE_PATH+ordersProductVO.getCompany_id()+FileUploadConst.PRODUCT+ordersProductVO.getProduct_id()+"/"+FileUploadConst.PX_150_150+FileUploadConst.PRODUCT_FACE+FileUploadConst.IMAGE_TYPE);
					request.setAttribute("orders", orders);
					request.setAttribute("ordersProductVO", ordersProductVO);
					request.setAttribute("estimate_arrive_time",DateUtil.DateToString(orders.getEstimate_arrive_time(), DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
					ordersParam = null;
					ordersProductVO = null;
					orders = null;
				}else {
					//无房
					return ActionReturnConst.REDIRECT+DoMainConst.DETAIL_URL+"/company/findCompanyDetails.do?id="+company_id;
				}
			}else {
				return ActionReturnConst.REDIRECT+DoMainConst.PC_URL;
			}

		} catch (Exception e) {
			new YuleException("新增订单错误",e);
			throw e;
		}finally{
			System.gc();
		}
		return "/pc/order-ok";
	}
}
