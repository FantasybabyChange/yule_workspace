package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.ICompanyGradeDao;
import com.yule.admin.param.InsertCompanyGradeParam;
import com.yule.admin.service.ICompanyGradeService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.CompanyGrade;
import com.yule.util.IDUtil;
import com.yule.vo.CompanyGradeVO;

@Service("companyGradeServiceImpl")
public class CompanyGradeServiceImpl implements ICompanyGradeService {

	@Autowired
	private ICompanyGradeDao companyGradeDao;

	public boolean insertCompanyGrade(CompanyGrade companyGrade) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(companyGrade.getId())){
				companyGrade.setId(IDUtil.getID());
			}
			companyGrade.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.companyGradeDao.insertCompanyGrade(companyGrade);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean updateCompanyGrade(CompanyGrade companyGrade) throws YuleException {
		boolean flag = false;
		try {
			this.companyGradeDao.updateCompanyGrade(companyGrade);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

	public boolean deleteCompanyGradeById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.companyGradeDao.deleteCompanyGradeById(id);
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean deleteCompanyGradeAll() throws YuleException {
		boolean flag = false;
		try {
			this.companyGradeDao.deleteCompanyGradeAll();
			flag = true;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public List<CompanyGrade> findCompanyGradeList() throws YuleException {
		return companyGradeDao.findCompanyGradeList();
	}

	public List<CompanyGradeVO> findCompanyGradeVOList() throws YuleException {
		return companyGradeDao.findCompanyGradeVOList();
	}

	public boolean batchInsertAndUpdateCompanyGrade(InsertCompanyGradeParam insertCompanyGradeParam) throws YuleException {
		boolean flag =false;
		try {
			List<String> lists = insertCompanyGradeParam.getId();
			if(null != lists && lists.size()>0){
				List<CompanyGrade> insertCompanyGrades = new ArrayList<CompanyGrade>();
				List<CompanyGrade> updateCompanyGrades = new ArrayList<CompanyGrade>();
				List<String> names = insertCompanyGradeParam.getName();
				List<Integer> orders = insertCompanyGradeParam.getOrder();
				int i =0;
				CompanyGrade companyGrade = null;
				for (String id:lists) {
					companyGrade = new CompanyGrade();
					companyGrade.setName(names.get(i));
					companyGrade.setOrder(orders.get(i));
					if(StringUtils.isEmpty(id)){
						companyGrade.setId(IDUtil.getID());
						companyGrade.setIs_delete(DeleteConst.IS_DELETE_TRUE);
						insertCompanyGrades.add(companyGrade);
					}else{
						companyGrade.setId(id);
						updateCompanyGrades.add(companyGrade);
					}
					i++;
				}
				lists.clear();
				names.clear();
				orders.clear();
				lists = null;
				names = null;
				orders = null;
				if(insertCompanyGrades.size()>0){
					   this.companyGradeDao.batchInsertCompanyGrade(insertCompanyGrades);	
					   insertCompanyGrades.clear();
				}
				insertCompanyGrades=null;
				if(updateCompanyGrades.size()>0){
					   this.companyGradeDao.batchUpdateCompanyGrade(updateCompanyGrades);	
					   updateCompanyGrades.clear();
				}
				updateCompanyGrades=null;
				flag = true;
			}
			insertCompanyGradeParam=null;
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}
	
	public boolean batchInsertCompanyGrade(List<CompanyGrade> companyGrades) throws YuleException {
		boolean flag =false;
		try {
			this.companyGradeDao.batchInsertCompanyGrade(companyGrades);	
		} catch (Exception e) {
	    	throw new YuleException(e);
	    }
		return flag;
	}

}
