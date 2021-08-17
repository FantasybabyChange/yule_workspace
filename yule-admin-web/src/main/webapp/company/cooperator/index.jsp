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
					<h3>企业合作</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
							<form  action="/companyCooperator/findCompanyCooperator.do" method="get" data-query-form="">	
								<fieldset>	
									<span>企业名称:<input type="text" class="text-input" name="name" value="${companyCooperatorQuery.name }" /></span>
									<span>电话号码:<input type="text" class="text-input" name="phone" value="${companyCooperatorQuery.phone }" /></span>
									<span>邮箱:<input type="text" class="text-input" name="mail" value="${companyCooperatorQuery.mail }" /></span><br/>
									<span>创建开始时间:<input type="text" class="text-input datetimepicker" name="start_time" value="${companyCooperatorQuery.start_time }" /></span>
									<span>创建结束时间:<input type="text" class="text-input datetimepicker" name="end_time" value="${companyCooperatorQuery.end_time }" /></span><br/>
									合作状态:<select name="status"><option  value="">全部</option><option  value="0" >已合作</option><option  value="1">未合作</option></select>
									<input type="submit" class="button" value="检索" />
								</fieldset>
							</form>
						</div>
						<table>
							<thead>
								<tr>
									<th>企业名称</th>
									<th>联系电话</th>
									<th>邮箱</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							${htmls}
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" >
selectValue("select[name='status']","${companyCooperatorQuery.status}");
$(function(){
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
</script>

</html>