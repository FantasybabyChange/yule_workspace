<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>产品最低消费</h3>
					<a href="/product/findProduct.do" class="goback-btn button">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="add-type-content clearfix">
					    ${htmls }
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript">
$(function() {
	$('.add-type-btn:eq(0)').click(function(event) {
		var productExpenseLength=$(".add-type-part").length;
		var typeTpl = '<div class="add-type-part"><div class="add-type-close">×</div><input type="hidden" name="productExpense['+productExpenseLength+'].id" value="" />'
			+ '<div class="add-type-title">消费名称</div>'
			+ '<input type="text" name="productExpense['+productExpenseLength+'].name"  class="text-input" />'
			+ '<div class="add-type-title">消费金额</div>'
			+ '<input type="text" name="productExpense['+productExpenseLength+'].min_expense"  class="text-input" />'
			+ '</div>'
		$(this).before(typeTpl);
	});
	$('.add-type-close').live('click',function(){
		  var productExpenseLength=$(".add-type-part").length;
		  if(productExpenseLength>1){
			  $(this).closest('.add-type-part').fadeOut('fast', function() {
	    	      $(this).remove()
		      });
		  }
	     
	  })
  });
</script>
</body>
</html>