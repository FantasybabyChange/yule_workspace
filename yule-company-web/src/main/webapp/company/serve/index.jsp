<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>服务设施管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />

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
							<div class="control-group">
							<form action="/companyServe/updateCompanyServe.do" method="post">
								<div class="checkbox">
								<label class="block">
								<input  class="ace check-all"    type="checkbox" /><span class="lbl">全选</span>
								</label>
								</div>
								<hr>
								${htmls }
								<div class="clear"></div>
								<br>
								<button  type="submit"  class="btn btn-xs btn-success" >更新</button>	<hr>
								</form>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
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
		
		<script type="text/javascript">
			jQuery(function($) {
				$('.check-all').click(function(){
					if ($(this).is(":checked")) {
		    			$('body').find('input[type="checkbox"]').prop('checked', true);
		    		}else{
		    			$('body').find('input[type="checkbox"]').prop('checked', false);
		    		}
				});
			});
		</script>
	</body>
</html>
