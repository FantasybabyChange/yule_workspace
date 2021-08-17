package com.yule.param;

import java.io.Serializable;
import java.util.List;

import com.yule.pojo.ProductExpense;

public class InsertProductExpenseParam implements Serializable {

	private static final long serialVersionUID = 8904454233876991133L;
    
    private String  productId;
	
	private  List<ProductExpense> productExpense;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<ProductExpense> getProductExpense() {
		return productExpense;
	}

	public void setProductExpense(List<ProductExpense> productExpense) {
		this.productExpense = productExpense;
	}

	public InsertProductExpenseParam(String productId,
			List<ProductExpense> productExpense) {
		super();
		this.productId = productId;
		this.productExpense = productExpense;
	}

	public InsertProductExpenseParam() {
		super();
	}
}
