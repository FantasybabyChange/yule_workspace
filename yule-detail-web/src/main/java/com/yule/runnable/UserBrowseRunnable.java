package com.yule.runnable;

import com.yule.constant.CustomBeanConst;
import com.yule.detail.vo.CompanyVO;
import com.yule.exception.YuleException;
import com.yule.mongo.detail.service.IUserBrowseMongo;
import com.yule.mongo.pojo.UserBrowse;
import com.yule.util.CustomBeanFactory;


public class UserBrowseRunnable implements Runnable {

	private String user_id;
	private CompanyVO companyVO;
	private IUserBrowseMongo userBrowseMongoImpl = (IUserBrowseMongo) CustomBeanFactory.getContext(CustomBeanConst.DETAIL_MONGO_PATHS).getBean("userBrowseMongoImpl");
	
	
	public UserBrowseRunnable(String user_id,CompanyVO companyVO) {
		this.user_id = user_id;
		this.companyVO = companyVO;
	}

	public void run() {
		try {
			UserBrowse userBrowse = new UserBrowse();
			userBrowse.setUser_id(user_id);
			userBrowse.setCompany_id(companyVO.getId());
			userBrowse.setArea_county_name(companyVO.getCountry_name());
			userBrowse.setCompany_name(companyVO.getCompany_name());
			userBrowse.setArea_city_name(companyVO.getCity_name());
			userBrowse.setAddress_datail(companyVO.getAddress_detail());
			userBrowse.setCompany_category_id(companyVO.getCompany_category_id());
			userBrowse.setCompany_category_name(companyVO.getCompany_category_name());
			userBrowse.setCompany_grade_id(companyVO.getCompany_grade_id());
			userBrowse.setCompany_grade_name(companyVO.getCompany_grade_name());
			this.userBrowseMongoImpl.insertUserBrowse(userBrowse);
		} catch (Exception e) {
			new YuleException("插入企业浏览记录出现异常!",e);
			e.printStackTrace();
		}
	}
}
