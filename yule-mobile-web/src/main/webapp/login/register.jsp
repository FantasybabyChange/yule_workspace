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
							<a class="mui-control-item" href="/login/login.jsp">登录</a>
							<a class="mui-control-item mui-active" href="/login/register.jsp">注册</a>
						</div>
						<div class="mui-login-content">
							<label id="register_message"></label>
							<form action="http://login.yuleing.com/register.do" method="post" target="register" validform="">
								<label>邮箱或手机号</label>
								<input type="text" class="mui-input-style" placeholder="请输入邮箱或手机号" name="account" />
								<label>密码 <a href="javascript:;" class="mui-get-passwd">忘记密码？</a>
								</label>
								<input type="password" class="mui-input-style" placeholder="请输入密码" name="password" />
								<div class="mui-code">
									<label>验证码</label>
									<input type="text" class="mui-input-style pull-left" placeholder="请输入验证码" style="width: 50%;" name="captcha" />
									<img alt="验证码" class="pull-left" style="margin-top: 10px;" title="验证码" style="cursor: pointer;" src="http://login.yuleing.com/captcha.jpg" data-src="http://login.yuleing.com/captcha.jpg">
								</div>
								<div class="mui-text-center mui-login-btn-wapper">
									<button class="mui-btn mui-btn-search ">创建我的账户</button>
									<p>已有帐户？<a href="/login/login.jsp">点击登录？</a></p>
								</div>
							</form>
						</div>
						<iframe src="about:blank" name="register" id="register" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
					</div>
					
					<div class="mui-login-wapper" id="phone_div" style="display:none">
						<div class="mui-login-content">
			            <form action="http://login.yuleing.com/registerPhoneAuthActivation.do" method="post" target="register_phone_auth">
							 验证码已发送至<span id="account"></span>,请在24小时内完成验证。
							<input type="text" class="mui-input-style pull-left" placeholder="请输入验证码" style="width: 50%;" name="captcha" />
				            <a href="javascript:;" class="b-button index-order-btn">完成注册</a>
			            </form>
			            </div>
			            <div>
		                    <p>没有收到验证码？您可以</p>
		                    <a href="javascript:;" data-captcha="" ><span id="phoneCount"></span>重新获取验证码</a>
			            </div>
			            <iframe src="about:blank" name="register_phone_auth" id="register_phone_auth" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
			            <form action="http://login.yuleing.com/phoneCaptcha.do" method="post" target="phone_captcha"></form>
			            <iframe src="about:blank" name="phone_captcha" id="phone_captcha" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
			        </div>
			        
			        <div class="mui-login-wapper" id="mail_div" style="display:none">
			           <div>
							验证邮件已发送至<span id="account"></span>,请查收并按邮件指示完成注册。
			               <a href="javascript:;" class="b-button index-order-btn" >请前往邮箱按照邮件提示完成注册</a>
			           </div>
			            <div >
		                    <p >没有收到验证码？您可以</p>
		                    <a href="javascript:;" data-captcha="" ><span id="mailCount"></span>重新获取验证码</a>
			            </div>
			            <form action="http://login.yuleing.com/mailCaptcha.do" method="post" target="mail_captcha"></form>
			            <iframe src="about:blank" name="mail_captcha" id="mail_captcha" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
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
		<script type="text/javascript" src="/yule.jquery.captcha.js"></script>
		<script type="text/javascript" src="/yule.jquery.user.js"></script>
		<!-- <script type="text/javascript" src="/yule.jquery.passStrength.js"></script> -->
		<script type="text/javascript" charset="utf-8">
			document.domain = 'yuleing.com';
			mui.init();
		</script>
	</body>
</html>