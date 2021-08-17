<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>企业管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<table>
							<thead>
								<tr>
									<th><input class="check-all" type="checkbox" /></th>
									<th>企业编号</th>									
									<th>用户名</th>
									<th>上次登录时间</th>
									<th>创建时间</th>
									<th style="width:30%">操作</th>
								</tr>
							</thead>
							${htmls}
						</table>
					</div>
					<div class="tab-content" id="tab2" style="display: none;">
						<form id="insertForm" action="/companyLogin/insertCompanyLogin.do" method="post" validform="">
							<fieldset>
								<p>
									<label>用户名:</label> <input class="text-input small-input"
										type="text" id="small-input" name="account" nullmsg="请输入用户名!" datatype="" errormsg="" /> 
								</p>
								<p>
									<label>密码:</label> <input class="text-input small-input"
										type="password" id="small-input" name="password" nullmsg="请输入密码!" datatype="" errormsg="" /> 
								</p>
								<p>
								<label>企业分类:</label>${categoryHtml}
								</p>
								<p>
									<input class="button" type="submit" value="新增" />
								</p>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
</html>