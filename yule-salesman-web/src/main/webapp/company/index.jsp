<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>企业管理</title>

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
										企业名称 :<input type="text" placeholder="企业名称" name="name" >
										创建开始时间 :<input type="text" class="datetimepicker" placeholder="开始时间" name="start_time">
										创建结束时间 :<input type="text" class="datetimepicker" placeholder="结束时间 " name="end_time">
										<div class="col-sm-10" style="text-align:center">
											<button class="btn btn-success" >查询</button>
										</div>
									</div> 
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped table-bordered table-hover" data-url="/company/findCompanyInfo.do" data-type="post">
										<thead>
											<tr>
												<th>企业名称</th>
												<th>企业类型</th>
												<th>企业档次</th>
												<th>提成</th>
												<th>折扣</th>
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

			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<%@ include file="/common/scripts.jsp" %>

		<!-- page specific plugin scripts -->
		<script src="http://static.yuleing.com/salesman/js/yule.jquery.tables.js"></script>
		
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$("body").on('click',"button[data-status]",function(){
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
			});
		</script>
	</body>
</html>
