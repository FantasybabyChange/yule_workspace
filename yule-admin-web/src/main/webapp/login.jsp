<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"/>
<meta name="renderer" content="webkit">
<!-- IE=10;IE=9;IE=8;IE=7;IE=EDGE -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="dns-prefetch" href="//static.yuleing.com">
<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
<title>预乐网-系统管理</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">
			<h1>预乐网-系统管理-登录页面</h1>
			<a href="javascript:;"><img id="logo" src="http://static.yuleing.com/admin/images/logo.png" alt="预乐网-系统管理-登录页面" /></a>
		</div>
		<div id="login-content">
			<form action="/login.do" method="post" id="loginForm">
				<div id="errorMessage">
					
				</div>
				<p>
					<label>用户名</label> <input class="text-input" type="text" id="account" name="account" nullmsg="请输入用户名!" datatype="" errormsg="" />
				</p>
				<div class="clear"></div>
				<p>
					<label>密码</label> <input class="text-input" type="password" id="password" name="password" nullmsg="请输入密码!" datatype="" errormsg="" />
				</p>
				<div class="clear"></div>
				<P>
					<label>验证码:</label><img alt="验证码" title="验证码" style="float:right;"  src="" style="cursor: pointer;" data-src="/captcha.jpg?sessionid=<%=request.getSession().getId()%>" /><input class="text-input small-input" type="text" id="captcha"  name="captcha"  nullmsg="请输入验证码!" datatype="" errormsg=""/>
				 	
				</P>
				<div class="clear"></div>

				<div class="clear"></div>
				<p>
					<input class="button" type="submit" value="登陆" style="float:right;"/>
				</p>
			</form>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.captcha.js"></script>
<script type="text/javascript">
function changeImage(o){
	var a = new Date().getTime();
	o.src = "/captcha.jpg?t="+a;
}
var errorMessage = '${errorMessage}';
$(function(){
	if(errorMessage!=''){
		var errorJson = jQuery.parseJSON(errorMessage);
		if(errorJson != null && errorJson.status === false){
			$("#errorMessage").html("<div class=\"notification information png_bg\"><div>"+errorJson.errorMessage+"</div></div>");	
		}
		
	}
	$("#loginForm").submit(function(){
		var flag = true;
		var erroeMessage = $("#errorMessage");
		$(this).find("input[datatype]").each(function(){
			var val = $.trim($(this).val());
			if(val===""||val===undefined||val===null){
				flag = false;
				erroeMessage.html("<div class=\"notification information png_bg\"><div>"+$(this).attr("nullmsg")+"</div></div>");
				return false;
			}
		});
		if(flag){
			$.ajax({
				url:'/verifyLogin.do',
				type:'post',
				data:{'account':$("#account").val(),'password':$("#password").val(),'captcha':$("#captcha").val()},
				dataType:'json',
				async:false,
				success:function(data){
					if(data!=null&&!data.status){
						erroeMessage.html("<div class=\"notification information png_bg\"><div>"+decodeURIComponent(data.message)+"</div></div>");
						flag = false;
						$('form').find('img').click();
					}
				}
			});
		}
		return flag;
	});
});
</script>
</body>
</html>
