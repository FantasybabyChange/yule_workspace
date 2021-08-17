<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div role="navigation" id="menu-bar" class="cont-shadow">
	<div class="g clearfix" style="50px !important">
	    <a href="http://www.yuleing.com/" style="float: left;">
	        <img src="http://images.yuleing.com/logo/yuleing.png" width="200px" style="margin:0 auto;"  alt="预乐进行时">
	    </a>
	    <ul class="right-nav" id="login-nav">
	    </ul>
    </div>
</div>
<form action="http://login.yuleing.com/findHeader.do" method="post" target="header" id="headerForm"></form>
<iframe src="about:blank" width="1" height="1" id="header" name="header" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
<script type="text/javascript">
	document.getElementById("headerForm").submit();
	document.domain = "yuleing.com";
	header={
		user_login_result:function(result){
			document.getElementById("login-nav").innerHTML = result;
		}
	}
</script>