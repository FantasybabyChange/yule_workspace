<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>价格对比</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
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
							<div class="row">
								<div class="col-xs-12">
									<div data-query="">
										企业名称:<input type="text" placeholder="企业名称" name="company_name" id="company_name" />
										<div class="col-sm-10" style="text-align:center">
											<button class="btn btn-success" >查询</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-striped table-bordered table-hover" data-url="/sale/comparePrice.do" data-type="post">
										<thead>
											<tr>
												<th>企业名称</th>
												<th>产品名称</th>
												<th>容纳人数</th>
												<th>最低消费</th>
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
		<script type="text/javascript">
		var ids =new Array();
		$(function(){
			$("#company_name").val("");
	    	$.ajax({
	    		type: "post",
	    		async: false, //同步执行
	    		url: "/company/findSameCompany.do",
	    		dataType: "json", 
	    		success: function (result) {
	    			$("#company_name").autocomplete(result.companies,{
	    				max : 10, //列表里的条目数
	    				minChars : 1, //自动完成激活之前填入的最小字符
	    				width : 200, //提示的宽度，溢出隐藏
	    				scrollHeight : 300, //提示的高度，溢出显示滚动条
	    				matchContains : true, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	    				multiple : false,
	    				selectFirst : false,
	    				//mustMatch : true,
	    				//autoFill : false, //自动填充
	    				formatItem : function(row, i, max) {
	    					if (null != row.label && row.label != undefined) {
	    						return row.label;
	    					}
	    					return "没有找到结果!";
	    				},
	    				formatMatch : function(row, i, max) {
	    					return row.label  + row.pinyin;
	    				},
	    				formatResult : function(row) {
	    					return row.label;
	    				}
	    			});
	    		},
	    		error: function (errorMsg) {    		
	    		}
	    		});
		});
		</script>		
	</body>
</html>
