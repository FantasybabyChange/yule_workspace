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
					<h3>用户登录列表</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
							<form  action="/userLogin/findUserLogin.do" method="get" data-query-form="">		
								<span>名称:<input type="text" class="text-input" name="name" value="${userLoginQuery.name }" ></span>
								<span>手机号:<input type="text" class="text-input" name="phone" value="${userLoginQuery.phone }"></span>
								<span>邮箱:<input type="text" class="text-input" name="mail" value="${userLoginQuery.mail }"></span><br/>
								<span>开始时间:<input type="text" class="text-input datetimepicker"   name="start_time" value="${userLoginQuery.start_time }"></span>
								<span>结束时间:<input type="text"  class="text-input datetimepicker"  name="end_time" value="${userLoginQuery.end_time }"></span><br/>
								是否删除<select name="is_delete"><option  value="">全部</option><option  value="0" >未删除</option><option  value="1">已删除</option></select>
								状态<select name="status"><option  value="">全部</option><option  value="0" >启用</option><option  value="1">未启用</option></select>
								<input type="submit" class="button" value="检索" />
							</form>
							
						</div>
					   <table>
							<thead>
								 <tr>
								   <th>名称</th>
								   <th>手机号</th>
								   <th>手机认证状态</th>
								   <th>邮箱</th>
								   <th>邮箱认证状态</th>
								   <th>是否删除</th>
								   <th>最后一次登陆时间</th>
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
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>

<script type="text/javascript">
	selectValue("select[name='is_delete']","${userLoginQuery.is_delete}");
	selectValue("select[name='status']","${userLoginQuery.status}");
	$(function(){
		$(".datetimepicker").click(function(){
			WdatePicker();
		});
	});
</script>
</body>
</html>