<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>消息管理</title>

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
								<a href="#send-wizard" data-toggle="modal" id="sendMessage" class="btn btn-app btn-info btn-sm no-radius">
											<i class="ace-icon fa fa-envelope bigger-200"></i>
											发送消息
											</a>
											<hr>
									<div data-query="">
										标题 :<input type="text" placeholder="标题" name="title" >
										是否查看:<select name=is_read>
													<option value="">全部</option>
													<option value="0">是</option>
													<option value="1">否</option>
												</select>
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
									<table class="table table-striped table-bordered table-hover" data-url="/adminMessage/findAdminMessage.do" data-type="post">
										<thead>
											<tr>
												<th>标题</th>
												<th>状态</th>
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
		<!-- 消息展示的div -->
		<div id="message-wizard" class="modal" >
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
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 消息发送的DIV -->
		<div id="send-wizard" class="modal" >
			<div class="modal-dialog"  style="width:70%;height: 100%;">
				<div class="modal-content" >
					<div class="modal-body step-content"  id="modal-step-contents" >
						<div class="step-pane active"  >
						<form action="/adminMessage/insertAdminMessage.do" method="post" dataType="json">
						<div>
						<label>消息接收者:</label>
						<div id="fatherDiv" class="div_txt">
						<input id="keyword" type="text" class="float:left;"style="border:none;width:100%" />
						</div>
						</div>
						<div class="hr hr-24"></div>
						<div>
						<input type="text" style="width: 70%;height: 20%;" name="title" placeholder="消息标题" >
						</div>
						<div class="space-4"></div>	
						<div>
						<textarea  name="content" cols="70" rows="10" placeholder="消息内容"></textarea>	
						</div>
							<button class="btn btn-info"  type="button" data-send="" data-dismiss="modal">
								<i class="ace-icon fa fa-check bigger-110" style="text-align:center"></i>
								发送
							</button>	
						</form>
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
		<!-- basic scripts -->	
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
				$('button[data-send]').click(function(){
					var keyvalue = $('#keyword').val();
					var datalength = $('div[data-keyword]').length
					if( datalength === 0 && keyvalue.length === 0){
						$('#fatherDiv').after('<span class="red"><i class="ace-icon fa fa-times"></i>请选择发送的企业</span>');
						return;
					}else if(datalength === 0 && keyvalue.length > 0){
						$('#fatherDiv').next().remove();
						$('#fatherDiv').after('<span class="red"><i class="ace-icon fa fa-times"></i>请选择输入的企业</span>');
						return;
					}else{
						$('#fatherDiv').next().remove();
						ajaxform.success = function(data){
							if(data.status){
								alert('消息发送成功');
							}
					}
					ajaxform.ajaxSubmit($("form"));
					}
				});
				$('body').on('click','a[modal-message]',function(){
					 var messageId = $(this).attr('modal-dialog');
					 $.ajax({
						url:"/adminMessage/findAdminMessageById.do",
						type:"post",
						dataType:"json",
						data:{'id':messageId},
						async:false,
						success:function(data){
							if(data != null && data.status === 0 ){
								$('#messageTitile').text(data.title);
								$('#sendTime').text(data.createTime);
								$('#messageContent').html(data.content);
								}
							}
						 });
					 var is_read = $(this).attr('modal-message');
					 var clickSpan =  $(this);
					 if(is_read != '0' && is_read != 0){
					 $.ajax({
							url:"/adminMessage/updateAdminMessage.do",
							type:"post",
							dataType:"json",
							data:{'id':messageId,'is_read':0},
							async:false,
							success:function(data){
								if(data != null && data.status){
									clickSpan.attr('modal-message',0);
									clickSpan.parent().next().text("已查看");
									}
								}
							 });
						 }
					});
			});
		</script>
	</body>
</html>
