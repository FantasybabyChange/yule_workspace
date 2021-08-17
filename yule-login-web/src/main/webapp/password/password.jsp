<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>重置密码</title>
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
            	<div class="alert alert-error" style="display: none;" id="message"></div>
            	<form id="password">
                <div class="pane-wrapper-tab-item clearfix active">
                        <div class="pane-wrapper-tab-list">
                            <label>新密码</label>
                            <input type="password" name="password" class="input-text" />
                        </div>
                        <div class="pane-wrapper-tab-list">
                            <label>重新输入新密码</label>
                           <input type="password" class="input-text" />
                        </div>
                        <a href="javascript:;" class="b-button index-order-btn m10" data-submit="">完成</a>
                </div>
                </form>
                <div class="pane-wrapper-tab-item clearfix" id="mail" >
						验证发已发送到邮箱 <a href="javascript:;" data-to-mail="">前往邮箱</a>
							<a href="javascript:;" data-mail-captcha=""><span></span>重新获取验证码</a>
                </div>
                <div class="pane-wrapper-tab-item clearfix" id="phone">
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
    <script type="text/javascript">
    $(function(){
   		$("a[data-submit]").click(function(){
   			$.ajax({
   				url:"/updatePassword.do",
   				type:"post",
   				data:$("#password").serializeArray(),
   				dataType:"json",
   				//async:false,
   				success:function(data){
   					if(!data.status){
   						$("#message").text(data.message);
   					}else{
   						window.location.href="http://www.yuleing.com/";
   					}
   				}
   			});
   		});
    });
    </script>
</body>
</html>