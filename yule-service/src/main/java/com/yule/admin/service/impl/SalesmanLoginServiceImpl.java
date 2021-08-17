package com.yule.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ISalesmanDao;
import com.yule.admin.dao.ISalesmanLoginDao;
import com.yule.admin.query.SalesmanQuery;
import com.yule.admin.service.ISalesmanLoginService;
import com.yule.admin.vo.SalesmanVO;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.pojo.Salesman;
import com.yule.pojo.SalesmanLogin;
import com.yule.util.EncryptUtil;
import com.yule.util.IDUtil;
import com.yule.vo.Page;
@Service("salesmanLoginServiceImpl")
public class SalesmanLoginServiceImpl implements ISalesmanLoginService {
	@Autowired
	private ISalesmanLoginDao salesmanLoginDao;
	@Autowired
	private ISalesmanDao salesmanDao;
	public boolean insertSalesmanLogin(SalesmanLogin salesmanLogin,Integer commision)throws YuleException {
		boolean flag = false;
		try {
			String id = salesmanLogin.getId();
			if (StringUtils.isEmpty(id)) {
				id = IDUtil.getID();
				salesmanLogin.setId(id);
			}
			salesmanLogin.setPassword(EncryptUtil.encryptToMD5(salesmanLogin.getPassword()));
			salesmanLogin.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			salesmanLogin.setStatus(StatusConst.STATUS_TRUE);
			this.salesmanLoginDao.insertSalesmanLogin(salesmanLogin);
			Salesman salesman = new Salesman();
			salesman.setId(id);
			salesman.setCommision(commision);
			salesman.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.salesmanDao.insertSalesman(salesman);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteSalesmanLogin(SalesmanLogin salesmanLogin) throws YuleException {
		boolean flag = false;
		try {
			this.salesmanLoginDao.updateSalesmanLogin(salesmanLogin);
			Salesman salesman = new Salesman();
			salesman.setId(salesmanLogin.getId());
			salesman.setIs_delete(salesmanLogin.getIs_delete());
			this.salesmanDao.updateSalesman(salesman);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateSalesmanLoginStatus(SalesmanLogin salesmanLogin)throws YuleException {
		boolean flag = false;
		try {
			this.salesmanLoginDao.updateSalesmanLogin(salesmanLogin);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateSalesmanLoginPassword(SalesmanLogin salesmanLogin)throws YuleException {
		boolean flag = false;
		try {
			salesmanLogin.setPassword(EncryptUtil.encryptToMD5(salesmanLogin.getPassword()));
			this.salesmanLoginDao.updateSalesmanLogin(salesmanLogin);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public Page<SalesmanVO> findSalesmanVOPage(SalesmanQuery salesmanQuery,
			int pageSize, int pageNo) throws YuleException {
		Page<SalesmanVO> page = new Page<SalesmanVO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageStart", (pageNo - 1) * pageSize);
		params.put("pageEnd", pageSize);
		if ( null != salesmanQuery) {
		params.put("account", salesmanQuery.getAccount());
		params.put("name", salesmanQuery.getName());
		params.put("start_time", salesmanQuery.getStart_time());
		params.put("end_time", salesmanQuery.getEnd_time());
		params.put("status", salesmanQuery.getStatus());
		params.put("is_delete", salesmanQuery.getIs_delete());
		}
		page.setDatas(this.salesmanLoginDao.findSalesmanVOPage(params));
		page.setRowCount(this.salesmanLoginDao.findSalesmanVOCount(params));
		page.setPageSize(pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	public boolean findSalesmanLoginByAccount(String account)throws YuleException {
		boolean flag = false;
		try {
			int count = this.salesmanLoginDao.findSalesmanLoginByAccount(account);
			if (count <= 0) {
				flag = true;
			}
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	public List<SalesmanLogin> findSalesmanLoginList()throws YuleException{
		return this.salesmanLoginDao.findSalesmanLoginList();
		
	}
}
