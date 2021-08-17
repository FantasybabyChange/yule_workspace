package com.yule.action.user;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.admin.param.InsertUserLevelParam;
import com.yule.admin.service.IUserLevelService;
import com.yule.common.BaseAction;
import com.yule.constant.ActionReturnConst;
import com.yule.constant.ErrorConst;
import com.yule.constant.PlaceholderConst;
import com.yule.constant.PrivilegeConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.UserLevel;
import com.yule.util.AdminLogUtil;

@Controller
@Scope("prototype")
@RequestMapping("/userLevel")
public class UserLevelAction extends BaseAction{
	
	@Autowired
	private IUserLevelService userLevelServiceImpl;
	
	/**
	 * 查看等级
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findUserLevel",method = RequestMethod.GET)
	public String findUserLevelList() throws Exception {
		try {
			List<UserLevel> userLevels=userLevelServiceImpl.findUserLevelList();
			AdminPrivilege adminPrivilege = null;
			StringBuffer operatorHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				operatorHtml.append("<a class=\"button\" href=\"javascript:;\" data-add-row=\"\" />新增一行</a>&nbsp;");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_BATCH_UPDATE);
				operatorHtml.append("<input class=\"button\" type=\"submit\" value=\""+adminPrivilege.getName()+"\">");
				adminPrivilege = null;
			}
			StringBuffer privilegeHtml = new StringBuffer("");
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_UPDATE);
				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-update=\"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
//			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE)){
//				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_DELETE);
//				privilegeHtml.append("<a  class=\"button\" href=\"javascript:;\" data-delete=\"\" data-id=\""+PlaceholderConst.ID+"\" data-url=\""+adminPrivilege.getUrl()+"\" >"+adminPrivilege.getName()+"</a>");
//				adminPrivilege = null;
//			}
			StringBuffer rowHtml = new StringBuffer("");
			rowHtml.append("<td><input id=\"id\" type=\"hidden\" name=\"id\" value=\""+PlaceholderConst.ID+"\"  />");
			rowHtml.append("<input id=\"name\" class=\"text-input\" type=\"text\"  name=\"name\"  nullmsg=\"请输入名称!\" datatype=\"\" errormsg=\"\"  value=\""+PlaceholderConst.NAME+"\"/></td>");
			rowHtml.append("<td><input id=\"expense\" class=\"text-input small-input\" type=\"text\"  name=\"expense\"  nullmsg=\"请输入消费金额!\"  errormsg=\"消费金额必须为数字!\" datatype=\"n\"  value=\""+PlaceholderConst.EXPENSE+"\"/></td>");
			rowHtml.append("<td><input id=\"score_ratio\" class=\"text-input small-input\" type=\"text\"  name=\"score_ratio\"  nullmsg=\"请输入积分增长比率!\"  errormsg=\"积分增长比率必须为数字!\" datatype=\"n\"  value=\""+PlaceholderConst.SCORE_RATIO+"\"/></td>");
			
			StringBuffer htmls = new StringBuffer("");
			
			if(null!=userLevels&&userLevels.size()>0){
				StringBuffer id = new StringBuffer();
				for(UserLevel userLevel:userLevels){
					id.append(userLevel.getId());
					htmls.append("<tr>");
					htmls.append(rowHtml.toString()
							.replace(PlaceholderConst.ID, id)
							.replace(PlaceholderConst.NAME, userLevel.getName())
							.replace(PlaceholderConst.SCORE_RATIO, String.valueOf(userLevel.getScore_ratio()))
							.replace(PlaceholderConst.EXPENSE, String.valueOf(userLevel.getExpense())));
					htmls.append("<td>"+privilegeHtml.toString().replace(PlaceholderConst.ID, id)+"</td>");
					htmls.append("</tr>");
					id.setLength(0);
				}
				userLevels.clear();
				userLevels=null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td style=\"text-align: center;\" colspan=\"5\">暂无数据(点击新增一行添加数据)</td>");
				htmls.append("</td>");
				htmls.append("</tr>");
			}
			if(getPrivilege().containsKey(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT)){
				adminPrivilege = getPrivilege().get(PrivilegeConst.ADMIN_PRIVILEGE_CODE_INSERT);
				rowHtml.append("<td><a class=\"button\" href=\"javascript:;\" data-insert=\"\" data-url=\""+adminPrivilege.getUrl()+"\">"+adminPrivilege.getName()+"</a>&nbsp;");
				adminPrivilege = null;
			}
			rowHtml.append("<a class=\"button\" href=\"javascript:;\" data-del-row=\"\" >删除</a></td>");
			request.setAttribute("rowHtml", rowHtml.toString().replace(PlaceholderConst.ID, "").replace(PlaceholderConst.NAME, "").replace(PlaceholderConst.EXPENSE, "").replace(PlaceholderConst.SCORE_RATIO, ""));
			request.setAttribute("privilegeHtml", privilegeHtml);
			request.setAttribute("operatorHtml", operatorHtml);
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看用户等级", getAdminUser(), LogEnum.QUERY);
		} catch (Exception e) {
			new YuleException("查看用户等级[/findUserLevel]错误",e);
			throw e;
		}
		return "/user/level/index";
	}


	/**
	 * 新增用户等级
	 */
	@RequestMapping(value = "/insertUserLevel",method = RequestMethod.POST)
	public String insertUserLevel(@ModelAttribute("userLevel")UserLevel userLevel) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = userLevelServiceImpl.insertUserLevel(userLevel);
			obj.put("id", userLevel.getId());
			obj.put("status", flag);
			AdminLogUtil.insertLog("新增用户等级", getAdminUser(), LogEnum.INSERT);
		} catch (Exception e) {
			new YuleException("新增用户等级[insertUserLevel]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 删除用户等级
	 */
	@RequestMapping(value = "/deleteUserLevel",method = RequestMethod.POST)
	public String deleteUserLevel(@RequestParam(value="id",required=false)String id) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = userLevelServiceImpl.deleteUserLevelById(id);
			obj.put("status", flag);
			AdminLogUtil.insertLog("删除用户等级", getAdminUser(), LogEnum.DELETE);
		} catch (Exception e) {
			new YuleException("删除用户等级[deleteUserLevel]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}
	
	/**
	 * 更新用户等级
	 */
	@RequestMapping(value = "/updateUserLevel",method = RequestMethod.POST)
	public String updateUserLevel(@ModelAttribute("userLevel")UserLevel userLevel) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("status", ErrorConst.STATUS_ERROR);
		try {
			boolean flag = userLevelServiceImpl.updateUserLevel(userLevel);
			obj.put("status", flag);
			AdminLogUtil.insertLog("更新用户等级", getAdminUser(), LogEnum.UPDATE);
		} catch (Exception e) {
			new YuleException("更新用户等级[updateUserLevel]错误",e);
			throw e;
		} finally{
			outputResult(obj.toString());
		}
		return null;
	}

	
	/**
	 * 批量修改用户等级
	 */
	@RequestMapping(value = "/batchUpdateUserlevel",method = RequestMethod.POST)
	public String batchUpdateUserlevel(InsertUserLevelParam insertUserLevelParam) throws Exception {
		try {
			userLevelServiceImpl.batchInsertAndUpdateUserLevel(insertUserLevelParam);
			AdminLogUtil.insertLog("批量新增并修改用户等级", getAdminUser(), LogEnum.BATCH);
		} catch (Exception e) {
			new YuleException("批量新增并修改用户等级[batchUpdateUserlevel]错误",e);
			throw e;
		}
		return ActionReturnConst.REDIRECT+"/userLevel/findUserLevel.do";
	}
}
