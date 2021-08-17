<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>消费管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
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
										消费种类:${select }
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<form action="">
										<table class="table table-striped table-bordered table-hover" data-url="/companyExpense/findCompanyExpense.do" data-type="post">
											<thead>
												<tr>
													<th>名称</th>
													<th>价格</th>
													<th>操作</th>
												</tr>
											</thead>
											<tfoot>
											</tfoot>
											<tbody>
											</tbody>
										</table>															
									</form>					
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
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxsubmit.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.tables.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>
		
		<script type="text/javascript">
	
		$(function(){
			trRow.privilegeHtml='${privilegeHtml}';
			$("#expense_category_id").on("change",function(){
				datatable.jsonData["pageNo"] = 1;
				datatable.getQueryData();
				datatable.getHtml();				
			});
		});
		var insertPrivilegeHtml = '${insertPrivilegeHtml}';
		function deleteExpense(target){
			var tar = $(target);
			if(tar!=null&&tar!=undefined){
				$.ajax({
					url:tar.attr("data-url"),
					type:"post",
					dataType:"json",
					data:{"id":tar.attr("data-id")},
					async:false,
					success:function(data){
						if(data!=""&&data!=undefined&&data){
							tar.parent().parent().parent().find("input").each(function(){
								var id = $(this).attr("name");
								if(id!=""&&id!=null&&id!=undefined&&id!="company_id"&&id!="product_category_id"){
									$(this).val("");
								}
							});
							tar.parent().html(insertPrivilegeHtml);
						}
					}
				});
			}
		}
		</script>
	</body>
</html>
