<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>重置密码方式</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
</head>

<body>
 	<div id="header-full">
 		<%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix">
        <div class="pup-login-ui login-page">
           <div class="find-pwd-title">尊敬的用户${userLoginInfoVO.name}</div>
            <div class="pup-login-ui-wrapper" >
                <div class="pane-wrapper-tab-item clearfix active">
                	<div class="alert alert-error" style="display: none;" id="captcha_message"></div>
						请选择重置密码方式 : 
                        <div class="pane-wrapper-tab-list">
                            <label>电子邮箱</label>
                            <a href="javascript:;" data-mail="">通过邮箱重置密码</a>
                        </div>
                        <div class="pane-wrapper-tab-list">
                            <label>手机</label>
                           <a href="javascript:;" data-phone="">通过手机重置密码</a>
                        </div>
                </div>
                <div class="pane-wrapper-tab-item clearfix" id="mail" >
						验证发已发送到邮箱 <a href="javascript:;" >请前往邮箱按照邮件提示完成注册</a>
							<a href="javascript:;" data-mail-captcha=""><span></span>重新获取验证码</a>
                </div>
                <div class="pane-wrapper-tab-item clearfix" id="phone">
                		<div class="alert alert-error" style="display: none;" id="message"></div>
						验证码:<input type="text" name="captcha" >
						<a href="javascript:;" data-phone-captcha=""><span></span>重新获取验证码</a>
						<a href="javascript:;" data-phone-submit="">提交</a>
						<form action="/phoneUpdatePassword.do" id="password" method="post"></form>
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
	<script type="text/javascript" src="http://static.yuleing.com/www/header/js/yule.jquery.captcha.js"></script>
    <script type="text/javascript">
    var mailAuth = "${userLoginInfoVO.mail_auth}";
    var phoneAuth = "${userLoginInfoVO.phone_auth}";
    $(function(){
    	var mail = "${userLoginInfoVO.mail}";
    	$("a[data-mail]").click(function(){
    		if(null!=mail&&""!=mail&&mailAuth==="0"){
	    		$.ajax({
	    			url:"/mailForgetPasswordCaptcha.do",
	    			type:"post",
	    			dataType:"json",
	    			//async:false,
	    			success:function(data){
	    				$("#mail").show();
	    				console.log(data.c);
	    				captcha.mail_auth_time = data.time;
	    				captcha.mail=$("#mail").find("a[data-mail-captcha]").find("span");
	    				captcha.mailAuth();
	    			}
	    		});
    		}else{
    			$("#captcha_message").text("您的邮箱尚未完善!");
    		}
    	});
    	var phone = "${userLoginInfoVO.phone}";
    	$("a[data-phone]").click(function(){
    		if(null!=phone&&""!=phone&&phoneAuth==="0"){
	    		$.ajax({
	    			url:"/phoneForgetPasswordCaptcha.do",
	    			type:"post",
	    			dataType:"json",
	    			//async:false,
	    			success:function(data){
	    				$("#phone").show();
	    				console.log(data.c);
	    				captcha.phone_auth_time = data.time;
	    				captcha.phone=$("#phone").find("a[data-phone-captcha]").find("span");
	    				captcha.phoneAuth();
	    			}
	    		});
    		}else{
    			$("#captcha_message").text("您的手机尚未完善!");
    		}
    	});
    	$("a[data-mail-captcha]").click(function(){
    		if(captcha.mail_auth_flag){
    			var t = $(this);
    			$.ajax({
    				url:"/mailForgetPasswordCaptcha.do",
    				type:"post",
    				dataType:"json",
    				//async:false,
    				success:function(data){
    					captcha.mail_auth_flag = false;
    					console.log(data.c);
    					captcha.mail_auth_time = data.time;
    					captcha.mail=t.find("span");
    					captcha.mailAuth();
    				}
    			});
    		}
    	});
    	$("a[data-phone-captcha]").click(function(){
    		if(captcha.phone_auth_flag){
    			var t = $(this);
    			$.ajax({
    				url:"/phoneForgetPasswordCaptcha.do",
    				type:"post",
    				dataType:"json",
    				//async:false,
    				success:function(data){
    					captcha.phone_auth_flag = false;
    					console.log(data.c);
    					captcha.phone_auth_time = data.time;
    					captcha.phone=t.find("span");
    					captcha.phoneAuth();
    				}
    			});
    		}
    	});
    	$("a[data-phone-submit]").click(function(){
    		var t = $(this);
    		$.ajax({
    			url:"/phoneForgetPasswordActivation.do",
    			type:"post",
    			data:{"captcha":t.parent().find("input[name='captcha']").val()},
    			dataType:"json",
    			//async:false,
    			success:function(data){
    				if(!data.status){
    					$("#message").text(data.message);
    				}else{
    					$("#password").submit();
    				}
    			}
    		});
    	});
    });
    </script>
</body>
</html>