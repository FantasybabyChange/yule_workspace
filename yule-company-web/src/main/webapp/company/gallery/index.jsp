<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>图册管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
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
							<div class="row">
								<div class="col-xs-12">
									<p>
										<button class="btn btn-app btn-purple btn-sm" id="upload">
											<i class="ace-icon fa fa-cloud-upload bigger-200"></i>
											上传文件
										</button>
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<!-- PAGE CONTENT BEGINS -->
									<div>
										<ul class="ace-thumbnails clearfix" id="company">
											${companyGalleryHtmls}
											<!--  
											<li>
												<a href="http://static.yuleing.com/assets/images/gallery/image-3.jpg" data-rel="colorbox">
												<img alt="150x150" src="http://static.yuleing.com/assets/images/gallery/thumb-3.jpg" />
												<div class="text">
													<div class="inner">Sample Caption on Hover</div>
												</div>
												</a>
											</li>
											-->
										</ul>
									</div>
								</div>
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
		<form action="/companyGallery/batchInsertCompanyGallery.do" method="post" data-ajax="" dataType="json" async="true">
		</form>
		
		<%@ include file="/common/scripts.jsp" %>
		
		<!-- page specific plugin scripts -->
		<script src="http://static.yuleing.com/assets/js/jquery.colorbox-min.js"></script>
		<script src="http://static.yuleing.com/assets/js/bootbox.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/jquery.lazyload.min.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.ajaxform.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.gallery.config.js"></script>

		<script type="text/javascript">
			var id = '${company_id}';
			document.domain = "yuleing.com";
			var html = "<li data-load=\"#{load}\">";
			html += "<a href=\"#{href}\" data-rel=\"colorbox\">";
			html += "<img alt=\"150x150\" src=\"#{url}\" data-original=\"#{original_url}\" />";
			html += "<div class=\"text\">";
			html += "<div class=\"inner\">#{name}</div>";
			html += "</div>";
			html += "</a>";
			html += "<div class=\"tools tools-bottom\">";
			html += "<a href=\"javascript:;\" data-remove=\"\" data-id=\"#{id}\">";
			html += "<i class=\"ace-icon fa fa-times red\"></i>";
			html += "</a>";
			html += "</div>";
			html += "</li>";
			function getLength(){
				return $(".ace-thumbnails").find("li").length;
			}
			jQuery(function($) {
				ajaxform.beforeSend = function(data){
					var results = ajaxform.datas;
					var thumbnails = $(".ace-thumbnails");
					$.each(results,function(i,o){
						var href= o.path+o.system_name;
						var url = o.path+"150_150"+o.system_name;
						$(".ace-thumbnails").prepend(html.replace("#{href}",href).replace("#{url}",url).replace("#{original_url}",url).replace("#{name}",o.name).replace("#{load}",o.system_name));
					});
				};
				ajaxform.success = function(data){
					var thumbnails = $(".ace-thumbnails");
					$.each(data,function(i,o){
						var li = thumbnails.find("li[data-load='"+o.system_name+"']");
						li.find("a[data-id='#{id}']").attr("data-id",o.id);
						li.find('[data-rel="colorbox"]').colorbox(colorbox_params);
						li.removeAttr("data-load");
					});
				};
				$('#upload').on('click', function(){
					if($(".ace-thumbnails").find("li").length>=50){
						alert("最多只能上传50个图片!");
				    }else{
				    	var modal = '<div class="modal fade"><div class="modal-dialog" style="width:90%;height:auto; ><div class="modal-content"><div class="modal-body">';
						modal+='<iframe id="mainFrame" scrolling="auto" width="100%" height="800" src="http://upload.yuleing.com/company/upload.html"></iframe></div></div></div></div>';
						var modal = $(modal);
						modal.modal("show").on("hidden", function(){
							modal.remove();
						});
				    }
				});
				//$("img[data-original]").lazyload({effect:"fadeIn"});
			})
		</script>
	</body>
</html>
