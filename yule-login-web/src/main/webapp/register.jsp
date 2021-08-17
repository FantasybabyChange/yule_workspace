<%@page import="com.yule.constant.UserCookieConst"%>
<%@page import="com.yule.constant.DoMainConst"%>
<%@page import="com.yule.util.CookieUtil"%>
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
    <title>用户注册</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/yule.mailautocomplete.css">
    <%
		if(!StringUtils.isEmpty(CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME))){
			response.sendRedirect(DoMainConst.PC_URL);
			return;
		}
	%>
</head>
<body>
 	<div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix">
        <div class="pup-login-ui login-page">
            <div class="pane-wrapper pup-login-ui-wrapper">
                <div class="pane-wrapper-tab-item clearfix active">
                    <div class="alert alert-error" style="display: none;" id="register_message"></div>
                    <form action="/register.do" method="post" target="register" validform="">
			            <div class="pull-left user_access_form">
			            	<p class="user_access_form-title">注册您的帐户</p>
			                <div class="pane-wrapper-tab-list">
			                   	   电子邮箱或手机号
			                   	 <input type="text" class="input-text" name="account" placeholder="请输入邮箱或手机号" required autofocus autocomplete="off">
			                </div>
			                <div class="pane-wrapper-tab-list">
			                    	密码
			                    <input type="password" class="input-text" name="password" placeholder="请输入密码" required autofocus>
			                </div>
			                <div class="user_access_password_strength input-xlarge ">
	                           <div class="pass_strength_bar pass_strength_progress" ></div>
	                           <div class="pass_strength_bar pass_strength_steps">
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                               <div class="pass_strength_step"></div>
	                           </div>
	                       </div>
			                <div class="pane-wrapper-tab-list">
			                                                                      验证码
			                    <input type="text" class="input-text" name="captcha" placeholder="请输入验证码" required autofocus>
			                    <img alt="验证码" title="验证码" style="cursor: pointer;" src="/captcha.jpg" data-src="/captcha.jpg" />
			                </div>
			                <br/>
			                <a href="javascript:;" class="b-button index-order-btn">创建我的账户</a>
			                <div class="user_access_section_trigger_link">
			                                                                      拥有帐户表示您同意本公司的<a href="javascript:;">条件和条款</a>以及<a href="javascript:;">隐私声明</a>
			                </div>
			            </div>
		            </form>
                    <div class="form-subsection">
                        <div class="form-usp-block">
                            <p class="form-header-p">快捷、免费又安全！</p>
                            <ul class="user_access_menu_usps">
                                <li>修改预订</li>
                                <li>个性化设置您的优惠</li>
                                <li>更快速地预订</li>
                            </ul>
                        </div>
                    </div>
                    <iframe src="about:blank" name="register" id="register" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
                </div>
                <div class="pane-wrapper-tab-item clearfix" id="phone_div">
		            <div class="alert alert-error" style="display: none;" id="register_phone_auth_message"></div>
		            <form action="/registerPhoneAuthActivation.do" method="post" target="register_phone_auth">
			            <div class="pull-left user_access_form">
			            	<p class="user_access_form-title">账户认证</p>
			                <div class="pane-wrapper-tab-list">
								 验证码已发送至<span id="account"></span>,请在24小时内完成验证。
			                   	 <input type="text" class="input-text" name="captcha" >
			                </div>
			                <a href="javascript:;" class="b-button index-order-btn" >完成注册</a>
			            </div>
		            </form>
		            <div class="form-subsection">
		                <div class="form-usp-block">
		                    <p class="form-header-p">没有收到验证码？您可以</p>
		                    <a href="javascript:;" data-captcha="" ><span id="phoneCount"></span>重新获取验证码</a>
		                </div>
		            </div>
		            <iframe src="about:blank" name="register_phone_auth" id="register_phone_auth" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
		            <form action="/phoneCaptcha.do" method="post" target="phone_captcha"></form>
		            <iframe src="about:blank" name="phone_captcha" id="phone_captcha" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
		        </div>
		        
		        <div class="pane-wrapper-tab-item clearfix" id="mail_div">
			           <div class="pull-left user_access_form">
			           	<p class="user_access_form-title">账户认证</p>
			               <div class="pane-wrapper-tab-list">
							验证邮件已发送至<span id="account"></span>,，请查收并按邮件指示完成注册。
			               </div>
			               <a href="javascript:;" class="b-button index-order-btn" target="_blank" data-to-mail="">去邮箱验证</a>
			           </div>
		            <div class="form-subsection">
		                <div class="form-usp-block">
		                    <p class="form-header-p">没有收到验证码？您可以</p>
		                    <a href="javascript:;" data-captcha="" ><span id="mailCount"></span>重新获取验证码</a>
		                </div>
		            </div>
		            <form action="/mailCaptcha.do" method="post" target="mail_captcha"></form>
		            <iframe src="about:blank" name="mail_captcha" id="mail_captcha" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
		        </div>
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
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/www/login/js/yule.jquery.captcha.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/www/login/js/yule.jquery.user.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/www/js/yule.jquery.passStrength.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/www/js/yule.jquery.mailautocomplete.js"></script>
<script type="text/javascript">
document.domain = 'yuleing.com';
$(function() {
    $("input[name='account']").mailAutoComplete();
    $("#mail_div").find("a[data-captcha]").click(function(){
    	if(captcha.mail_auth_flag){
    		captcha.mail_auth_flag = false;
    		$("form[target='mail_captcha']").html("<input type=\"hidden\" name=\"account\" value=\""+user.account+"\">");
    		$("form[target='mail_captcha']").submit();
    	}
    });
    $("#phone_div").find("a[data-captcha]").click(function(){
    	if(captcha.phone_auth_flag){
    		captcha.phone_auth_flag = false;
    		$("form[target='phone_captcha']").html("<input type=\"hidden\" name=\"account\" value=\""+user.account+"\">");
    		$("form[target='phone_captcha']").submit();
    	}
    });
    $("form[validform]").submit(function(){
    	var flag = true;
    	var account = $("input:text[name='account']");
    	if(account===null||account===""){
    		$("register_message").text("请输入帐号!");
    		flag = false;
    	}
    	return flag;
    });
    
    user.register_form.find("input[name='password']").keyup(function(){
    	passStrength.verify($(this).val());
    });
    
	$("img[data-src]").live("click",function(){
		$(this).attr("src",$(this).attr("data-src")+"?time="+new Date().getTime());
	});
	
	user.register_form.find(".index-order-btn").click(function(){
		user.register_form.submit();
    });
    
    $("form[target='register_phone_auth']").find(".index-order-btn").click(function(){
    	$("form[target='register_phone_auth']").submit();
    });
    
});
</script>
</body>
</html>