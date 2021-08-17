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
					<h3>企业编辑</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<form action="/companyLogin/updateCompanyLogin.do" method="post" validform="">
							<fieldset>
							    <input type="hidden" name="id" value="${id }"  />
								<p>
								  <label>新密码:</label> 
								  <input class="text-input small-input" type="password" id="small-input" name="password" nullmsg="请输入密码!" datatype="" errormsg="" />
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
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
</body>
</html>