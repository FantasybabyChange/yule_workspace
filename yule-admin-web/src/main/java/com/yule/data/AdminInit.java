package com.yule.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yule.admin.service.IAdminPrivilegeService;
import com.yule.admin.service.IAdminRoleService;
import com.yule.admin.service.IAdminUserService;
import com.yule.constant.AdminConst;
import com.yule.constant.ConfigConst;
import com.yule.constant.CustomBeanConst;
import com.yule.constant.DeleteConst;
import com.yule.constant.StatusConst;
import com.yule.exception.YuleException;
import com.yule.pojo.AdminPrivilege;
import com.yule.pojo.AdminRole;
import com.yule.pojo.AdminRolePrivilege;
import com.yule.pojo.AdminUser;
import com.yule.util.ConvertUtil;
import com.yule.util.CustomBeanFactory;
import com.yule.util.IDUtil;

public class AdminInit {
	
	private static List<String> adminPrivilegeIds = new ArrayList<String>();
	
	private static Element root = null;
	
	static{
		SAXReader sr = new SAXReader();
		try {
			InputStream in = AdminInit.class.getClassLoader().getResourceAsStream(ConfigConst.ADMIN);
			Document doc = sr.read(in);
			root = doc.getRootElement();
			in.close();
			doc.clearContent();
		} catch (Exception e) {
			new YuleException(e);
		}
	}
	
	
	public static void init() throws Exception{
		initAdminPrivilege();
		initAdminUser();
	}

	@SuppressWarnings("unchecked")
	private static void initAdminPrivilege() throws Exception{
		IAdminPrivilegeService adminPrivilegeServiceImpl = (IAdminPrivilegeService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminPrivilegeServiceImpl");
		adminPrivilegeServiceImpl.deleteAdminPrivilegeAll();
		Element privileges = root.element("privilege");
		List<Element> admin_privileges_one = privileges.elements("privilege");
		List<Element> admin_privileges_two = null;
		List<Element> admin_privileges_three = null;
		
		StringBuffer one_id = new StringBuffer();
		StringBuffer two_id = new StringBuffer();
		StringBuffer three_id = new StringBuffer();
		int i = 0;
		int k = 0;
		int j = 0;
		List<AdminPrivilege> adminPrivileges = new ArrayList<AdminPrivilege>();
		AdminPrivilege adminPrivilege = null;
		for(Element admin_privilege_one : admin_privileges_one){
			adminPrivilege = new AdminPrivilege();
			one_id.append(IDUtil.getID());
			adminPrivilege.setId(one_id.toString());
			adminPrivilege.setName(admin_privilege_one.attributeValue("name"));
			adminPrivilege.setUrl(admin_privilege_one.attributeValue("url"));
			adminPrivilege.setCode(admin_privilege_one.attributeValue("code"));
			adminPrivilege.setParent_id(null);
			adminPrivilege.setIs_show(ConvertUtil.stringToInteger(admin_privilege_one.attributeValue("is_show")));
			adminPrivilege.setOrder(i);
			adminPrivileges.add(adminPrivilege);
			adminPrivilegeIds.add(adminPrivilege.getId());
			admin_privileges_two = admin_privilege_one.elements("privilege");
			if(null!=admin_privileges_two&&admin_privileges_two.size()>0){
				for(Element admin_privilege_two : admin_privileges_two){
					adminPrivilege = new AdminPrivilege();
					two_id.append(IDUtil.getID());
					adminPrivilege.setParent_id(one_id.toString());
					adminPrivilege.setId(two_id.toString());
					adminPrivilege.setName(admin_privilege_two.attributeValue("name"));
					adminPrivilege.setUrl(admin_privilege_two.attributeValue("url"));
					adminPrivilege.setCode(admin_privilege_two.attributeValue("code"));
					adminPrivilege.setIs_show(ConvertUtil.stringToInteger(admin_privilege_two.attributeValue("is_show")));
					adminPrivilege.setOrder(k);
					adminPrivileges.add(adminPrivilege);
					adminPrivilegeIds.add(adminPrivilege.getId());
					admin_privileges_three = admin_privilege_two.elements("privilege");
					if(null!=admin_privileges_three&&admin_privileges_three.size()>0){
						for(Element admin_privilege_three : admin_privileges_three){
							adminPrivilege = new AdminPrivilege();
							three_id.append(IDUtil.getID());
							adminPrivilege.setParent_id(two_id.toString());
							adminPrivilege.setId(three_id.toString());
							adminPrivilege.setName(admin_privilege_three.attributeValue("name"));
							adminPrivilege.setUrl(admin_privilege_three.attributeValue("url"));
							adminPrivilege.setCode(admin_privilege_three.attributeValue("code"));
							adminPrivilege.setIs_show(ConvertUtil.stringToInteger(admin_privilege_three.attributeValue("is_show")));
							adminPrivilege.setOrder(j);
							adminPrivileges.add(adminPrivilege);
							adminPrivilegeIds.add(adminPrivilege.getId());
							three_id.setLength(0);
							j++;
						}
						admin_privileges_three.clear();
						admin_privileges_three = null;
					}
					two_id.setLength(0);
					k++;
					j=0;
				}
				admin_privileges_two.clear();
				admin_privileges_two = null;
			}
			one_id.setLength(0);
			i++;
			k=0;
		}
		adminPrivilegeServiceImpl.batchInsertAdminPrivilege(adminPrivileges);
		adminPrivileges.clear();
		adminPrivileges = null;
		adminPrivilegeServiceImpl = null;
		
	}
	
	private static void initAdminUser() throws Exception{
		IAdminRoleService adminRoleServiceImpl = (IAdminRoleService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminRoleServiceImpl");
		IAdminUserService adminUserServiceImpl = (IAdminUserService)CustomBeanFactory.getContext(CustomBeanConst.ADMIN_SERVICE_PATHS).getBean("adminUserServiceImpl");
		adminRoleServiceImpl.deleteAdminRoleAll();
		adminUserServiceImpl.deleteAdminUserAll();
		
		Element role = root.element("role");
		Element admin_role = role.element("admin_role");
		String admin_role_id = IDUtil.getID();
		AdminRole adminRole = new AdminRole();
		adminRole.setId(admin_role_id);
		adminRole.setName(admin_role.attributeValue("name"));
		adminRole.setIs_admin(AdminConst.IS_ADMIN_TRUE);
		adminRole.setIs_delete(DeleteConst.IS_DELETE_TRUE);
		adminRoleServiceImpl.insertAdminRole(adminRole);
		
		
		Element user = root.element("user");
		Element admin_user = user.element("admin_user");
		String admin_user_id = IDUtil.getID();
		AdminUser adminUser = new AdminUser();
		adminUser.setId(admin_user_id);
		adminUser.setAdmin_role_id(admin_role_id);
		adminUser.setAccount(admin_user.attributeValue("account"));
		adminUser.setPassword(admin_user.attributeValue("password"));
		adminUser.setStatus(StatusConst.STATUS_TRUE);
		adminUserServiceImpl.insertAdminUser(adminUser);
		
		List<AdminRolePrivilege> adminRolePrivileges = new ArrayList<AdminRolePrivilege>();
		AdminRolePrivilege adminRolePrivilege = null;
		for(String pid : adminPrivilegeIds){
			adminRolePrivilege = new AdminRolePrivilege();
			adminRolePrivilege.setId(IDUtil.getID());
			adminRolePrivilege.setAdmin_role_id(admin_role_id);
			adminRolePrivilege.setAdmin_privilege_id(pid);
			adminRolePrivileges.add(adminRolePrivilege);
		}
		adminRoleServiceImpl.batchInsertAdminRolePrivilege(adminRolePrivileges);
		adminRolePrivileges.clear();
		adminRolePrivileges = null;
		adminPrivilegeIds.clear();
		adminPrivilegeIds = null;
		adminUserServiceImpl = null;
		adminRoleServiceImpl = null;
	}
	
}
