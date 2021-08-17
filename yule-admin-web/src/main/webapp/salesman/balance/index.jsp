<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>合作伙伴结算管理</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">

		<div id="main-content">

			<div class="content-box">
				<div class="content-box-header">
					<h3>合作伙伴结算</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
						<form  action="/salesmanBalance/findSalesmanBalances.do" method="get" data-query-form="">
							<fieldset>		
								<span>业务员ID:<input type="text" class="text-input" name="salesman_id" value="${salesmanBalanceQuery.salesman_id }" /></span>
								<span>开始时间:<input type="text" class="text-input datetimepicker" name="start_time" value="${salesmanBalanceQuery.start_time }" /></span>
								<span>结束时间:<input type="text" class="text-input datetimepicker"  name="end_time" value="${salesmanBalanceQuery.end_time }" /></span><br/>
								<span>结算状态:<select name="pay_status"><option value="">所有</option><option value="0">已结算</option><option value="1">未结算</option></select></span><br/>
								<input type="submit" class="button" value="检索" />
							</fieldset>
						</form>
						</div>
						<table>
								<thead>
									<tr>
										<th>日期</th>
										<th>合作伙伴名称</th>
										<th>企业订单数</th>
										<th>企业收入</th>
										<th>合作伙伴收益</th>
										<th>支付时间</th>
										<th>操作</th>
									</tr>
								</thead>
								${salesmanBalanceHtmls}
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
$(function(){
	selectValue("select[name='pay_status']","${salesmanBalanceQuery.pay_status}");
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
	$("a[pay]").live('click',function(){
 		var btn = $(this);
		var tr = btn.parent().parent();
		tr.hide();
		var jsonData = {};
		jsonData["id"] = btn.attr("data-id");
		$.ajax({
			type: "POST",
			url: btn.attr("data-url"),
			dataType:"json",
			data:jsonData,
			async:false,
			success: function(msg){
				if(msg.status==true){
					btn.parent().parent().find("td[class='pay_time']").text(msg.pay_time);
					btn.parent().text("已支付");
				}
			},
		});
		tr.show(); 
	});
});
</script>
</body>
</html>