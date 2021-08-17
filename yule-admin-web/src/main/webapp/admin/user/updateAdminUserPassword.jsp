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
					<h3>修改用户密码</h3>
				</div>
				<div class="content-box-content">
					<form action="/adminUser/updateAdminUserPassword.do" method="post" validform="">
						<fieldset>
							<p>
								<label>原始密码:</label> <input class="text-input small-input"
									type="password" id="small-input" name="password" nullmsg="请输入原始密码!" datatype="" errormsg="" />
							</p>
							<p>
								<label>新密码:</label> <input class="text-input small-input"
									type="password" id="small-input" name="newPassword" nullmsg="请输入新密码!" datatype="" errormsg="" dataequal="password" dataequalmsg="密码不一致!"/>
							</p>
							<p>
								<label>请再次输入新密码:</label> <input class="text-input small-input"
									type="password" id="small-input" nullmsg="请输入新密码!" datatype="" errormsg="" dataequal="password" dataequalmsg="密码不一致!" />
							</p>
							<p>
								<input class="button" type="submit" value="更新" />
							</p>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>