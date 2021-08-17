<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>用户管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />

	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default">
			<%@ include file="/common/header.jsp" %>
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
				<%@ include file="/common/menu.jsp" %>
			</div>

			<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
									<div data-query="">
										用户名称 :<input type="text" placeholder="用户名称" name="account" >
										是否启用:<select name="status">
													<option value="">全部</option>
													<option value="0">是</option>
													<option value="1">否</option>
													</select>
										创建开始时间 :<input type="text" class="datetimepicker" placeholder="开始时间" name="start_time">
										创建结束时间 :<input type="text" class="datetimepicker" placeholder="结束时间 " name="end_time">
										<div class="col-sm-10" style="text-align:center">
											<button class="btn btn-success" >查询</button>
											<a class="btn btn-primary" href="#companyUser-insert-wizard"   data-toggle="modal"   >新增用户</a>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped table-bordered table-hover" data-url="/companyUser/findCompanyUser.do" data-type="post">
										<thead>
											<tr>
												<th>用户名称</th>
												<th>登录时间</th>
												<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>创建时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tfoot>
										</tfoot>
										<tbody>
										</tbody>
									</table>
								</div>
							</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

<!-- 修改密码 -->		
			<div id="companyUser-wizard" class="modal" >
				<div class="modal-dialog" >
					<div class="modal-content" style="width:70%;margin-top:40%;">
					<div class="modal-body step-content" id="modal-step-contents">
							<label>新密码:</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="password" name="password" id="password"  placeholder="请输入新密码" />
							<button  class="btn btn-sm btn-success" style="margin-left:7%;" data-dismiss="modal">
								修改密码
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 新增用户 -->
			<div id="companyUser-insert-wizard" class="modal">
				<div class="modal-dialog">
					<div class="modal-content" style="margin-top:30%;height:80%;width:70%;">
						<div class="modal-body step-content" id="modal-step-contents" style="text-align:center;">
					<form action="/companyUser/insertCompanyUser.do" method="post" validform="">
					<fieldset>
					<p>		<label>用户名:</label>
							<input type="text" name="account" id="account"  placeholder="请输入用户名" datatype="" nullmsg="请输入用户名" /></p>
					<p>	<label>密码:</label>
							<input type="password" name="password" id="password"  placeholder="请输入密码" datatype="*6-16" nullmsg="请输入密码"  /></p>
					<p>	<button  class="btn btn-sm btn-success"  type="submit">新增</button></p>
					</fieldset>
					</form>
						</div>
																		
					</div>
				</div>
			</div>
			
			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<%@ include file="/common/scripts.jsp" %>

		<!-- page specific plugin scripts -->
		
		<script src="http://static.yuleing.com/assets/js/bootbox.min.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.tables.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>		
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$("body").on('click','button[data-status]',function(){
					var btn = $(this);
					var tr = btn.parent().parent();
					tr.hide();
					var jsonData = {};
					jsonData["id"] = btn.attr("data-id");
					jsonData[btn.attr("data-name")] = btn.attr("data-status");
					$.ajax({
						type: "POST",
						url: btn.attr("data-url"),
						dataType:"json",
						data:jsonData,
						async:false,
						success: function(msg){
							if(msg.status==true){
								btn.attr("data-status",msg.value);
								btn.text(decodeURIComponent(msg.text));
								if(btn.attr("data-name")=='is_delete')
								btn.parent().parent().find("td[class='is_delete']").text(decodeURIComponent(msg.is_delete_text));
							}
						}
					});
					tr.show();
				});
				var companyUserId = '';
				$('table').on('click','a[modal-password]',function(){
					companyUserId = $(this).attr('modal-dialog');
				});
				$('#companyUser-wizard').on('click','button[data-dismiss]',function(){
					var password = $('#companyUser-wizard').find('#password').val();
					if(password != null && password.length > 1){
					$.ajax({
						url:'/companyUser/updateCompanyUser.do',
						type:'post',
						dataType:"json",
						async:false,
						data:{'id':companyUserId,'password':password},
						success:function(data){
							if(data){
								bootbox.alert('修改用户密码成功');
							}
							
						}
					});
					}else{
						bootbox.alert('请输入密码');
					}
				});
				$('form[validform]').submit(function(){
					var form = $(this);
					form.find('span[error-data]').remove();
					var flag = false;
					$.ajax({
						url:'/companyUser/verifyCompanyUser.do',
						type:'post',
						dataType:"json",
						async:false,
						data:{'account':$('#account').val()},
						success:function(data){
							if(data && data.status){
								flag = true;
							}else{
								form.append('<span error-data style="color:red">'+data.message+'</span>');
							}
							
						}
					});
					return flag;
				});
			});
		</script>
	</body>
</html>
