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
					<h3>用户登录信息</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
					    <form action="/userLogin/updateUserLogin.do" method="post" validform="">
							<fieldset><input type="hidden" name="id" value="${htmls.id }"  />
								<p>
								  	<label>名称</label>								
								    <input class="text-input small-input" type="text" id="small-input" name="name" value="${htmls.name }" nullmsg="请输入名称!" datatype="s6-18" errormsg=""/>
								</p>
								<p>
									<label>密码</label>
									<input class="text-input small-input" type="password" id="small-input" name="password" value="${htmls.password }" nullmsg="请输入密码!" datatype="s6-18" errormsg=""/>
								</p>
								<p>
									<label>手机</label>
									<input class="text-input small-input" type="text" id="small-input" name="phone" value="${htmls.phone }"  nullmsg="请输入手机号!" datatype="p" errormsg=""/>
								</p>
								<p>
									<label>邮箱</label>
									<input class="text-input small-input" type="text" id="small-input" name="mail" value="${htmls.mail }" nullmsg="请输入邮箱!" datatype="e" errormsg="" />
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