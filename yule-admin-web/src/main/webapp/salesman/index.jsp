<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
					<h3>业务员管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<form  action="/salesmanLogin/findSalesmanLogin.do" method="get" data-query-form="">
							<fieldset>
								<span>帐号:<input type="text" class="text-input" name="account" value="${salesmanQuery.account }" ></span>
								<span>姓名:<input type="text" class="text-input" name="name" value="${salesmanQuery.name }"></span><br/>
								<span>创建开始时间:<input type="text" class="text-input datetimepicker" name="start_time" value="${salesmanQuery.start_time }"></span>
								<span>创建结束时间:<input type="text" class="text-input datetimepicker" name="end_time" value="${salesmanQuery.end_time }"></span><br/>
								是否删除<select name="is_delete"><option  value="">全部</option><option  value="0" >未删除</option><option  value="1">已删除</option></select>
								状态<select name="status"><option  value="">全部</option><option  value="0" >启用</option><option  value="1">未启用</option></select>
								<input type="submit" class="button" value="查询"/>
							</fieldset>
						</form>
						<table>
							<thead>
								<tr>
									<th><input class="check-all" type="checkbox" /></th>
									<th>销售人员编码</th>									
									<th>用户名</th>
									<th>姓名</th>
									<th>上次登录时间</th>
									<th>是否删除</th>
									<th>创建时间</th>
									<th style="width:30%">操作</th>
								</tr>
							</thead>
							${htmls}
						</table>
					</div>
					<div class="tab-content" id="tab2" style="display: none;">
						<form action="/salesmanLogin/insertSalesmanLogin.do" method="post"  ajaxvalidform="" data-url="/salesmanLogin/verifySalesmanLogin.do">
							<fieldset>
								<p>
									<label>帐号:</label> <input class="text-input" type="text" id="small-input" name="account" nullmsg="请输入用户名!" datatype="" errormsg="" ajaxdata="" /> 
								</p>
					      		<p>
									<label>密码:</label> <input class="text-input"
										type="password" id="small-input" name="password" nullmsg="请输入密码!" datatype="" errormsg="" /> 
								</p>
								<p>
									<label>提成:</label> <input class="text-input"
										type="text" id="small-input" name="commision" nullmsg="请输入提成!" datatype="n" errormsg="请输入数字!" /> 
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
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" >
$(function(){
	selectValue("select[name='is_delete']","${salesmanQuery.is_delete}");
	selectValue("select[name='status']","${salesmanQuery.status}");
});
$(function(){
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
</script>

</body>
</html>