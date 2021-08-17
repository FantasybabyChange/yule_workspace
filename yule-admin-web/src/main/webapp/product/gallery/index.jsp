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
					<h3>产品图册</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="ablum-content">
						<div class="ablum-list">
							<form id="findProductGalleryForm" action="/productGallery/findProductGallery.do" method="get">
							<div class="upload-type-choice">上传至：
									${productSelectHtml}
                                <div class="upload-btn">
									<a href="#upload" rel="modal" 	><span class="create-btn">上传图片</span></a>
									<!-- <a data-trigger="modal" href="#upload" data-title="Modal title"><span class="create-btn" >上传图片</span></a> -->
								</div>
                            </div>
                            </form>
							${htmls }
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form id="insertGalleryForm" action="/productGallery/batchInsertProductGallery.do" method="post">
			<input type="hidden" name="id" value="${product.id }" />
			<input type="hidden" name="company_id" value="${product.company_id }" />  
	</form>
	<div id="upload" style="display: none">
		<iframe id="mainFrame" scrolling="auto" width="800" height="600" src="http://upload.yuleing.com/admin/upload.html"></iframe>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/facebox.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js"></script>
<script type="text/javascript">
	document.domain = "yuleing.com";
	$(function (){
		$('a[rel*=modal]').facebox();
		selectValue("#productId","${productId }");
    });
</script>
</body>
</html>