<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>财务结算</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css" />

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
							<!-- PAGE CONTENT BEGINS -->
							<div class="hr dotted"></div>
							<div class="user-profile">
								<div class="tabbable">
									<ul class="nav nav-tabs padding-18">
										<li class="active">
											<a data-toggle="tab" href="#home">
												<i class="green ace-icon fa fa-user bigger-120"></i>
												上月结算
												
											</a>
										</li>

										<li>
											<a data-toggle="tab" href="#feed">
												<i class="orange ace-icon fa fa-rss bigger-120"></i>
												历史结算
											</a>
										</li>

									</ul>

									<div class="tab-content no-border padding-24">
										<div id="home" class="tab-pane in active" >
											<div class="row">

												<div class="col-xs-12 col-sm-12">
													${balance }					
													<div class="hr hr-8 dotted"></div>
												</div><!-- /.col -->
											</div><!-- /.row -->
										</div><!-- /#home -->

										<div id="feed" class="tab-pane">
											<div class="row">
												<div class="col-xs-12 col-sm-12">
													<div class="col-xs-12">
														<div data-query="">
															开始时间 :<input type="text" class="datetimepicker" placeholder="开始时间" name="start_time">
															结束时间 :<input type="text" class="datetimepicker" placeholder="结束时间 " name="end_time">
															<div class="col-sm-10" style="text-align:center">
																<button class="btn btn-success" >查询</button>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-xs-12">
														 100积分 =1 元
														<table class="table table-striped table-bordered table-hover" data-url="/balance/findHistoryBalance.do" data-type="post">
															<thead>
																<tr>
																	<th>日期</th>
																	<th>订单数(笔)</th>
																	<th>收入(元)</th>
																	<th>用户积分累计(分)</th>
																	<th>提成(百分比)</th>
																	<th>支付(元)</th>
																	<th>支付状态</th>
																	<th>支付时间</th>
																</tr>
															</thead>
															<tfoot>
															</tfoot>
															<tbody>
															</tbody>
														</table>
													</div>
												<div class="hr hr-8 dotted"></div>
												</div>							
											</div>
										</div>
									</div>
									<!-- PAGE CONTENT ENDS -->
								</div><!-- /.col -->
							</div><!-- /.row -->
						</div><!-- /.page-content -->
					</div><!-- /.main-content -->
				</div>
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
		<script src="http://static.yuleing.com/company/js/yule.jquery.tables.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="http://static.yuleing.com/js/jquery.autocomplete.min.js"></script>
	</body>
</html>
