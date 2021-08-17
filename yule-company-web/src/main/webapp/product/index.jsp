<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>产品管理</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<%@ include file="/common/styles.jsp" %>
		<link rel="stylesheet" href="http://static.yuleing.com/css/uploadify.css" type="text/css" media="screen" />
		
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
									<form  id="productForm" action="/product/batchUpdateProduct.do" method="post" data-ajax="" dataType="json" async="true">
									<table  class="table table-striped table-bordered table-hover" >
										<thead>
											<tr>
												<th>名称</th>
												<th>可容纳人数 </th>
												<th>最低消费 </th>
												<th>操作</th>
											</tr>
										</thead>
										<tfoot>
										</tfoot>
											${htmls }
									</table>
									</form>
									<button class="btn btn-success" data-form="productForm" batch-insert="" type="button" >批量更新</button>
								</div>
							</div><!-- /.row -->
						</div>
					</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<div id="product-face-wizard" class="modal">
				<div class="modal-dialog">
					<div class="modal-content" style="margin-top:30%;height:80%;width:70%;">
						<div class="modal-body step-content" id="modal-step-contents" style="text-align:center;">
									<div class="profile-info-row">
										<div class="profile-info-name"> 产品封面</div>
										<div class="profile-info-value">
										</div>
									</div>

									<div class="profile-info-row">

										<div class="profile-info-value">
											<input id="product_face"  type="file"  data-upload="" />
											<img src="" id="product_face_image">
										</div>
									</div>
						</div>
																		
					</div>
				</div>
			</div>
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
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>
		<script language="javascript" type="text/javascript">
			document.write("<script type='text/javascript' "
					+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date().getTime()
					+ "'></s" + "cript>");
		</script>
		<script type="text/javascript">
		var  product_id ='';
		var company_id='${companyId}';
		$(function(){
			var form ;
			var trs;
			trRow.privilegeHtml='${privilegeHtml}';
			$("button[batch-insert]").click(function(){
				form=$('#'+$(this).attr("data-form"));
				trs ='';
				form.find("tr").each(function(){
					var tr = $(this);
					var person_num = tr.find("input[name='person_num']");
					if(person_num.val()==''){
						person_num.removeAttr("value");
						tr.find("input[name='min_expense']").removeAttr("value");
						trs += "<tr>"+tr.html()+"</tr>";
						tr.remove();
					}
				});
				if(validator.valid(form)){
					form.submit(ajaxform.ajaxSubmit(form));
/* 					$("#list").find("input[name='person_num']").each(function(){
						if($(this).val()==''){
							$(this).parent().parent().remove();
						}
					});
 */					$("#list").append(trs);
				}
			});
			ajaxform.success = function(data){
				if(data!=null&&data!=''&&data!=undefined){
				var trs=form.find("tr");
					$(data).each(function(){
						var obj=$(this);
						var tr=trs[parseInt(obj.attr("order"))+2];
						var btn = $(tr).find("button");
						btn.parent().html(trRow.privilegeHtml.replace(new RegExp("#{id}",'g'),obj.attr("id")));
						$(tr).find("input[name='id']").val(obj.attr("id"));
					}); 
				}
			};
			ajaxform.beforeSend = function(){
				
			};
			ajaxform.complete = function(){
				
			};
		});
		var insertPrivilegeHtml = '${insertPrivilegeHtml}';
		$("tbody").on("click","a[product-face]",function(){
			product_id =$(this).attr("modal-dialog");
			$("#product_face_image").attr("src","http://images.yuleing.com/company/"+company_id+"/product/"+product_id+"/150_150product_face.jpg");
			setTimeout(inituploadify(),100);
		});
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
							tar.parent().parent().parent().find("input").each(function(){
								var id = $(this).attr("name");
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
		var errorCodes = ["-100","-110","-120","-130"];
		var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(10MB)","零字节的文件","无效的文件类型"];
		var fileSystemName = "";	
		var fileTypeExts = "*.jpg;*.png;*.gif;*.jpeg";
		var flashPath = "/swf/uploadify.swf" ;
		var uploadifyPath = "http://upload.yuleing.com/companyProductImage/upload.do";
		var uploadLimit = 0;
		var queueSizeLimit = 5;
		var fileSizeLimit = "2MB";
	
		var imageTitle = "点击查看原图"; 
		//页面初始化方法
		function inituploadify(){
			$("#product_face").uploadify({
		        'swf':flashPath,
		        'fileObjName':'files[]',
		        'uploader':uploadifyPath,
		        'auto': true,
		        'removeTimeout':0,
		        'multi': false, //多个文件
		        'uploadLimit':uploadLimit,
		        'fileSizeLimit':fileSizeLimit,
		        'fileTypeDesc' : '图片文件(*.jpg;*.png;*.gif;*.jpeg)',
		        'buttonText': '点击上传',
		        'fileTypeExts':fileTypeExts,
		        'progressData':'percentage',
		        'speed':'percentage',
		        'queueSizeLimit':queueSizeLimit,
		        'removeCompleted':true,
		        'onSelect': function (file) {
		        	this.queueData.filesErrored=0;
		        },
		        'onOpen':function(event,ID,fileObj){
		        	//alert(ID);
		        },
		        'onSelectError':function(file,errorCode,errorMsg){
		        	for(var i=0;i<errorCodes.length;i++){
		        		if(errorCodes[i]==errorCode){
		        			this.queueData.errorMsg = errorMsgs[i];
		        		}
		        	}
		        },
		        'onCancel':function(file){
		        	//alert(file.name);
		        },
		        'onFallback':function(){
		        	alert("浏览器不能兼容Flash,请下载最新版!");
		        },
		        'onClearQueue':function(queueItemCount) {
		        	alert("清除上传完成"+queueItemCount);
		        },
		        'onUploadStart':function(file){
		        	var name = this.wrapper.attr("id");
		        	var id =this.wrapper.selector;
		    		var formData ={"params":'{"upload_dir":"/company/'+company_id+'/product/'+product_id+'/","upload_name":"product_face","noinit":[{"name":"50_50","is_watermark":"false","keep":"true"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'};
		    		$(id).uploadify("settings","formData",formData);
		        },
		        'onUploadSuccess':function(file,data,response){
		        	var temp=eval(data);
		        	var image_path = "http://images.yuleing.com/company/"+company_id+"/product/"+product_id+"/150_150product_face.jpg"+"?t="+new Date().getTime();
		        	var id =this.wrapper.selector;
		        	$(id).uploadify('settings', 'buttonText', '正在加载');
		        	$(id+"_image").attr("src",image_path);
		        	$(id).uploadify('settings', 'buttonText', '重新上传');

		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert("这个文件: " + file.name + " 不能被上传原因: " + errorString);
		        	 switch (errorMsg){
		             case '400':
		                 $('#'+file.id).find('.data').html(" - 上传失败，文件超过大小限制(2MB)");
		                 break;
		             case '401':
		                 $('#'+file.id).find('.data').html(" - 上传失败，零字节的文件");
		                 break;
		             case '402':
		                 $('#'+file.id).find('.data').html(" - 上传失败，无效的文件类型");
		                 break;
		             case '500':
		                 $('#'+file.id).find('.data').html(" - 上传失败，服务器问题");
		                 break;
		         }
		    	},
		    	'onDialogClose':function(queueDat){
		    		this.queueData.files;
		    	}
		    });
		}
		</script>
	</body>
</html>
