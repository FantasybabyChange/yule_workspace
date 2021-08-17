package com.yule.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.enumerate.LogEnum;
import com.yule.exception.YuleException;
import com.yule.mongo.admin.query.UserFavoriteQuery;
import com.yule.mongo.admin.service.IUserFavoriteMongo;
import com.yule.mongo.pojo.UserFavorite;
import com.yule.util.AdminLogUtil;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
@RequestMapping("/userFavorite")
public class UserFavoriteAction extends BaseAction{

	@Autowired
	private IUserFavoriteMongo userFavoriteMongoImpl;
	/**
	 * 查询
	 */
	@RequestMapping(value = "/findUserFavorite",method = RequestMethod.GET)
	public String findCollections(UserFavoriteQuery userFavoriteQuery,@RequestParam(value = "pageNo", required = false) Integer pageNo)throws Exception {
		if (pageNo==null||pageNo<1) {
			pageNo=1;
		}
		try {
			StringBuffer htmls = new StringBuffer();
			Page<UserFavorite> page = this.userFavoriteMongoImpl.findUserFavoritePageByUserId(userFavoriteQuery, PageConst.PAGE_SIZE_TEN, pageNo);
			htmls.append("<tfoot><tr><td colspan=\"8\">");
			htmls.append("<div class=\"bulk-actions align-left\"></div>");
			htmls.append(PaginationUtil.getPaginationHtml(page));
			htmls.append("<div class=\"clear\"></div>");
			htmls.append("</td></tr></tfoot>");
			htmls.append("<tbody>");
			if(page.getRowCount()>0){
				if (StringUtils.isEmpty(userFavoriteQuery.getCompanyId())) {
					for (UserFavorite userFavorite :page.getDatas()) {
						htmls.append("<tr>");
						htmls.append("<td>" + userFavorite.getCompany_name() + "</td>");
						htmls.append("</tr>");
					}
				}else {
					for (UserFavorite userFavorite :page.getDatas()) {
						htmls.append("<tr>");
						htmls.append("<td>" + userFavorite.getUser_name() + "</td>");
						htmls.append("</tr>");
					}
				}
			page.cleanDatas();
			page = null;
			}else{
				htmls.append("<tr>");
				htmls.append("<td class=\"td-center-style\" colspan=\"4\">暂无数据</td>");
				htmls.append("</tr>");
			}
			htmls.append("</tbody>");
			request.setAttribute("htmls", htmls);
			AdminLogUtil.insertLog("查看用户收藏", getAdminUser(), LogEnum.QUERY);
			htmls = null;
		} catch (Exception e) {
			new YuleException("查看用户收藏[/findCollections]错误");
			throw e;
		}
		return "/user/favorite/index";
	}
}
