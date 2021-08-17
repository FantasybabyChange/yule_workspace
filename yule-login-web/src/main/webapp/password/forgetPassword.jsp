<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>找回密码</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/yule.mailautocomplete.css">
</head>

<body>
 	<div id="header-full">
 		<%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix">
        <div class="pup-login-ui login-page">
           <div class="find-pwd-title">找回密码</div>
            <div class="pup-login-ui-wrapper" >
            	<div class="alert alert-error" style="display: none;" id="message"></div>
            	<form id="verify">
                <div class="pane-wrapper-tab-item clearfix active">
                        <div class="pane-wrapper-tab-list">
                            <label>电子邮箱地址/手机号</label>
                            <input type="text" class="input-text" name="account">
                        </div>
                        <div class="pane-wrapper-tab-list">
                            <label>验证码</label>
                            <input type="text" class="input-text w100" name="captcha" >
                            <img alt="" src="/captcha.jpg" data-src="/captcha.jpg">
                            <span class="reCode">看不清？<a href="javascript:;" >换一张</a></span>
                        </div>
                        <a href="javascript:;" class="b-button index-order-btn m10">提交</a>
                </div>
				</form>
				<form action="/findForgetPasswordCaptcha.do" id="captcha" method="get"></form>
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
    <script type="text/javascript" src="http://static.yuleing.com/www/login/js/yule.jquery.user.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/www/js/yule.jquery.mailautocomplete.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("input[name='account']").mailAutoComplete();
    	var form = $("#verify");
    	form.find(".index-order-btn").click(function(){
    		$.ajax({
    			url:"/forgetPassword.do",
    			type:"post",
    			data:$("#verify").serializeArray(),
    			dataType:"json",
    			//async:false,
    			success:function(data){
    				if(!data.status){
    					form.find("img[data-src]").click();
    					$("#message").text(data.message);
    					$("#message").show();
    				}else{
    					$("#captcha").submit();
    				}
    			}
    		})
    	});
    	$("img[data-src]").click(function(){
    		$(this).attr("src",$(this).attr("data-src")+"?time="+new Date().getTime());
    	});
    });
    </script>
</body>
</html>