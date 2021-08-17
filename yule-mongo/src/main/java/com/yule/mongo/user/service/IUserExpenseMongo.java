package com.yule.mongo.user.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserExpense;
import com.yule.mongo.user.query.UserExpenseQuery;
import com.yule.mongo.user.vo.UserExpenseVO;
import com.yule.vo.Page;

public interface IUserExpenseMongo {
	
	public Page<UserExpense> findUserExpensePage(UserExpenseQuery userExpenseQuery,int pageSize,int pageNo) throws YuleException;
	
	public UserExpenseVO findUserExpenseVO(UserExpenseQuery userExpenseQuery) throws YuleException;
	
	public boolean insertUserExpense(UserExpense userExpense) throws YuleException;

}
