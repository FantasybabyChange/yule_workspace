<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单管理</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">

		<div id="main-content">

			<div class="content-box">
				<div class="content-box-header">
					<h3>订单</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
						<form  action="/orders/findOrders.do" method="get" data-query-form="">
							<fieldset>		
								<span>订单号:<input type="text" class="text-input" name="order_num" value="${ordersQuery.order_num }" /></span>
								<span>公司ID:<input type="text" class="text-input" name="company_id" value="${ordersQuery.company_id }" /></span>
								<span>用户ID:<input type="text" class="text-input" name="user_id" value="${ordersQuery.user_id }" /></span>
								<span>产品ID:<input type="text" class="text-input" name="product_id" value="${ordersQuery.product_id }" /></span><br/>
								<span>顾客姓名:<input type="text" class="text-input" name="person_name" value="${ordersQuery.person_name }" /></span>
								<span>手机号:<input type="text" class="text-input" name="phone" value="${ordersQuery.phone }" /></span><br/>
								<span>下单开始时间:<input type="text" class="text-input datetimepicker" name="start_time" value="${ordersQuery.start_time }" /></span>
								<span>下单结束时间:<input type="text" class="text-input datetimepicker"  name="end_time" value="${ordersQuery.end_time }" /></span><br/>
								是否删除<select name="is_delete"><option  value="">全部</option><option  value="0" >未删除</option><option  value="1">已删除</option></select>
								状态<select name="queryStatus">
								<option  value="">全部</option>
								<option  value="0" >已预订</option>
								<option  value="1">已确认</option>
								<option  value="2">已到店</option>
								<option  value="3">已完成</option>
								<option  value="4">未到店</option>
								<option  value="5">已取消</option>
								</select>
								<input type="submit" class="button" value="检索" />
							</fieldset>
						</form>
						</div>
						<table>
								<thead>
									<tr>
										<th>订单编号</th>
										<th>客户ID</th>
										<th>客户姓名</th>
										<th>客户电话</th>
										<th>企业ID</th>
										<th>企业名称</th>
										<th>消费金额</th>
										<th>使用积分</th>
										<th>预计到达时间</th>
										<th>下单时间</th>
										<th>订单状态</th>
									</tr>
								</thead>
								${orderHtmls}
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript">
	selectValue("select[name='is_delete']","${ordersQuery.is_delete}");
	selectValue("select[name='queryStatus']","${ordersQuery.queryStatus}");
$(function(){
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
</script>
</body>
</html>