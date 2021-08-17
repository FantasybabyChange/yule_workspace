package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyServeDao;
import com.yule.admin.dao.ICompanyServeRelevantDao;
import com.yule.admin.param.InsertCompanyServeParam;
import com.yule.admin.param.UpdateCompanyServeCheckParam;
import com.yule.admin.service.ICompanyServeService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyServe;
import com.yule.pojo.CompanyServeRelevant;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyServeVO;
@Service("companyServeServiceImpl")
public class CompanyServeServiceImpl implements ICompanyServeService {

	@Autowired
	private ICompanyServeDao companyServeDao;
	
	@Autowired
	private ICompanyServeRelevantDao companyServeRelevantDao;
	
	public boolean updateCompanyServe(CompanyServe companyServe) throws YuleException {
		boolean flag =false;
        try {
        	  this.companyServeDao.updateCompanyServe(companyServe);
        	  flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyServeById(String id) throws YuleException {
		boolean flag =false;
		try {
			this.companyServeDao.deleteCompanyServeById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteCompanyServeAll() throws YuleException {
		boolean flag =false;
		try {
			this.companyServeDao.deleteCompanyServeAll();
			this.companyServeRelevantDao.deleteCompanyServeRelevantAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<CompanyServe> findCompanyServeIsCheckList(String companyId) throws YuleException{
		return this.companyServeDao.findCompanyServeCheckList(companyId);
	}

	public List<CompanyServe> findCompanyServeList() throws YuleException {
		return this.companyServeDao.findCompanyServeList();
	}

	public List<CompanyServeVO> findCompanyServeCheckList(String companyId) throws YuleException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_id", companyId);
		return this.companyServeDao.findCompanyAndServeList(params);
	}
	
	public boolean batchInsertAndUpdateCompanyServe(InsertCompanyServeParam insertCompanyServeParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertCompanyServeParam.getId();
			if(null != lists && lists.size()>0){
				List<CompanyServe> insertCompanyServes = new ArrayList<CompanyServe>();
				List<CompanyServe> updateCompanyServes = new ArrayList<CompanyServe>();
				List<String> names = insertCompanyServeParam.getName();
				List<Integer> orders = insertCompanyServeParam.getOrder();
				int i=0;
				CompanyServe companyServe = null;
				for (String id:lists) {
					companyServe = new CompanyServe();
					companyServe.setName(names.get(i));
					companyServe.setOrder(orders.get(i));
					if(StringUtils.isEmpty(id)){
						companyServe.setId(IDUtil.getID());
						companyServe.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertCompanyServes.add(companyServe);
					}else{
						companyServe.setId(id);
						updateCompanyServes.add(companyServe);
					}
					i++;
				}
				lists.clear();
				names.clear();
				orders.clear();
				lists = null;
				names = null;
				orders = null;
				if(insertCompanyServes.size()>0){
					   this.companyServeDao.batchInsertCompanyServe(insertCompanyServes);	
					   insertCompanyServes.clear();
				}
				insertCompanyServes=null;
				if(updateCompanyServes.size()>0){
					   this.companyServeDao.batchUpdateCompanyServe(updateCompanyServes);	
					   updateCompanyServes.clear();
				}
				updateCompanyServes=null;
				flag = true;
			}
			insertCompanyServeParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean batchInsertCompanyServe(List<CompanyServe> companyServes) throws YuleException {
		boolean flag =false;
		try {
			this.companyServeDao.batchInsertCompanyServe(companyServes);	
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean updateCompanyServeCheck(UpdateCompanyServeCheckParam updateCompanyServeCheckParam) throws YuleException {
		boolean flag = false;
		try{
			Map<String,Object> params = new HashMap<String, Object>();
			String companyId = updateCompanyServeCheckParam.getCompany_id();
			params.put("companyId", companyId);
			List<String> companyServeIds = updateCompanyServeCheckParam.getCompany_serve_id();
			if(null!=companyServeIds&&companyServeIds.size()>0){
				List<CompanyServeRelevant> lists = new ArrayList<CompanyServeRelevant>();
				CompanyServeRelevant companyServeRelevant = null;
				for (String s : companyServeIds) {
					companyServeRelevant=new CompanyServeRelevant();
					companyServeRelevant.setCompany_serve_id(s);
					companyServeRelevant.setId(IDUtil.getID());
					lists.add(companyServeRelevant);
				}
				params.put("companyServes", lists);
				companyServeIds.clear();
				companyServeIds= null;
				companyServeRelevantDao.updateCompanyServeRelevant(params);
				lists.clear();
				lists = null;
			}
			params.clear();
			flag = true;
		} catch(Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean insertCompanyServe(CompanyServe companyServe)
			throws YuleException {
		boolean flag =false;
        try {
        	companyServe.setId(IDUtil.getID());
        	companyServe.setIs_delete(DeleteConst.IS_DELETE_TRUE);
        	this.companyServeDao.insertCompanyServe(companyServe);
        	flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
