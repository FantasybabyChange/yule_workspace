<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
					<h3>用户</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">用户列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
							<form action="/adminUser/findAdminUser.do" method="get" data-query-form="">
							</form>
						</div>
						<table>
							<thead>
								<tr>
									<th>用户名</th>
									<th>登陆时间</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							${htmls}
						</table>
					</div>

					<div class="tab-content" id="tab2" style="display: none;">
						<form action="/adminUser/insertAdminUser.do" method="post" ajaxvalidform="" data-url="/adminUser/verifyAdminUser.do">
							<fieldset>
								<p>
									<label>用户名:</label> <input class="text-input small-input"
										type="text" id="small-input" name="account" nullmsg="请输入用户名!" datatype="" errormsg="" ajaxdata="" /> 
								</p>
								<p>
									<label>密码:</label> <input class="text-input small-input"
										type="password" id="small-input" name="password" nullmsg="请输入密码!" datatype="" errormsg="" /> 
								</p>
								<p>
								   <label>角色选择:</label>
								   ${roleHtmls }
								</p>
								<p>
									<label>请选择状态</label> 
									${statusHtml}
								</p>
								<p>
									<input class="button" type="submit" value="添加" />
								</p>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
</body>
</html>