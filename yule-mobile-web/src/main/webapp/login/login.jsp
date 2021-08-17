<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="aplus-terminal" content="1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no,minimal-ui" />
		<meta name="keywords" content="夜店预订,特价夜店,夜店查询,夜总会预订,特价夜总会,夜总会查询查询,酒吧预订,特价酒吧,酒吧查询,娱乐会所预订,特价娱乐会所,娱乐会所查询,演艺会所预订,特价演艺会所,演艺会所查询" />
		<meta name="description" content="预乐进行时是中国领先的O2O娱乐休闲预订公司,夜店点评及特价夜店查询,娱乐会所预订,酒吧预订,演艺会所预订,为您提供全方位娱乐休闲场所预订咨询服务"/>
		<link rel="dns-prefetch" href="//static.yuleing.com">
		<link rel="dns-prefetch" href="//images.yuleing.com">
		<link rel="dns-prefetch" href="//p.yuleing.com">
		<title>预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
		<meta name="apple-touch-fullscreen" content="no"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
		<meta name="format-detection" content="telephone=no"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
		<meta name="aplus-terminal" content="1">
		<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="http://images.yuleing.com/logo/logo.png">
		<link rel="stylesheet" href="/css/mui.css" />
		<link rel="stylesheet" href="/css/yuleing.css" />
		<link rel="stylesheet" href="/css/mobiscrol.css" />
	</head>

	<body>
		<div class="mui-off-canvas-wrap mui-draggable">
			<aside id="offCanvas" class="mui-off-canvas-right">
				<%@ include file="/common/header-menu.jsp" %>
			</aside>
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav">
					<%@ include file="/common/header-nav.jsp" %>
				</header>
				<div id="mui-popover" class="mui-popover mui-search-popover">
					<%@ include file="/common/header-search.jsp" %>
				</div>
				<div class="mui-content">
					<div class="mui-login-wapper">
						<div id="segmentedControl" class="mui-segmented-control mui-list-tab">
							<a class="mui-control-item mui-active" href="/login/login.jsp">登录</a>
							<a class="mui-control-item" href="/login/register.jsp">注册</a>
						</div>
						<div class="mui-login-content">
							<label id="login_message"></label>
							<form action="http://login.yuleing.com/login.do" method="post" target="login">
								<label>电子邮箱或手机号</label>
								<input type="text" class="mui-input-style" placeholder="请输入邮箱或手机号" name="account" />
								<label>密码 <a href="#" class="mui-get-passwd">忘记密码？</a></label>
								<input type="password" class="mui-input-style" placeholder="请输入密码" name="password" />
								<div class="mui-text-center mui-login-btn-wapper">
									<button class="mui-btn mui-btn-search">登录</button>
									<p>还没有帐户吗？<a href="/login/register.jsp">立即注册吧</a></p>
								</div>
							</form>
						</div>
						<iframe src="about:blank" name="login" id="login" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
					</div>
					<footer>
						<%@ include file="/common/footer.jsp" %>
					</footer>
					<!-- off-canvas backdrop -->
					<div class="mui-off-canvas-backdrop"></div>
				</div>
			</div>
		</div>
		<form id="redirect" action="/index.do" ></form>
	    <script type="text/javascript" src="/js/jquery.js"></script>
		<script type="text/javascript" src="/js/mobiscroll.js"></script>
		<script type="text/javascript" src="/js/mui.js"></script>
		<script type="text/javascript" src="/yule.jquery.user.js"/></script>
		<script type="text/javascript" charset="utf-8">
			document.domain = 'yuleing.com';
			mui.init();
		</script>
	</body>
</html>