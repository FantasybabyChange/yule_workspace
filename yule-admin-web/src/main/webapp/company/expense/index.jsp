`<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>企业消费管理</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" >
					<form data-query-form="" action="/companyExpense/findCompanyExpense.do" method="get">
					<input type="hidden" name="companyId" value="${companyExpenseQuery.company_id}"/>
					${select}
					</form>
					<form >
							<table>
									<thead>
										<tr>
											<th>名称</th>
											<th>价格</th>
											<th>操作</th>
										</tr>
									</thead>
									${htmls}
							</table>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js" ></script>
<script type="text/javascript" >
$(function(){
	trRow.privilegeHtml = '${privilegeHtml}';
	trRow.html = '${rowHtml}';
	/* selectValue("select[name='companyExpenseCategory']","${companyExpenseQuery.expense_category_id}"); */
	$("#companyExpenseCategory").on('change',function(){
		location.href="/companyExpense/findCompanyExpense.do?companyId="+'${companyExpenseQuery.company_id}'+"&expense_category_id="+$(this).val();		
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
					tar.parent().parent().find("input").each(function(){
						var id = $(this).attr("id");
						if(id!=""&&id!=null&&id!=undefined&&id!="company_id"&&id!="expense_category_id"){
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