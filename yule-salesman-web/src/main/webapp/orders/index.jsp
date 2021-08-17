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
									<div data-query="/orders/findOrders.do">
										订单编号 :<input type="text" placeholder="订单编号" name="order_num" >
										产品名称 :<input type="text" placeholder="产品名称" name="product_name">
										企业名称 :<input type="text" placeholder="企业名称" name="company_name">
										开始时间 :<input type="text" class="datetimepicker" placeholder="开始时间" name="start_time">
										结束时间 :<input type="text" class="datetimepicker" placeholder="结束时间 " name="end_time">
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
												<span class="lbl">已到店 </span>
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
												<th>企业名称</th>
												<th>消费金额</th>
												<th>订单状态</th>
												<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>下单时间</th>
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
		</script>
	</body>
</html>
