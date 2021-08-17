package com.yule.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yule.admin.dao.IUserDao;
import com.yule.admin.dao.IUserLoginDao;
import com.yule.admin.query.UserLoginQuery;
import com.yule.admin.service.IUserLoginService;
import com.yule.exception.YuleException;
import com.yule.pojo.User;
import com.yule.pojo.UserLogin;
import com.yule.util.EncryptUtil;
import com.yule.vo.Page;

@Service("userLoginServiceImpl")
public class UserLoginServiceImpl implements IUserLoginService{
	
	@Autowired
	private IUserLoginDao userLoginDao;
	@Autowired
	private IUserDao userDao;
	

	public boolean updateUserLogin(UserLogin userLogin) throws YuleException {
		boolean flag=false;
		try {
			if (null!=userLogin.getPassword()) {
				userLogin.setPassword(EncryptUtil.encryptToMD5(userLogin.getPassword()));
			}
			this.userLoginDao.updateUserLogin(userLogin);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<UserLogin> findUserLoginPage(UserLoginQuery userLoginQuery,int pageSize, int pageNo)throws YuleException {
		Page<UserLogin> page=new Page<UserLogin>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		params.put("name", userLoginQuery.getName());
		params.put("phone", userLoginQuery.getPhone());
		params.put("mail", userLoginQuery.getMail());
		params.put("is_delete", userLoginQuery.getIs_delete());
		params.put("start_time", userLoginQuery.getStart_time());
		params.put("end_time", userLoginQuery.getEnd_time());
		params.put("status", userLoginQuery.getStatus());
		page.setDatas(this.userLoginDao.findUserLoginList(params));
		page.setPageSize(pageSize);
		page.setRowCount(this.userLoginDao.findUserLoginCount(params));
		page.setPageNo(pageNo);
		return page;
	}
                    

	public UserLogin findUserLoginById(String id) throws YuleException {
		return this.userLoginDao.findUserLoginById(id);
	}

	public boolean deleteUserLoginById(UserLogin userLogin) throws YuleException {
		boolean flag = false;
		try {
			this.userLoginDao.deleteUserLoginById(userLogin);
			User user =new User();
			user.setId(userLogin.getId());
			user.setIs_delete(userLogin.getIs_delete());
			this.userDao.deleteUserById(user);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
