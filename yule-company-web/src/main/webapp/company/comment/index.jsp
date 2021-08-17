<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>点评管理</title>

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
							<div class="row">
								<div class="col-xs-12">
									<div data-query="">
										用户名称 :<input type="text" placeholder="用户名称" name="user_name" >
										
											<button class="btn btn-success" >查询</button>
										<br>
										<br>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped table-bordered table-hover" data-url="/companyComment/findCompanyComment.do" data-type="post">
									<thead>
										<th colspan="7">用户评论</th>
									</thead>
									<tfoot>
									</tfoot>
									<tbody>
									</tbody>
									</table>
								</div>
							</div><!-- /.row -->
						</div>
					</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<!-- 评论的div -->
								<div id="comment-wizard" class="modal" style="width:100%">
									<div class="modal-dialog" style="width:70%">
										<div class="modal-content" style="width:100%">
											<div class="modal-body step-content" style="width:100%" id="modal-step-contents">
												<div class="step-pane active" style="width:100%" >
													<table id="commentTable" style="width:100%;">
													
													</table>
												</div>
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
		<script src="http://static.yuleing.com/company/js/yule.jquery.tables.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			datatable.complete = function(){
				$('.timeago').timeago();
				$('body').on('click','a[data-toggle]',function(){
					 var orderNum = $(this).attr('modal-dialog');
					 $.ajax({
							url:"/companyComment/findCommentByOrderNum.do",
							type:"post",
							dataType:"json",
							data:{'order_num':$(this).attr('modal-dialog')},
							async:false,
							success:function(data){
								if(data != null){
									$('#comment-wizard').find('#commentTable').html(data.tbody);
									$('.timeago').timeago();
								}
							}
					 	});
				});
			}
		</script>
	</body>
</html>
