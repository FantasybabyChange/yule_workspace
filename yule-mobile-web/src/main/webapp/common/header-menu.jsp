<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.yule.constant.UserCookieConst"%>
<%@page import="com.yule.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
	<li class="mui-table-view-cell mui-back-home">
		<a href="/index.do">
			<span class="mui-icon mui-icon-home mui-pull-right"></span>首页
		</a>
	</li>
</ul>
<div class="mui-slider-menu">账户资料</div>
<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
	<% String cookie =  CookieUtil.getCookieValue(request.getCookies(), UserCookieConst.USER_COOKIE_NAME); %>
	<%if(!StringUtils.isEmpty(cookie)){ %>
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-list"></span>管理我的预订
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-trash"></span>最近浏览历史
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-star"></span>我的收藏
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a href="http://login.yuleing.com/logout.do?redirect_url=http://m.yuleing.com" class="mui-navigate-right">
			<span class="mui-icon mui-icon-list"></span>退出
		</a>
	</li>
	<%}else{ %>
	<li class="mui-table-view-cell">
		<a href="/login/login.jsp" class="mui-navigate-right">
			<span class="mui-icon mui-icon-contact"></span>登录
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a href="/login/register.jsp" class="mui-navigate-right">
			<span class="mui-icon mui-icon-contact"></span>注册
		</a>
	</li>
	<%} %>
</ul>
<div class="mui-slider-menu">实用链接</div>
<ul class="mui-table-view mui-table-view-chevron mui-table-view-inverted">
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-phone"></span>客服服务
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-chatboxes"></span>网站反馈
		</a>
	</li>
	<li class="mui-table-view-cell">
		<a class="mui-navigate-right">
			<span class="mui-icon mui-icon-help"></span>常见问题
		</a>
	</li>

</ul>