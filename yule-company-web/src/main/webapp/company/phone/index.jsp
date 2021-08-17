<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>联系方式</title>

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
									<div insert-url="/companyPhone/insertCompanyPhone.do">
										<span>姓名 :<input type="text" placeholder="姓名" name="name"   nullmsg="请输入名称" datatype="list" errormsg="名称重复!" ></span>
									<span>手机号 :<input type="text" placeholder="手机号" name="phone"  datatype="p" nullmsg="请输入联系方式"></span>
									<span>类型 :
										<select name="type">
											<option value="0">接待</option>
											<option value="1">经理</option>
										</select>
									</span>
											<button class="btn btn-success" data-query-insert="" >添加</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<form id="companyPhoneForm" action="/companyPhone/batchUpdateCompanyPhone.do" method="post" data-ajax="" dataType="json" async="true">
									<table table-name="" class="table table-striped table-bordered table-hover" >
										<thead>
											<tr>
												<th>姓名</th>
												<th>手机号 </th>
												<th>类型 </th>
												<th>操作</th>
											</tr>
										</thead>
										<tfoot>
										</tfoot>
										<tbody id="list">
											${htmls }
										</tbody>
									</table>
									</form>
									<button class="btn btn-success" data-form="companyPhoneForm" batch-update="" >批量更新</button>
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
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxform.js"></script>	
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>	
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(function(){
			trRow.rowHtml = '${rowHtml}';
		});
		</script>
	</body>
</html>
