<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<h3>${account }权限配置</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
					<div class="clear"></div>
			   </div>
				<div class="content-box-content">
				<h3>全选:<input id="chooseAll" name="checkAll" type="checkbox"/></h3>
				   <div class="type-content">
					<form action="/companyUser/insertCompanyUserPrivilege.do" method="post">
					<fieldset>
					<input type="hidden" name="companyId" value="${companyId }"/>
				    <input type="hidden" name="company_user_id" value="${companyUserId }"/>
			     	   ${htmls }
				    <input class="button" type="submit" value="更新" />
					</fieldset>
					</form>
				   </div>
				    
				</div>
	   		</div>
	   	</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript">
 $(function(){
	 $('[data-trigger="collapse"] input[type="checkbox"]').click(function(event) {
		 if($(this).attr('name') != 'checkAll'){
	        if ($(this).is(":checked")) {
	            $(this).parent().parent().find('input[type="checkbox"]').attr('checked', 'checked');
	        } else {
	            $(this).parent().parent().find('input[type="checkbox"]').removeAttr('checked');
	        }
		 }
	 });
	    $('input[type="checkbox"]').click(function(event) {
	    	if($(this).attr('name') != 'checkAll'){
	        if ($(this).is(":checked")) {
	            $(this).closest('.type-list').find('[data-trigger="collapse"] input[type="checkbox"]').attr('checked', 'checked');
	            $(this).closest('.first-list').find('.alt-row input[type="checkbox"]').attr('checked', 'checked');
	        } else {
	            if($(this).closest('.type-list .collapsible').find(' input[type="checkbox"]:checked').length==0){
	                  $(this).closest('.type-list').find('[data-trigger="collapse"] input[type="checkbox"]').removeAttr('checked');
	            }
	            if($(this).closest('.type-list-part').find('input[type="checkbox"]:checked').length==0){
	                 $(this).closest('.first-list').find('.alt-row input[type="checkbox"]').removeAttr('checked');
	             }
	        	}
	    	}else{
	    		if ($(this).is(":checked")) {
	    			$('body').find('input[type="checkbox"]').attr('checked', 'checked');
	    		}else{
	    			$('body').find('input[type="checkbox"]').removeAttr('checked');
	    		}
	    		
	    	}
	    	});
 });
</script>
</body>
</html>
