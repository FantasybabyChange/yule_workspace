package com.yule.action;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.ErrorConst;
import com.yule.detail.service.ICompanyService;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.IUserFavoriteMongo;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.vo.UserLoginVO;
@Controller
@Scope("prototype")
@RequestMapping("/userFavorite")
public class UserFavoriteAction extends BaseAction{
	
	@Autowired
	private IUserFavoriteMongo userFavoriteMongoImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@RequestMapping(value="/findUserFavorite",method=RequestMethod.POST)
	public String findUserFavorite(@RequestParam(value="companyId",required=false)String companyId)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			UserLoginVO userLoginVO = getUserLoginVO();
			 StringBuffer userFavoriteHTMLs = new StringBuffer();
			 if (userLoginVO == null) {
				 userFavoriteHTMLs.append("<i class=\"icon\"></i>");
				 userFavoriteHTMLs.append("<span  data-type='user_unlogin'>保存至收藏</span>");					
				}else{
					int count = this.userFavoriteMongoImpl.findUserFavoriteByUserId(userLoginVO.getId(), companyId);
					 if (count > 0) {
						userFavoriteHTMLs.append("<i class=\"icon\"></i>");
						userFavoriteHTMLs.append("<span data-type='favorite_false'\">已收藏<small style=\"color:black;\">&nbsp;&nbsp;共收藏"+this.userFavoriteMongoImpl.findUserFavoriteByUserId(null, companyId)+"次</small></span>");
					 }else{
						 userFavoriteHTMLs.append("<i class=\"icon\"></i>");
						 userFavoriteHTMLs.append("<span  data-type='favorite_true' user data-id=\""+companyId+"\">保存至收藏</span>");
					 }
				}
			 object.put("userFavoriteHTMLs", userFavoriteHTMLs.toString());
			 userFavoriteHTMLs.setLength(0);
			 object.put("status", ErrorConst.STATUS_SUCCESS);
		} catch (Exception e) {
			new YuleException("查询用户收藏【findUserFavorite】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
	@RequestMapping(value="/insertUserFavorite",method=RequestMethod.POST)
	public String insertUserFavorite(@RequestParam(value="companyId",required=false)String companyId)throws Exception{
		JSONObject object = new JSONObject();
		object.put("status", ErrorConst.STATUS_ERROR);
		try {
			UserLoginVO userLoginVO = getUserLoginVO();
			if(userLoginVO != null){
				CompanyVO companyVO = this.companyServiceImpl.findCompanyVOById(companyId);
				UserFavorite  userFavorite = new UserFavorite();
				userFavorite.setAddress_datail(companyVO.getAddress_detail());
				userFavorite.setArea_city_name(companyVO.getCity_name());
				userFavorite.setArea_county_name(companyVO.getCountry_name());
				userFavorite.setCompany_id(companyVO.getId());
				userFavorite.setCompany_name(companyVO.getCompany_name());
				userFavorite.setUser_id(userLoginVO.getId());
				userFavorite.setUser_name(userLoginVO.getName());
				userFavorite.setCompany_category_id(companyVO.getCompany_category_id());
				userFavorite.setCompany_category_name(companyVO.getCompany_category_name());
				userFavorite.setCompany_grade_id(companyVO.getCompany_grade_id());
				userFavorite.setCompany_grade_name(companyVO.getCompany_grade_name());
				boolean flag = this.userFavoriteMongoImpl.insertUserFavorite(userFavorite);
				if (flag) {
					StringBuffer userFavoriteHTMLs = new StringBuffer();
					userFavoriteHTMLs.append("<i class=\"icon\"></i>");
					userFavoriteHTMLs.append("<span data-type='favorite_false'\">已收藏<small style=\"color:black;\">&nbsp;&nbsp;共收藏"+this.userFavoriteMongoImpl.findUserFavoriteByUserId(null, companyId)+"次</small></span>");
					object.put("userFavoriteHTMLs", userFavoriteHTMLs.toString());
					userFavoriteHTMLs.setLength(0);
					object.put("status", ErrorConst.STATUS_SUCCESS);
				}
			}
		} catch (Exception e) {
			new YuleException("新增用户收藏【insertUserFavorite】出现异常",e);
			throw e;
		}finally{
			outputResult(object.toString());
		}
		return null;
	}
}
