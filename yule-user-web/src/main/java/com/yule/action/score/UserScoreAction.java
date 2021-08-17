package com.yule.action.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.PageConst;
import com.yule.constant.ScoreConst;
import com.yule.mongo.pojo.UserScore;
import com.yule.mongo.user.query.UserScoreQuery;
import com.yule.mongo.user.service.IUserScoreMongo;
import com.yule.util.PaginationUtil;
import com.yule.vo.Page;

@Controller
@Scope("prototype")
public class UserScoreAction extends BaseAction{
	
	@Autowired
	private IUserScoreMongo userScoreMongoImpl;

	@RequestMapping(value = "/score")
	public String findScore(UserScoreQuery userScoreQuery,@RequestParam(value="pageNo",required=false)Integer pageNo) throws Exception {
		if(null==pageNo){
			pageNo = 1;
		}
		userScoreQuery.setUserId(getCookieValue());
		Page<UserScore> page = userScoreMongoImpl.findUserScorePage(userScoreQuery, PageConst.PAGE_SIZE_TEN, pageNo);
		StringBuffer htmls = new StringBuffer("");
		if(page.getRowCount()>0){
			List<UserScore> userScores = page.getDatas();
			for(UserScore userScore:userScores){
				htmls.append("<tr>");
				htmls.append("<td>"+ScoreConst.SCORES[userScore.getType()]+"</td>");
				htmls.append("<td>"+userScore.getScore()+"</td>");
				htmls.append("<td>"+ScoreConst.STATUS[userScore.getStatus()]+"</td>");
				htmls.append("</tr>");
			}
			userScores.clear();
		}
		request.setAttribute("pageHtmls", PaginationUtil.getPaginationHtml(page));
		request.setAttribute("htmls", htmls.toString());
		request.setAttribute("userScoreQuery", userScoreQuery);
		return "score";
	}
	
}