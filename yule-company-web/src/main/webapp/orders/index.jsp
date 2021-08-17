<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>订单管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/company/css/yule.style.confirm.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default navbar-collapse h-navbar">
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
										订单编号 :&nbsp;&nbsp;<input type="text" placeholder="订单编号" name="order_num" >
										产品名称 :&nbsp;&nbsp;<input type="text" placeholder="产品名称" name="product_name">
										客户姓名 :&nbsp;&nbsp;<input type="text" placeholder="客户姓名" name="person_name">
										客户电话 :&nbsp;&nbsp;<input type="text" placeholder="客户电话" name="phone">
										<br>
										开始时间 :&nbsp;&nbsp;<input type="text" class="datetimepicker" placeholder="开始时间" name="start_time">
										结束时间 :&nbsp;&nbsp;<input type="text" class="datetimepicker" placeholder="结束时间 " name="end_time">
										<div class="checkbox">
											<label> 订单状态: </label>
											<label>
												<input name="status" type="checkbox" class="ace" value="0" >
												<span class="lbl"> 已预约</span>
											</label>
											<label>
												<input name="status" type="checkbox" class="ace" value="1" >
												<span class="lbl"> 已确定</span>
											</label>
											<label>
												<input name="status" type="checkbox" class="ace" value="2" >
												<span class="lbl">已到店 </span>
											</label>
											<label>
												<input name="status" type="checkbox" class="ace" value="3" >
												<span class="lbl">已完成 </span>
											</label>
											<label>
												<input name="status" type="checkbox" class="ace" value="4" >
												<span class="lbl"> 未到店</span>
											</label>
											<label>
												<input name="status" type="checkbox" class="ace" value="5" >
												<span class="lbl"> 已取消</span>
											</label>
										</div>
										<div class="col-sm-10" style="text-align:center">
											<button type="button" class="btn btn-purple btn-sm">
												检索
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped table-bordered table-hover" data-url="/orders/findOrders.do">
										<thead>
											<tr>
												<th>订单编号</th>
												<th>产品名称</th>
												<th>客户姓名</th>
												<th>使用积分</th>
												<th>客户电话</th>
												<th>消费金额</th>
												<th>订单状态</th>
												<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>下单时间</th>
												<th>操作</th>
											</tr>
										</thead>
										${htmls }
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
			<div id="order-wizard" class="modal">
								<input type="hidden" id="orderID" name="order_num"/>
								<input type="hidden" id="status" name="status"/>
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header" data-target="#modal-step-contents">
												<ul class="wizard-steps">
													<li data-target="#modal-step1"  class="active">
														<span class="step"></span>
														<span class="title">已预订</span>
													</li>
													<li data-target="#modal-step2">
														<span class="step"></span>
														<span class="title">已确认</span>
													</li>
													<li data-target="#modal-step3">
														<span class="step"></span>
														<span class="title">已到店</span>
													</li>
													<li data-target="#modal-step4">
														<span class="step"></span>
														<span class="title">已完成</span>
													</li>
												</ul>
											</div>
											<div class="modal-body step-content" id="modal-step-contents">
												<div class="step-pane active" id="modal-step1">
												</div>
											</div>
											<div class="modal-footer wizard-actions">
											<button class="btn btn-danger btn-sm pull-left"  style="display:none;" button-use="uncomplete" data-dismiss="modal">
													<i class="ace-icon fa fa-times"></i>
													未到店
												</button>
												<button class="btn btn-success btn-next" button-use="sure" data-dismiss="modal">
													确认
													<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>
												<button class="btn btn-success btn-next" style="display:none;" button-use="complete" data-dismiss="modal">
													完成
													<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
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
		<script src="http://static.yuleing.com/company/js/yule.jquery.confirm.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript">
		var statusArr=['已预订','已确定','已到店','已完成','未到店','已取消'];
		var progressSpan = 
			'<li data-target="#modal-step5" class="{#active}">'
			+'<span class="step"></span>'
			+'<span class="title">[#name]</span>'
			+'</li>';
		function changeStatus(id,status,expense){
			if(id != null){
				var tr = $('a[modal-dialog="'+id+'"]').parent().parent();
			var changeSpan = tr.find('span[status-change]');
			changeSpan.text(statusArr[status]);
			if(status === '3'){
				tr.find('td[expnese-change]').text(expense.toString());
			}
			}
		}
			jQuery(function($) {
				var backHtml = "";
				$('body').on('click','a[modal-order]',function(){
					 var orderId = $(this).attr('modal-dialog');
					 $.ajax({
						url:"/orders/findOrdersDetailsData.do",
						type:"post",
						dataType:"json",
						data:{'order_num':$(this).attr('modal-dialog')},
						async:false,
						success:function(data){
							if(data != null){
								var status = data.orderStatus;
								var foreTime = status;
								var spans = "";
								if(status === 4 || status === 5){
									foreTime = 6 - status;
									for(var i = 0;i< foreTime;i++){
										spans += progressSpan.replace("[#name]",statusArr[i]).replace("{#active}","active");
									}
									spans += progressSpan.replace("[#name]",statusArr[status]).replace("{#active}","active");;
									$('#order-wizard').find('.wizard-steps').html(spans);
									spans = null;
									$("button[button-use='sure']").hide();
									$("button[button-use='complete']").hide();
									$("button[button-use='uncomplete']").hide();
								}else{
									for(var i = 0;i< 4;i++){
										if(i <= status){
											spans += progressSpan.replace("[#name]",statusArr[i]).replace("{#active}","active");
										}else{
											spans += progressSpan.replace("[#name]",statusArr[i]).replace("{#active}","");
										}
									}
									$('#order-wizard').find('.wizard-steps').html(spans);
									spans = null;
									if(status === 1){
									  $("button[button-use='uncomplete']").show();
									}else{
									  $("button[button-use='uncomplete']").hide();
									}
									if(status === 3){
									  $("button[button-use='sure']").hide();
									  $("button[button-use='complete']").show();
									}else{
									  $("button[button-use='sure']").show();
									  $("button[button-use='complete']").hide();
									}
								}
									$('#order-wizard').find('#status').val(status + 1);
									$('#order-wizard').find('#orderID').val(orderId);
									$('#order-wizard').find('#modal-step1').html(data.detailsHTML);
							}else{
									return false;
							}
						 }
						});
					});
				$('#order-wizard').on('click',"button[button-use]",function(){
					var typeValue = $(this).attr('button-use');
					if( typeValue === "complete"){
						return;
					}else if(typeValue === "uncomplete"){
					$('#order-wizard').find("#status").val(4);
					}
					var jsonData = {};
					var inputs = $('#order-wizard').find("input");
					var id = "";
					var status ;
					var expense_money;
					inputs.each(function(i){
						var name = $(this).attr("name");
						if(name!=""&&name!=null&&name!=undefined){
							if(name === 'order_num'){
								 id = $(this).val();
							}
							if(name === 'status'){
								status = $(this).val();
							}
							if(name === 'expense_money'){
								expense_money = $(this).val();
							}
							jsonData[name] = $(this).val();
						}
						$(this).attr("value",$(this).val());
					});				
					$.ajax({
						url:"/orders/updateOrdersStatus.do",
						type:"post",
						dataType:"json",
						data:jsonData,
						async:false,
						success:function(data){
							changeStatus(id,status,expense_money);
							if(status == 1){
								setNotReadInfo();	
							}
							
							}
						});
					});
				
				$('body').on('click','a[modal-comment]',function(){
					 var orderId = $(this).attr('modal-dialog');
					 $.ajax({
							url:"/orders/findOrdersComment.do",
							type:"post",
							dataType:"json",
							data:{'order_num':$(this).attr('modal-dialog')},
							async:false,
							success:function(data){
								if(data != null && data.status != 1){
									$('#comment-wizard').find('#commentTable').html(data.tbody);
									$('.timeago').timeago();
								}
							}
					 	});
				});
				});
			
		</script>
	</body>
</html>
