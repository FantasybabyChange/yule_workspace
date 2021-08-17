<%@page import="com.yule.vo.UserLoginVO"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>
   	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="dns-prefetch" href="//static.yuleing.com">
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <title>个人设置</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
    <link rel="stylesheet" href="http://static.yuleing.com/css/uploadify.css" type="text/css" media="screen" />
</head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix mt-10">
        <div class="user-center-left">
            <%@ include file="/common/menu.jsp" %>
        </div>
        <% UserLoginVO userLoginVO = (UserLoginVO)request.getAttribute("userLoginVO"); %>
        <div class="user-center-right">
            <h2 class="user-center-title">设置</h2>
            <p class="user-profile-tips">修改您的个人信息、以及出行偏好</p>
            <div class="user-profile-title">个人信息</div>
            <div class="settings-panel-content">
            	<div class="setting-item">
                    <p class="settings_label">个人照片</p>
                    <p class="settings_user_data ">
	                    <input 	id="user_face"  type="file"  data-upload="" />
	                    <img alt="" src="http://images.yuleing.com/user/${user.id }/150_150user_face.jpg" id="user_face_image" style="width: 150;height: 150">
                    </p>
                </div>
                <div class="setting-item">
                    <p class="settings_label">姓名</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span>
                        <span data-lable=""><%=userLoginVO.getName() %></span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                    	<label >姓名</label>
                        <input type="text" name="name" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="<%=userLoginVO.getName() %>" nullmsg="请输入姓名!" datatype="" errormsg="" >
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-login="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">地址</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit" id="">编辑</a>
                        </span> 
                        <span data-lable="">${user.area_province_name } ${user.area_city_name } ${user.address }</span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                        <label >省</label>
                        <select id="province" name="area_province_id" data-ajax="city" data-target="#city" data-name="provinceId" title="输入您的地址，我们即可自动输入您的资料以方便您更快速轻松地预订。" class="bootstrapped-input input-text input-xlarge" size="1" maxlength="100" nullmsg="请输入选择省!" datatype="" errormsg="" >
                        	<option value="">请选择</option>
                        </select>
                        <div class="clear"></div>
                        <label >市</label>
                        <select id="city" name="area_city_id" title="输入您的地址，我们即可自动输入您的资料以方便您更快速轻松地预订。" class="bootstrapped-input input-text input-xlarge" size="1" maxlength="100" nullmsg="请选择市!" datatype="" errormsg="" >
                        	<option value="">请选择</option>
                        </select>
                        <div class="clear"></div>
                        <label >详细地址</label>
                        <input type="text" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" name="address" class="bootstrapped-input input-text input-xlarge" size="60" maxlength="100" value="${user.address }" nullmsg="请输入详细地址!" datatype="" errormsg="" >
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-user="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">电话</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span> 
                        <span data-lable=""><%=userLoginVO.getPhone() %></span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                    	<% 
                    		if(!StringUtils.isEmpty(userLoginVO.getPhone())){
                    	%>
	                        <label >原手机号码:<span name="phone"><%=userLoginVO.getPhone() %></span></label>
	                        <div class="clear"></div>
							<label >请输入您收到的验证码</label>
	                        <input type="text" name="captcha" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入验证码!" datatype="" errormsg="">
	                        <a href="javascript:;" data-phone-captcha="" ><span></span>获取验证码</a>
	                        <div class="clear"></div>
	                        <a href="javascript:;" class="b-button index-order-btn mt10" data-phone="">下一步</a>
	                        <a href="javascript:;" class="cancel_action">取消</a>
                        <%}else{%>
	                        <label >手机号码:</label>
	                        <input type="text" name="phone" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入验证码!" datatype="" errormsg="">
	                        <div class="clear"></div>
	                        <label >验证码:</label>
	                        <input type="text" name="captcha" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入验证码!" datatype="" errormsg="">
	                        <a href="javascript:;" data-insert-phone-verify-captcha="" ><span></span>获取验证码</a>
	                        <div class="clear"></div>
	                        <a href="javascript:;" class="b-button index-order-btn mt10" data-insert-phone="">保存修改</a>
	                        <a href="javascript:;" class="cancel_action">取消</a>
                        <%}%>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">邮箱</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span> 
                        <span data-lable=""><%=userLoginVO.getMail() %></span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                    	<% 
                    	if(!StringUtils.isEmpty(userLoginVO.getMail())){
                    	%>
	                        <label >原邮箱地址:<span name="phone"><%=userLoginVO.getMail() %></span></label>
	                        <div class="clear"></div>
							<label >请输入您收到的验证码</label>
	                        <input type="text" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" name="captcha" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入验证码!" datatype="" errormsg="">
	                        <a href="javascript:;" data-mail-captcha="" ><span></span>获取验证码</a>
	                        <div class="clear"></div>
	                        <a href="javascript:;" class="b-button index-order-btn mt10" data-mail="">下一步</a>
                        <%}else{ %>
                        	<label >邮箱地址:</label>
                        	<input type="text" name="mail" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入邮箱!" datatype="" errormsg="">
                        	<div class="clear"></div>
                        	<label >验证码:</label>
	                        <input type="text" name="captcha" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入验证码!" datatype="" errormsg="">
	                        <img alt="验证码" style="cursor: pointer;" src="/captcha.jpg" data-src="/captcha.jpg" />
	                        <div class="clear"></div>
	                        <a href="javascript:;" class="b-button index-order-btn mt10" data-mail-insert-auth-verify="">保存修改</a>
	                        <a href="javascript:;" class="cancel_action">取消</a>
                        <%}%>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">生日</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span> 
						<span data-lable="">${birthday}</span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                    	<input type="text" title="" id="birthday"class="bootstrapped-input input-text input-xlarge datetimepicker" size="30" maxlength="100" value="" readonly="readonly" nullmsg="请选择日期!" datatype="" errormsg="">
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-user="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">性别</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span> 
                        <span data-lable="">${sex}</span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                        <select name="sex" title="输入您的地址，我们即可自动输入您的资料以方便您更快速轻松地预订。" class="bootstrapped-input input-text input-xlarge" size="1" maxlength="100" nullmsg="请选性别!" datatype="" errormsg="">
                        	<option value="0">保密</option>
                        	<option value="1">先生</option>
                        	<option value="2">女士</option>
                        </select>
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-user="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <div class="setting-item">
                    <p class="settings_label">密码</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span> ******
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                    	<p class="inline_error firstname_error" ></p>
                    	<label >当前密码</label>
                        <input type="password" name="password" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请选当前密码!" datatype="" errormsg="">
                        <div class="clear"></div>
                        <label >新密码</label>
                        <input type="password" name="newPassword" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入新密码!" datatype="" errormsg="">
                        <div class="clear"></div>
                        <label >再次输入密码</label>
                        <input type="password" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="" nullmsg="请输入新密码!" datatype="" errormsg="">
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-password="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
            </div>
            <div class="user-profile-title">娱乐偏好</div>
            <div class="settings-panel-content">
            	<div class="setting-item">
            		<p class="inline_error firstname_error" ></p>
                    <p class="settings_label">首选分类</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span>
                        <span data-lable="">${userInterest.company_category_name }</span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                        <select id="category" name="company_category_id" title="输入您的地址，我们即可自动输入您的资料以方便您更快速轻松地预订。" class="bootstrapped-input input-text input-xlarge" size="1" maxlength="100" nullmsg="请选择分类!" datatype="" errormsg="">
                        	<option value="">请选择</option>
                        </select>
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-interest="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <div class="setting-item">
                	<p class="inline_error firstname_error" ></p>
                    <p class="settings_label">首选档次</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit">编辑</a>
                        </span>
                        <span data-lable="">${userInterest.company_grade_name }</span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                        <select id="grade" name="company_grade_id" title="输入您的地址，我们即可自动输入您的资料以方便您更快速轻松地预订。" class="bootstrapped-input input-text input-xlarge" size="1" maxlength="100" nullmsg="请选择档次!" datatype="" errormsg="">
                        	<option value="">请选择</option>
                        </select>
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-interest="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                <!-- 
                <div class="setting-item">
                    <p class="settings_label">首选设施</p>
                    <p class="settings_user_data ">
                        <span class="row_actions"><a href="javascript:;" class="add_or_edit" id="">编辑</a>
                        </span> 
                        <span data-lable=""></span>
                    </p>
                    <div class="settings_user edit_form " style="display: none;">
                        <div class="clear"></div>
                        <a href="javascript:;" class="b-button index-order-btn mt10" data-user="">保存修改</a>
                        <a href="javascript:;" class="cancel_action">取消</a>
                    </div>
                </div>
                 -->
            </div>
        </div>
    </div>
    <div class="footer">
        <%@ include file="/common/footer.jsp" %>
    </div>
    <div class="footer-other">
        <%@ include file="/common/footer-other.jsp" %>
    </div>
    <div class="right-sider-bar w1000">
    	<%@ include file="/common/right-sider-bar.jsp" %>
    </div>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/slides.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/ajax.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/modal.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/panes.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/tab.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/imagePreview.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/collapse.js"></script>
    <script src="http://static.yuleing.com/www/js/slides.min.jquery.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.config.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.area.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.captcha.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.interest.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/laydate/laydate.js"></script>
    <script type="text/javascript">
    area.province_value="${user.area_province_id}";
	area.city_value="${user.area_city_id}";
	interest.grade_value="${userInterest.company_grade_id}";
	interest.category_value="${userInterest.company_category_id}";
    $(function() {
    	var birthday_time = {
    		    elem: '#birthday',
    		    format: 'YYYY-MM-DD'
    		};
        laydate(birthday_time);
		$("a[data-login]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			var s = t.find("select[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			s.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/updateUserLogin.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				var text = "";
				i.each(function(){
					text += $(this).val()+" ";
				});
				s.each(function(){
					text += $(this).val()+" ";
				});
				t.parent().find("span[data-lable]").text(text);
				t.find(".cancel_action").click();
			}
		});
		
		$("a[data-user]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			var s = t.find("select[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			s.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/updateUser.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				var text = "";
				s.each(function(){
					text += $(this).find("option:selected").text()+" ";
				});
				i.each(function(){
					text += $(this).val()+" ";
				});
				t.parent().find("span[data-lable]").text(text);
				t.find(".cancel_action").click();
			}
		});
		
		$("a[data-interest]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			var s = t.find("select[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			s.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/updateUserInterest.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				var text = "";
				s.each(function(){
					text += $(this).find("option:selected").text()+" ";
				});
				i.each(function(){
					text += $(this).val()+" ";
				});
				t.parent().find("span[data-lable]").text(text);
				t.find(".cancel_action").click();
			}
		});
		
		$("a[data-password]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			var s = t.find("select[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			s.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/updatePassword.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				t.find(".cancel_action").click();
			}
		});
		
		$("a[data-phone-captcha]").click(function(){
			if(captcha.phone_auth_flag){
				var t = $(this);
				$.ajax({
					url:"/phoneCaptcha.do",
					type:"post",
					dataType:"json",
					//async:false,
					success:function(data){
						if(!data.status){
							window.location.reload();
						}else{
							captcha.phone_auth_flag = false;
							console.log(data.c);
							captcha.phone_auth_time = data.time;
							captcha.phone=t.find("span");
							captcha.phone_interval = setInterval("captcha.phoneAuth()",1000);
						}
					}
				});
			}
		});
		
		$("a[data-insert-phone-verify-captcha]").click(function(){
			if(captcha.phone_auth_flag){
				var t = $(this);
				$.ajax({
					url:"/phoneInsertAuth.do",
					type:"post",
					data:{"phone":t.parent().find("[name='phone']").val()},
					dataType:"json",
					//async:false,
					success:function(data){
						if(!data.status){
							t.parent().find(".firstname_error").text(data.message);
						}else{
							captcha.phone_auth_flag = false;
							console.log(data.c);
							captcha.phone_auth_time = data.time;
							captcha.phone=t.find("span");
							captcha.phone_interval = setInterval("captcha.phoneAuth()",1000);
						}
					}
				});
			}
		});
		
		$("a[data-insert-phone]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/phoneInsertAuthActivation.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				//var text = "";
				//i.each(function(){
				//	if($(this).attr("name")!="captcha"){
				//		text += $(this).val()+" ";
				//	}
				//});
				//t.parent().find("span[data-lable]").text(text);
				//t.find(".cancel_action").click();
				window.location.reload();
			}else{
				t.find(".firstname_error").text(dataFlag.message);
			}
		});
		
		//修改邮箱获取验证码
		$("a[data-mail-captcha]").click(function(){
			if(captcha.mail_auth_flag){
				var t = $(this);
				$.ajax({
					url:"/mailCaptcha.do",
					type:"post",
					dataType:"json",
					//async:false,
					success:function(data){
						if(!data.status){
							window.location.reload();
						}else{
							captcha.mail_auth_flag = false;
							console.log(data.c);
							captcha.mail_auth_time = data.time;
							captcha.mail=t.find("span");
							captcha.mail_interval = setInterval("captcha.mailAuth()",1000);
						}
						
					}
				});
			}
		});
		
		$("a[data-mail-phone]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			var dataFlag = {};
			$.ajax({
				url : "/phoneInsertAuth.do",
				type : "post",
				data:jsonData,
				dataType:"json",
				async:false,
				success : function(data){
					dataFlag = data;
				}
			});
			if(dataFlag.status){
				//var text = "";
				//i.each(function(){
				//	if($(this).attr("name")!="captcha"){
				//		text += $(this).val()+" ";
				//	}
				//});
				//t.parent().find("span[data-lable]").text(text);
				//t.find(".cancel_action").click();
				window.location.reload();
			}
		});
		
		
		var mailHtmls = '<p class=\"inline_error firstname_error\" ></p><label >您的新邮箱地址</label>'+
		'<input type="text" name="mail" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="">'+
		'<div class="clear"></div>'+
	    '<label >验证码</label>'+
	    '<input type="text" name="captcha" class="bootstrapped-input input-text input-xlarge" size="13" maxlength="100" value="">'+
	    '<img alt="验证码" style="cursor: pointer;" src="/captcha.jpg" data-src="/captcha.jpg" />'+
	    '<div class="clear"></div>'+
	    '<a href="javascript:;" class="b-button index-order-btn mt10" data-mail-auth="">保存修改</a>'+
	    '<a href="javascript:;" class="cancel_action">取消</a>';
	    //修改邮箱第一步
		$("a[data-mail]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			$.ajax({
				url:"/mailAuthCaptcha.do",
				type:"post",
				data:jsonData,
				dataType:"json",
				//async:false,
				success:function(data){
					if(!data.status){
						t.find(".firstname_error").text(data.message);
					}else{
						t.html(mailHtmls);
						clearInterval(captcha.mail_interval);
						captcha.mail_auth_flag = false;
					}
				}
			});
		});
		
		var mailSuccessHtmls = $('<label >预乐已经向您的新邮箱发送了一封验证邮件，请及时查收。验证完成后请刷新页面</label><a href="javascript:;" data-auth-mail-captcha="" ><span></span>获取验证码</a><a href="javascript:;" class="b-button index-order-btn mt10" >请前往邮箱按照邮件提示完成注册</a>');
		
		
		$("a[data-auth-mail-captcha]").live("click",function(){
			if(captcha.mail_auth_flag){
				var t = $(this);
				$.ajax({
					url:"/mailAuthVerifyCaptcha.do",
					type:"post",
					dataType:"json",
					//async:false,
					success:function(data){
						captcha.mail_auth_flag = false;
						console.log(data.c);
						captcha.mail_auth_time = data.time;
						captcha.mail=t.find("span");
						captcha.mail_interval = setInterval("captcha.mailAuth()",1000);
					}
				});
			}
		});
		
		$("a[data-mail-auth]").live("click",function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			$.ajax({
				url:"/mailAuthVerify.do",
				type:"post",
				data:jsonData,
				dataType:"json",
				//async:false,
				success:function(data){
					if(!data.status){
						t.find(".firstname_error").text(data.message);
					}else{
						console.log(data.c);
						mailCaptchaValue = t.find("input[name='mail']").val();
						t.html(mailSuccessHtmls);
						captcha.mail_auth_flag = false;
						captcha.mail_auth_time = data.time;
						captcha.mail=t.find("a[data-auth-mail-captcha]").find("span");
						captcha.mail_interval = setInterval("captcha.mailAuth()",1000);
					}
				}
			});
		});
		
		var insertMailSuccessHtmls = $('<label >预乐已经向您的新邮箱发送了一封验证邮件，请及时查收。验证完成后请刷新页面</label><a href="javascript:;" data-mail-insert-auth-captcha="" ><span></span>获取验证码</a><a href="javascript:;" class="b-button index-order-btn mt10" >请前往邮箱按照邮件提示完成注册</a>');
		
		$("a[data-mail-insert-auth-captcha]").live("click",function(){
			if(captcha.mail_auth_flag){
				captcha.mail_auth_flag = false;
				var t = $(this);
				$.ajax({
					url:"/mailInsertAuthCaptcha.do",
					type:"post",
					data:{"mail":mailCaptchaValue},
					dataType:"json",
					//async:false,
					success:function(data){
						console.log(data.c);
						captcha.mail_auth_time = data.time;
						captcha.mail=t.find("span");
						captcha.mail_interval = setInterval("captcha.mailAuth()",1000);
					}
				});
			}
		});
		
		$("a[data-mail-insert-auth-verify]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			$.ajax({
				url:"/mailInsertAuth.do",
				type:"post",
				data:jsonData,
				dataType:"json",
				//async:false,
				success:function(data){
					if(!data.status){
						t.find(".firstname_error").text(data.message);
						t.find("img").click();
					}else{
						console.log(data.c);
						mailCaptchaValue = t.find("input[name='mail']").val();
						t.html(insertMailSuccessHtmls);
						captcha.mail_auth_flag = false;
						captcha.mail_auth_time = data.time;
						captcha.mail=t.find("a[data-mail-insert-auth-captcha]").find("span");
						captcha.mail_interval = setInterval("captcha.mailAuth()",1000);
					}
				}
			});
		});
		
		//$("a[to-mail]").live('click',function(){
		//	var mail = account.substring(account.indexOf("@")+1);
		//	$("#mail_div").find(".index-order-btn").attr("href",user.mail_url[mail]);
		//});
		
		var phoneHtmls = '<p class=\"inline_error firstname_error\" ></p><label >您的新手机号</label>'+
		'<input type="text" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" name="phone" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="">'+
		'<a href="javascript:;" data-phone-auth="" ><span></span>获取验证码</a>'+
		'<label >请输入您收到的验证码</label>'+
		'<input type="text" title="输入您的姓名即可更快速地预订。您每一次预订时，我们将自动输入您的姓名。" name="captcha" class="bootstrapped-input input-text input-xlarge" size="30" maxlength="100" value="">'+
		'<div class="clear"></div>'+
		'<a href="javascript:;" class="b-button index-order-btn mt10" data-phone-auth-activation="">保存修改</a>'+
		'<a href="javascript:;" class="cancel_action">取消</a>';
		$("a[data-phone]").click(function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			$.ajax({
				url:"/phoneAuthCaptcha.do",
				type:"post",
				data:jsonData,
				dataType:"json",
				//async:false,
				success:function(data){
					if(!data.status){
						t.find(".firstname_error").text(data.message);
					}else{
						t.html(phoneHtmls);
						clearInterval(captcha.phone_interval);
						captcha.phone_auth_flag = true;
					}
				}
			});
		});
		$("a[data-phone-auth]").live("click",function(){
			if(captcha.phone_auth_flag){
				var t = $(this).parent();
				$.ajax({
					url:"/phoneAuthVerify.do",
					type:"post",
					data:{"phone":t.find("input[name='phone']").val()},
					dataType:"json",
					//async:false,
					success:function(data){
						if(!data.status){
							t.find(".firstname_error").text(data.message);
						}else{
							captcha.phone_auth_flag = false;
							console.log(data.c);
							captcha.phone_auth_time = data.time;
							captcha.phone=t.find("span");
							captcha.phone_interval = setInterval("captcha.phoneAuth()",1000);
						}
					}
				});
			}
		});
		
		$("a[data-phone-auth-activation]").live("click",function(){
			var jsonData = {};
			var t = $(this).parent();
			var i = t.find("input[name]");
			i.each(function(){
				jsonData[$(this).attr("name")] = $(this).val();
			});
			$.ajax({
				url:"/phoneAuthActivation.do",
				type:"post",
				data:jsonData,
				dataType:"json",
				//async:false,
				success:function(data){
					if(!data.status){
						t.find(".firstname_error").text(data.message);
					}else{
						window.location.reload();
						captcha.phone_auth_flag = true;
						cleanInterval(captcha.phone_interval);
					}
				}
			});
		});
		
    });
    </script>
    <script language="javascript" type="text/javascript">
	document.write("<script type='text/javascript' "
		+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date().getTime()
		+ "'></s" + "cript>");
	</script>
	<script type="text/javascript">
	var errorCodes = ["-100","-110","-120","-130"];
	var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(10MB)","零字节的文件","无效的文件类型"];
	var fileSystemName = "";	
	var fileTypeExts = "*.jpg;*.png;*.gif;*.jpeg";
	var flashPath = "/swf/uploadify.swf" ;
	var uploadifyPath = "http://upload.yuleing.com/userImage/upload.do";
	var uploadLimit = 0;
	var queueSizeLimit = 5;
	var fileSizeLimit = "2MB";
	var user_id='';
	var imageTitle = "点击查看原图"; 
	//页面初始化方法
	$(function(){
		user_id='${user.id}';
		$("input[data-upload]").uploadify({
	        'swf':flashPath,
	        'fileObjName':'files[]',
	        'uploader':'http://upload.yuleing.com/userImage/upload.do',
	        'auto': true,
	        'removeTimeout':0,
	        'multi': false, //多个文件
	        'uploadLimit':uploadLimit,
	        'fileSizeLimit':fileSizeLimit,
	        'fileTypeDesc' : '图片文件(*.jpg;*.png;*.gif;*.jpeg)',
	        'buttonText': '点击上传',
	        'fileTypeExts':fileTypeExts,
	        'progressData':'percentage',
	        'speed':'percentage',
	        'queueSizeLimit':queueSizeLimit,
	        'removeCompleted':true,
	        'onSelect': function (file) {
	        	this.queueData.filesErrored=0;
	        },
	        'onOpen':function(event,ID,fileObj){
	        	//alert(ID);
	        },
	        'onSelectError':function(file,errorCode,errorMsg){
	        	for(var i=0;i<errorCodes.length;i++){
	        		if(errorCodes[i]==errorCode){
	        			this.queueData.errorMsg = errorMsgs[i];
	        		}
	        	}
	        },
	        'onCancel':function(file){
	        	//alert(file.name);
	        },
	        'onFallback':function(){
	        	alert("浏览器不能兼容Flash,请下载最新版!");
	        },
	        'onClearQueue':function(queueItemCount) {
	        	alert("清除上传完成"+queueItemCount);
	        },
	        'onUploadStart':function(file){
	        	var name = this.wrapper.attr("id");
	        	var id =this.wrapper.selector;
	    		var formData ={"params":'{"upload_dir":"'+user_id+'","upload_name":"'+name+'","noinit":[{"name":"50_50","is_watermark":"false","keep":"false"},{"name":"100_100","is_watermark":"false","keep":"false"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'};
	    		$(id).uploadify("settings","formData",formData);
	        },
	        'onUploadSuccess':function(file,data,response){
	        	var temp=eval(data);
	        	var image_path = "http://images.yuleing.com/user/"+user_id+"/150_150"+this.wrapper.attr("id")+".jpg"+"?t="+ new Date().getTime();
	        	var id =this.wrapper.selector;
	        	$(id).uploadify('settings', 'buttonText', '正在加载');
	        	$(id+"_image").attr("src",image_path);
	        	$(id).uploadify('settings', 'buttonText', '重新上传');
	
	        },
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	        	//alert("这个文件: " + file.name + " 不能被上传原因: " + errorString);
	        	 switch (errorMsg){
	             case '400':
	                 $('#'+file.id).find('.data').html(" - 上传失败，文件超过大小限制(2MB)");
	                 break;
	             case '401':
	                 $('#'+file.id).find('.data').html(" - 上传失败，零字节的文件");
	                 break;
	             case '402':
	                 $('#'+file.id).find('.data').html(" - 上传失败，无效的文件类型");
	                 break;
	             case '500':
	                 $('#'+file.id).find('.data').html(" - 上传失败，服务器问题");
	                 break;
	         }
	    	},
	    	'onDialogClose':function(queueDat){
	    		this.queueData.files;
	    	}
	    });
	});
	</script>
</body>
</html>