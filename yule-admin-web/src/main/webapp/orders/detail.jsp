<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>定时器任务</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>(${orders.order_num })订单详情</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
							<fieldset>
								<p>
									<label>企业名称</label>
									<span>${company.name }</span>
								</p>
								<p>
									<label>产品名称</label>
									<span>${product.name }</span>
								</p>
								<p>
									<label>客户姓名</label>
									<span>${orders.persion_name }</span>
								</p>
								<p>
									<label>客户电话</label>
									<span>${orders.phone }</span>
								</p>
								<p>
									<label>出发时间</label>
									<span>${orders.go_time }</span>
								</p>
								<p>
									<label>到达时间</label>
									<span>${orders.arrive_time }</span>
								</p>
								<p>
					              <label>特殊要求</label>
					              <span>${orders.desc }</span>
					            </p>
							</fieldset>
							<div class="clear"></div>
					</div>
				</div>
			</div>
			<!-- End Notifications -->
			<div id="footer"><small>
					&#169; Copyright 2010 Your Company | Powered by <a href="javascript:;">admin
						templates</a> | <a href="javascript:;">Top</a>
				</small>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
</body>
</html>