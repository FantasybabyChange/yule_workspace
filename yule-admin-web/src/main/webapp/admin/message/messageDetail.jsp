<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css">
<style>
.div_txt {
	border: 1px solid #c3c3c3;
	width: 90%;
	font-family: Tahoma;
}
</style>
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>消息${parentIdtest}</h3>
					<a class="goback-btn button" href="javascript:;">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content" style="text-indent: 0em;word-wrap:break-word;">
					<center><p>${adminMessage.title} </p></center>
					<p>${create_time} </p>
					<hr>
					<br>
					<label>消息内容</label><p>${adminMessage.content}</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
	<script src="http://static.yuleing.com/admin/js//yule.jquery.config.js"></script>
	<script type="text/javascript">
	$(function(){
		var is_read = '${adminMessage.is_read}';
		var id = '${adminMessage.id}';
		 if(is_read != '0' && is_read != 0){
			$.ajax({
			    url:"/adminMessage/updateAdminMessage.do",
				type:"post",
				dataType:"json",
				data:{'id':id,'is_read':0},
				async:false
			 });
		 }
	});
	</script>
</body>
</html>