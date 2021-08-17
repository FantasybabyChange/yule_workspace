package com.yule.mongo.company.service;

import com.yule.exception.YuleException;
import com.yule.mongo.pojo.UserExpense;

public interface IUserExpenseMongo {
	
	public boolean insertUserExpense(UserExpense userExpense) throws YuleException;

	public double findUserExpenseByuserId(String id)throws YuleException;
}
