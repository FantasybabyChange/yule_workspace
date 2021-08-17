<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
<style type="text/css">
  .number{
     width: 50px;
  }
</style>
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>产品管理</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div>
						<form action="/product/findProduct.do" method="get">
							企业编号:<input name="id" class="text-input small-input" value="${companyId }" />
							<input type="submit" class="button" value="检索" />
						</form>
					</div>
					<div class="tab-content default-tab" id="tab1">
						<form action="/product/batchUpdateProduct.do" method="post" validform="">
							<input type="hidden" name="company_id" value="${companyId }" id="companyId" />
							<table>
								<thead>
									<tr>
										<th>产品名称</th>
										<th  width="30%">容纳人数(0-10)</th>
										<th  width="30%">最低消费</th>	
										<th  width="20%">操作</th>
									</tr>
								</thead>
							     ${htmls }
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" >
$(function(){
	trRow.privilegeHtml = '${privilegeHtml}';
	trRow.html = '${rowHtml}';
});
function insertObject(target){
	var tar = $(target);
	if(tar!=null&&tar!=undefined){
		var tr = tar.parent().parent();
		if(validtbody(tr)){
			var jsonData = '{';
			var inputs = tr.find("input");
			inputs.each(function(i){
				var id = $(this).attr("id");
				if(id!=""&&id!=null&&id!=undefined){
					jsonData += '"'+id+'":\"'+$(this).val()+'\",';
				}
			});
			jsonData = jsonData.substring(0,jsonData.length-1)+"}";
			$.ajax({
				url:tar.attr("data-url"),
				type:"post",
				dataType:"json",
				data:jQuery.parseJSON(jsonData),
				async:false,
				success:function(data){
					if(data.flag){
						tr.find("input[id='id']").val(data.id);
						tar.parent().html(trRow.privilegeHtml.replace(new RegExp("#{id}",'g'),data.id));
					}
				}
			});
		}
	}
}
var insertPrivilegeHtml = '${insertPrivilegeHtml}';
function deleteProduct(target){
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