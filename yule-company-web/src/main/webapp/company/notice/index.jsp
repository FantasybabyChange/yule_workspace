<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>通知管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		
		<style>
			.div_txt {
				border: 1px solid #c3c3c3;
				width: 90%;
				font-family: Tahoma;
			}
		</style>
		<!-- page specific plugin styles -->	
		<link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css">
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
										标题 :<input type="text" placeholder="标题" name="title" >
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
									<table class="table table-striped table-bordered table-hover" data-url="/adminNotice/findAdminNotice.do" data-type="post">
										<thead>
											<tr>
												<th>标题</th>
												<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>创建时间</th>
											</tr>
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
		<!-- 通知查看的div -->
		<div id="notice-wizard" class="modal" >
			<div class="modal-dialog" >
				<div class="modal-content" >
					<div class="modal-header" >
						<h3><CENTER><span id="messageTitile"class="blue"></span></CENTER></h3>
						发送时间:<span id="sendTime"></span>
					</div>
					<div class="modal-body step-content"  id="modal-step-contents" >
						<div class="step-pane active"  >
							<div style="text-indent: 0em;word-wrap:break-word;" class="alert alert-info" id="messageContent" >
							</div>
							<hr class="hr 24">
							<span class="blue">相关附件</span>
							<div id="attachmentGroup">
							
							</div>
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
		<script type="text/javascript" src="http://static.yuleing.com/js/jquery.autocomplete.min.js"></script>
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxSendMessage.js"></script>
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxform.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$('body').on('click','a[modal-notice]',function(){
					 var messageId = $(this).attr('modal-dialog');
					 $.ajax({
						url:"/adminNotice/findAdminNoticeById.do",
						type:"post",
						dataType:"json",
						data:{'id':messageId},
						async:false,
						success:function(data){
							if(data != null){
								$('#messageTitile').text(data.title);
								$('#sendTime').text(data.createTime);
								$('#messageContent').html(data.content);
								$('#attachmentGroup').html(data.attachmentHtmls);
								}
							}
						 });
					});
			});
		</script>
	</body>
</html>
