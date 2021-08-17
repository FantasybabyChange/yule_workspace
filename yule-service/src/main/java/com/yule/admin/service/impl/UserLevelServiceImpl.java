package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IUserLevelDao;
import com.yule.admin.param.InsertUserLevelParam;
import com.yule.admin.service.IUserLevelService;
import com.yule.exception.YuleException;
import com.yule.pojo.UserLevel;
import com.yule.util.IDUtil;

@Service("userLevelServiceImpl")
public class UserLevelServiceImpl implements IUserLevelService {

	@Autowired
	private IUserLevelDao userLevelDao;
	
	public boolean updateUserLevel(UserLevel userLevel) throws YuleException {
		boolean flag = false;
		try {
			this.userLevelDao.updateUserLevel(userLevel);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteUserLevelById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.userLevelDao.deleteUserLevelById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean insertUserLevel(UserLevel userLevel) throws YuleException {
		boolean flag = false;
		try {
			if (StringUtils.isEmpty(userLevel.getId())) {
				userLevel.setId(IDUtil.getID());
			}
			userLevelDao.insertUserLevel(userLevel);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteUserLevelAll() throws YuleException {
		boolean flag = false;
		try {
			userLevelDao.deleteUserLevelAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	
	public boolean batchInsertLevel(List<UserLevel> userLevels)throws YuleException{
		boolean flag=true;
		try {
			this.userLevelDao.batchInsertUserLevel(userLevels);
			flag=true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<UserLevel> findUserLevelList() throws YuleException {
		return userLevelDao.findUserLevelList();
	}
	
	
	public boolean batchInsertAndUpdateUserLevel(InsertUserLevelParam insertUserLevelParam) throws YuleException{
		boolean flag=false;
		try {
			List<String> lists = insertUserLevelParam.getId();
			if(null!=lists&&lists.size()>0){
				List<UserLevel> insertUserLevels = new ArrayList<UserLevel>();
				List<UserLevel> updateUserLevels = new ArrayList<UserLevel>();
				List<String> names = insertUserLevelParam.getName();
				List<Integer> expenses = insertUserLevelParam.getExpense();
				List<Integer> score_ratios = insertUserLevelParam.getScore_ratio();
				int i =0;
				UserLevel userLevel = null;
				for (String id : lists) {
					userLevel = new UserLevel();
					userLevel.setName(names.get(i));
					userLevel.setExpense(expenses.get(i));
					userLevel.setScore_ratio(score_ratios.get(i));
					if (StringUtils.isEmpty(id)) {
						userLevel.setId(IDUtil.getID());
						insertUserLevels.add(userLevel);
					}else {
						userLevel.setId(id);
						updateUserLevels.add(userLevel);
					}
					i++;
				}
				lists.clear();
				names.clear();
				expenses.clear();
				lists=null;
				names = null;
				expenses= null;
				if (insertUserLevels.size()>0) {
					this.userLevelDao.batchInsertUserLevel(insertUserLevels);
					insertUserLevels.clear();
				}
				insertUserLevels=null;
				if (updateUserLevels.size()>0) {
					this.userLevelDao.batchUpdateUserLevel(updateUserLevels);
					updateUserLevels.clear();
				}
				updateUserLevels=null;
				flag=true;
			}
			insertUserLevelParam = null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}


}
