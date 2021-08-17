package com.yule.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yule.admin.dao.IAdminNoticeAttachmentDao;
import com.yule.admin.dao.IAdminNoticeDao;
import com.yule.admin.param.InsertAdminNoticeParam;
import com.yule.admin.query.AdminNoticeQuery;
import com.yule.admin.service.IAdminNoticeService;
import com.yule.constant.DeleteConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminNotice;
import com.yule.pojo.AdminNoticeAttachment;
import com.yule.util.IDUtil;
import com.yule.vo.Page;
@Service("adminNoticeServiceImpl")
public class AdminNoticeServiceImpl implements IAdminNoticeService {

	@Autowired
	private IAdminNoticeDao adminNoticeDao;
	
	@Autowired
	private IAdminNoticeAttachmentDao adminNoticeAttachmentDao;

	public boolean insertAdminNotice(AdminNotice adminNotice) throws YuleException {
		boolean flag = false;
		try {
			if(StringUtils.isEmpty(adminNotice.getId())){
				adminNotice.setId(IDUtil.getID());
			}
			adminNotice.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			this.adminNoticeDao.insertAdminNotice(adminNotice);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public boolean updateAdminNotice(AdminNotice adminNotice) throws YuleException {
		boolean flag = false;
		try {
			this.adminNoticeDao.updateAdminNotice(adminNotice);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;

	}

	public boolean deleteAdminNoticeById(String id) throws YuleException {
		boolean flag = false;
		try {
			this.adminNoticeDao.deleteAdminNoticeById(id);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}


	public List<AdminNotice> findAdminNoticeList() throws YuleException {
		return this.adminNoticeDao.findAdminNoticeList();
	}


	public Page<AdminNotice> findAdminNoticePage(AdminNoticeQuery adminNoticeQuery ,int pageSize, int pageNo) throws YuleException {
		Page<AdminNotice> page = new Page<AdminNotice>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null!=adminNoticeQuery) {
				params.put("title", adminNoticeQuery.getTitle());
				params.put("is_delete", adminNoticeQuery.getIs_delete());
				params.put("start_time", adminNoticeQuery.getStart_time());
				params.put("end_time", adminNoticeQuery.getEnd_time());
				params.put("type", adminNoticeQuery.getType());
			}
			params.put("pageStart", (pageNo - 1) * pageSize);
			params.put("pageEnd", pageSize);
			page.setDatas(this.adminNoticeDao.findAdminNoticeList(params));
			page.setRowCount(this.adminNoticeDao.findAdminNoticeCount(params));
			page.setPageSize(pageSize);
			page.setPageNo(pageNo);
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return page;
	}

	public boolean batchUpdateAdminNoticeById(List<String> id) throws YuleException {
		boolean flag = false;
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("ids", id);
			params.put("is_delete", DeleteConst.IS_DELETE_FALSE);
			this.adminNoticeDao.batchUpdateAdminNoticeById(params);
			flag = true;
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}
	
	public boolean insertAdminNotice(InsertAdminNoticeParam adminNoticeAttachmentParam)throws YuleException {
		boolean flag = false;
		try {
			AdminNotice adminNotice =new AdminNotice();
			adminNotice.setTitle(adminNoticeAttachmentParam.getTitle());
			adminNotice.setId(IDUtil.getID());
			adminNotice.setIs_delete(DeleteConst.IS_DELETE_TRUE);
			adminNotice.setContent(adminNoticeAttachmentParam.getContent());
			adminNotice.setType(adminNoticeAttachmentParam.getType());
			List<String> names= adminNoticeAttachmentParam.getName();
			List<String> sizes=adminNoticeAttachmentParam.getSize();
			List<String> paths = adminNoticeAttachmentParam.getPath();
			List<String> system_names = adminNoticeAttachmentParam.getSystem_name();
			List<String> types= adminNoticeAttachmentParam.getAttachment_type();
			this.adminNoticeDao.insertAdminNotice(adminNotice);
			if (null!=names&&names.size()>0) {
				List<AdminNoticeAttachment> adminNoticeAttachments = new ArrayList<AdminNoticeAttachment>();
				AdminNoticeAttachment adminNoticeAttachment = null;
				for (int i = 0; i < names.size(); i++) {
					adminNoticeAttachment = new AdminNoticeAttachment();
					adminNoticeAttachment.setId(IDUtil.getID());
					adminNoticeAttachment.setName(names.get(i));
					adminNoticeAttachment.setIs_delete(DeleteConst.IS_DELETE_TRUE);
					adminNoticeAttachment.setType(types.get(i));
					adminNoticeAttachment.setSystem_name(system_names.get(i));
					adminNoticeAttachment.setSize(sizes.get(i));
					adminNoticeAttachment.setPath(paths.get(i));
					adminNoticeAttachment.setAdmin_notice_id(adminNotice.getId());
					adminNoticeAttachments.add(adminNoticeAttachment);
				}
				names.clear();
				names = null;
				sizes.clear();
				sizes=null;
				paths.clear();
				paths=null;
				system_names.clear();
				system_names=null;
				types.clear();
				types=null;
				this.adminNoticeAttachmentDao.batchInsertAdminNoticeAttachment(adminNoticeAttachments);
				adminNoticeAttachments.clear();
				adminNoticeAttachments = null;
				adminNoticeAttachmentParam = null;
				flag = true;
			}
		} catch (Exception e) {
			throw new YuleException(e);
		}
		return flag;
	}

	public AdminNotice findAdminNoticeVOById(String id) throws YuleException {
		return this.adminNoticeDao.findAdminNoticeVOById(id);
	}
}
