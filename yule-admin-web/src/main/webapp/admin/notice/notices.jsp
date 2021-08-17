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
			<div class="content-box">
				<div class="content-box-header">
					<h3>系统通知${parentIdtest}</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
				
					<div class="tab-content default-tab" id="tab1">
	                   	<div>
							<form  action="/adminNotice/findAdminNoticeIsAdmin.do" method="get" data-query-form="">		
	                   			<span>标题:<input type="text" class="text-input" name="title" value="${adminNoticeQuery.title }" ></span>
								<span>开始时间:<input type="text" class="text-input datetimepicker"   name="start_time" value="${adminNoticeQuery.start_time }"></span>
								<span>结束时间:<input type="text"  class="text-input datetimepicker"  name="end_time" value="${adminNoticeQuery.end_time }"></span>
								<input type="submit" class="button" value="检索" />
	                   		</form>
	                   	</div>	
	                   <table>
	                       	<thead>
	                       	<tr>
							<th>通知标题</th>
							<th>通知内容</th>
							<th>创建时间</th>
							<th>操作</th>
							</tr>
							</thead>
	                      ${htmls }
	                   </table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript">
$(function(){
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
</script>
</body>
</html>