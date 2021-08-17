<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>用户权限配置</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		
		<link rel="stylesheet" href="http://static.yuleing.com/company/css/yule.checkbox.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />

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
				
				<div class="breadcrumbs" id="breadcrumbs">
					<a href="/companyUser/findCompanyUser.do">返回</a>
				</div>
				<div class="page-content">
							<form action="/companyUser/insertCompanyUserPrivilege.do" method="post">
			   			<input type="hidden" name="companyId" value="${companyId }"/>
					    <input type="hidden" name="company_user_id" value="${companyUserId }"/>
				        ${htmls }
				    <input class="button" type="submit" value="更新" />
				</form>
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
		<script src="http://static.yuleing.com/company/js/collapse.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			  	$('[data-trigger="collapse"] input[type="checkbox"]').click(function(event) {
		            if ($(this).is(":checked")) {
		                $(this).parent().parent().find('input[type="checkbox"]').prop('checked', true);
		            } else {
		                $(this).parent().parent().find('input[type="checkbox"]').prop('checked', false);
		            }
		        });
		        $('input[type="checkbox"]').click(function(event) {
		            if ($(this).is(":checked")) {
		                $(this).closest('.type-list').find('[data-trigger="collapse"] input[type="checkbox"]').prop('checked', true);
		                $(this).closest('.first-list').find('.alt-row input[type="checkbox"]').prop('checked', true);
		            } else {
		                if ($(this).closest('.type-list .collapsible').find(' input[type="checkbox"]:checked').length == 0) {
		                    $(this).closest('.type-list').find('[data-trigger="collapse"] input[type="checkbox"]').prop('checked', false);
		                }
		                if ($(this).closest('.type-list-part').find('input[type="checkbox"]:checked').length == 0) {
		                    $(this).closest('.first-list').find('.alt-row input[type="checkbox"]').prop('checked', false);
		                }
		            }
		        });
			});
		</script>
	</body>
</html>
