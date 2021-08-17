package com.yule.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.yule.vo.AdminNoticeAttachmentVO;


/**	
 * 系统通知
 */
public class AdminNotice implements Serializable {
	
	
	private static final long serialVersionUID = 2441250540636193947L;
	
	private String id;
	//标题
	private String title;
	//内容
	private String content;
	//是否删除(0未删除,1删除)
	private Integer is_delete;
	//创建时间
	private Timestamp create_time;
	//更新时间
	private Timestamp update_time;
	
	private Integer type;

	private List<AdminNoticeAttachmentVO> adminNoticeAttachmentVOs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	
	public List<AdminNoticeAttachmentVO> getAdminNoticeAttachmentVOs() {
		return adminNoticeAttachmentVOs;
	}
	public void setAdminNoticeAttachmentVOs(
			List<AdminNoticeAttachmentVO> adminNoticeAttachmentVOs) {
		this.adminNoticeAttachmentVOs = adminNoticeAttachmentVOs;
	}
	public AdminNotice() {
		super();
	}
}
