<%@page import="com.yule.vo.UserLoginVO"%>
<%@page import="com.yule.redis.constant.RedisConst"%>
<%@page import="com.yule.util.YuLeEncryptUtil"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.yule.redis.util.JedisUtil"%>
<%@page import="com.yule.constant.UserCookieConst"%>
<%@page import="com.yule.constant.DoMainConst"%>
<%@page import="com.yule.util.CookieUtil"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.yule.constant.CookieTimeConst"%>
<%@page import="com.yule.constant.TimeConst"%>
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
    <title>用户登录</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/yule.mailautocomplete.css">
</head>

<body>
	<%	
		UserLoginVO userLoginVO = null;
		String cookie = CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME);
		if(!StringUtils.isEmpty(cookie)){
			String timeout = CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_TIMEOUT_COOKIE_NAME);
			if(!StringUtils.isEmpty(timeout)) {
				if((System.currentTimeMillis()-Long.valueOf(timeout))<TimeConst.HALF_HOUR) {
					response.sendRedirect(DoMainConst.USER_URL+"/index.do");
				}
			}
			String userId = YuLeEncryptUtil.decode(cookie);
			String key = RedisConst.USER + userId;
			userLoginVO = (UserLoginVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),UserLoginVO.class);
		}
		
	%>
 	<div id="header-full">
 		<%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix">
        <div class="pup-login-ui login-page">
            <div class="pane-wrapper pup-login-ui-wrapper">
                <div class="pane-wrapper-tab-item clearfix active">
                    <div class="alert alert-error" style="display: none;" id="login_message"></div>
                    <form action="/login.do" method="post" target="login">
                    <div class="pull-left user_access_form">
                    	<%
                    		if(null!=userLoginVO){	 
                    	%>
                        <div class="pane-wrapper-tab-list">
                            <img src="http://static.yuleing.com/www/images/top-avatar.png" data-img="<%=userLoginVO.getId() %>" class="mr10 pull-left" >
                            <h2 class="pane-wrapper-username"><%=userLoginVO.getName() %></h2>
                            <a href="javascript:void(0)" class="change-user-login">不是本人?</a>
                        </div>
                        <% }else{ %>
                        <div class="pane-wrapper-tab-list">
                            <label>电子邮箱地址</label>
                            <input type="text" class="input-text" name="account" placeholder="请输入帐号" required autofocus>
                        </div>
                        <%} %>
                        <div class="pane-wrapper-tab-list">
                            <label>密码 <a href="/findForgetPassword.do" target="_blank" class="forget-pass">忘记密码</a>
                            </label>
                            <input type="password" class="input-text" name="password" placeholder="请输入密码" required autofocus>
                        </div>
                        <div class="pane-wrapper-tab-list" id="login_captcha" style="display:none">
		                </div>
		                <br/>
                        <a href="javascript:;" class="b-button index-order-btn">登录</a>
                        <div class="user_access_section_trigger_link">
                            还没有帐户？<a href="/register.jsp" data-target="user_access_signup_menu" class="user_access_section_trigger">点击注册</a>
                        </div>
                    </div>
                    </form>
                    <div class="form-subsection">
		                <div class="form-usp-block">
		                    <p class="form-header-p">登录即可：</p>
		                    <ul class="user_access_menu_usps">
		                        <li>更改当前预订</li>
		                        <li>查阅您所有的确认信息</li>
		                        <li>获取个性化的优惠信息</li>
		                    </ul>
		                </div>
		            </div>
		            <iframe src="about:blank" name="login" id="login" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
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
    <script type="text/javascript" src="http://static.yuleing.com/www/login/js/yule.jquery.user.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/www/js/yule.jquery.mailautocomplete.js"></script>
    <script type="text/javascript">
    	document.domain = 'yuleing.com';
    	var backurl = "<%=request.getParameter("backurl")%>";
    	$(function(){
    		 $("input[name='account']").mailAutoComplete();
    		 $('.change-user-login').click(function(event) {
   		        $('#userLogin').hide();
   		        $('#userInput').show();
   		        $.ajax({
   		        	url:"/ajaxLogout.do",
   		        	type:"post",
   		        	success:function(data){}
   		        });
   		        $(this).parent().html('<label>电子邮箱地址</label><input type="text" class="input-text" name="account" placeholder="请输入帐号" required autofocus>');
   		     });
   			$("img[data-src]").live("click",function(){
   				$(this).attr("src",$(this).attr("data-src")+"?time="+new Date().getTime());
   			});
   		    $("form[target='login']").find(".index-order-btn").click(function(){
   		    	$("form[target='login']").submit();
   		    });
    	});
    </script>
</body>
</html>