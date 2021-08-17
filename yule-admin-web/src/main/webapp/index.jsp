<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="renderer" content="webkit">
<!-- IE=10;IE=9;IE=8;IE=7;IE=EDGE -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="dns-prefetch" href="//static.yuleing.com">
<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
<title>预乐网-后台管理</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div class="ui-layout-center">
		<iframe id="mainFrame" name="mainFrame" class="ui-layout-center"
			width="100%" height="600" frameborder="0" scrolling="auto"
			src="${mainPath}"></iframe>
		<!-- <iframe id="topFrame" name="topFrame" class="ui-layout-north"
			width="100%" frameborder="0" height="260" scrolling="no"
			src="${basePath}/common/top.jsp"></iframe> -->
		<div class="ui-layout-south">
			<%@ include file="/common/foot.jsp" %>
		</div>
		<!-- 
		<iframe id="footFrame" name="footFrame" 
			width="100%" frameborder="0" height="260" scrolling="no"
			src="${footPath}"></iframe>
			 -->
	</div>
	<div class="ui-layout-west">
		<div id="sidebar">
			<div id="sidebar-wrapper">
				<h1 id="sidebar-title">
					<a href="javascript:;">${adminUser.account }</a>
				</h1>
				<a href="${mainPath}" target="mainFrame"> <img
					id="logo" src="http://static.yuleing.com/admin/images/logo.png" alt="${adminUser.account }" />
				</a>
				<div id="profile-links">
					您好, <a href="javascript:;" title="Edit your profile">${adminUser.account }</a>, 您有 <a
						href="/adminMessage/findAdminMessageNotRead.do" target="mainFrame" title="${adminMessageNotRead } 条信息">${adminMessageNotRead } 条新信息</a> <br />
					<br /> <a href="http://www.yuleing.com" target="_blank" title="查看网站">查看网站</a> | <a href="/adminUser/findAdminUserPassword.do" target="mainFrame" title="修改密码">修改密码</a> | <a
						href="/logout.do" title="退出">退出</a>
				</div>
				<ul id="main-nav">
					${adminPrivilegeHtml}
				</ul>
				<div id="messages" style="display: none"></div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/jquery.layout-latest.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript">
	var layoutOptions = {
		applyDemoStyles : false,
		center__childOptions : {
			applyDemoStyles : false,
			north : {
				size : 250,
			},
			south : {
				size : 50,
			},
			panes : {
				closable : false,

				spacing_open : 0,
				spacing_closed : 0
			}
		},
		west : {
			size : 236,
		},
		panes : {
			closable : false,
			spacing_open : 0,
			spacing_closed : 0
		}
	};

	$(document).ready(function() {
		$('body').layout(layoutOptions);
	});
</script>
</body>
</html>