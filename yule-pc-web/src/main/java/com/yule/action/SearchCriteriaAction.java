package com.yule.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yule.common.BaseAction;
import com.yule.util.SearchCriteriaUtil;

@Controller
@Scope("prototype")
public class SearchCriteriaAction extends BaseAction{
	
	@RequestMapping(value = "/findSearchCriteria",method = RequestMethod.POST)
	public String findAreaCityNav() throws Exception {
//		if(!JedisUtil.getInstance().exists(IndexRedisConst.INDEX_SEARCH_CRITERIA)){
			StringBuffer htmls = new StringBuffer();
			htmls.append(SearchCriteriaUtil.getCompanyGrade());
			htmls.append(SearchCriteriaUtil.getCompanyCategory());
			htmls.append(SearchCriteriaUtil.getCompanyPointCategory());
//			JedisUtil.getInstance().set(IndexRedisConst.INDEX_SEARCH_CRITERIA, htmls.toString());
//			JedisUtil.getInstance().pexpire(IndexRedisConst.INDEX_SEARCH_CRITERIA,TimeConst.ONE_DAY);
//		}
		outputResult(htmls.toString());
	    return null;
	}
	
}