<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>优惠管理</title>

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
									<div insert-url="/companyFavorable/insertCompanyFavorable.do">
										<span>优惠名称:<input data-rep="" type="text" placeholder="优惠名称" name="name" datatype="" nullmsg="请输入优惠名"></span>
										<span> 价格 :<input type="text" placeholder="价格" name="price" datatype="n" nullmsg="请输入优惠价格"></span>
										<span> 优惠内容 :<input type="text" placeholder="优惠内容" name="content" datatype="" nullmsg="请输入优惠内容"></span>
											<button class="btn btn-success" data-query-insert="" >添加</button>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<form id="companyFavorableForm" action="/companyFavorable/batchUpdateCompanyFavorable.do" method="post" data-ajax="" dataType="json" async="true">
									<table table-name="" class="table table-striped table-bordered table-hover" >
										<thead>
											<tr>
												<th>名称</th>
												<th>价格 </th>
												<th>优惠内容 </th>
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
									<button class="btn btn-success" type="button" data-form="companyFavorableForm" batch-update="" >批量更新</button>
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
