<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>地理位置信息</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="user-scalable=no,width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/select2.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-editable.css" />
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
									<div class="profile-user-info profile-user-info-striped">

										<div class="profile-info-row">
											<div class="profile-info-name"> 企业区域 </div>
											<div class="profile-info-value">
												<i class="fa fa-map-marker light-orange bigger-110"></i>
												省<select id="province" style=" width:100px;">
												<option >请选择</option>
												</select>
												市<select id="city" style="width:100px;">
													<option >请选择</option>
												</select>
												区<select id="county" data-id="area_county_id" style=" width:100px;">
												<option >请选择</option>
												</select>
												商区
												<select id="business" data-id="area_business_id" style=" width:100px;">
												<option >请选择</option>
												</select>
												<input type="hidden" id="companyUserId"  value="${companyAddressVO.id}"/>
											</div>
										</div>
										
										<div class="profile-info-row">
											<div class="profile-info-name"> 地址搜索 </div>
											<div class="profile-info-value">
													<input id="address_detail"  value="${companyAddressVO.address_detail}"/>
													<button id="seachAddress" class="btn btn-xs blue">搜索</button><span class="red"><i class="ace-icon fa fa-exclamation-circle"></i>确定位置后 请在地图上点击右键选择设为企业坐标</span>
											</div>
										</div>
									</div>										
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
								<div id="l-map" style="margin-left:11px;width:98%;height:400px;"></div>  
								</div><!-- /.col -->
							</div><!-- /.row -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->	
			
			
		<div id="addrress-wizard"  class="modal" >
			<div class="modal-dialog"  style="margin-top:15%;">
				<div class="modal-content" >
				<div class="modal-header" data-target="#modal-step-contents">
				<h3>确认详细地址</h3>
				</div>
					<div class="modal-body step-content"  id="modal-step-contents" style="height:100px;">
						<div class="step-pane active"   >
							详细地址:<input type='text' id='addressId'  style="width:100%;"data-id="address_detail" >
						</div>
					</div>
						<div class="modal-footer wizard-actions">
							<button class="btn btn-danger btn-sm pull-left"  type="button" data-dismiss="modal">
								<i class="ace-icon fa fa-times" ></i>
								取消
							</button>
							<button class="btn btn-info" id='changeDetail' type="button" data-dismiss="modal">
								<i class="ace-icon fa fa-check bigger-110" ></i>
								确定
							</button>
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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script>
		<script type="text/javascript" src="http://static.yuleing.com/company/js/baiduMap.js"></script>
		<script src="http://static.yuleing.com/assets/js/select2.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/x-editable/ace-editable.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/bootbox.min.js"></script>
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				$.fn.editable.defaults.mode = 'inline';
				$.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
			    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
			                                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>'; 
			   inforWindow.image_path = '${companyUser.image_path}';
			   inforWindow.company_name = '${companyUser.name}';
			   inforWindow.provinceText = "${companyAddressVO.area_province_name}";
			   inforWindow.cityText = "${companyAddressVO.area_city_name}";
			   inforWindow.countyText = "${companyAddressVO.area_county_name}";
			   inforWindow.businessText = "${companyAddressVO.area_business_name}";
			   inforWindow.addressDetailText='${companyAddressVO.address_detail}';
			   inforWindow.initInfoWindow();
			   initMap("${companyAddressVO.lng}","${companyAddressVO.lat}");
			   
				$.ajax({
			    	url:"/area/findProvince.do",
			    	type:"post",
			    	async:false,
			    	dataType:"json",
			    	success:function(data){
			    		if(data!=null&&data.length>0){
								var optionHtml = '';
								optionHtml = '<option >请选择</option>';
			    			$.each(data,function(i){
			    				if(inforWindow.provinceText === data[i].text){
			    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].text+'省'+"</option>";
			    					setCity(data[i].id);
			    				}else{
			    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].text+'省'+"</option>";
			    				}
			    				
							});
			    			$("#province").html(optionHtml);
			    			$("#province").select2();
					    }
			    	}
			    });
			    function setCity(value){
			    	if(value!=""){
						$.ajax({
					    	url:"/area/findCity.do",
					    	type:"post",
					    	data:{"provinceId":value},
					    	dataType:"json",
					    	success:function(data){
					    		if(data!=null&&data.length>0){
					    			var optionHtml = '<option >请选择</option>';
					    			$.each(data,function(i){
					    				if(inforWindow.cityText === data[i].text){
					    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].text+'市'+"</option>";
					    					setCounty(data[i].id);
					    					setBusiness(data[i].id);
					    				}else{
					    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].text+'市'+"</option>";
					    				}
					    				
									});
					    			$("#city").html(optionHtml);
					    			$("#city").select2();
							    }
					    	}
					    });
					}
			    }
			    function setCounty(value){
			    	if(value!=""){
				    	$.ajax({
					    	url:"/area/findCounty.do",
					    	type:"post",
					    	data:{"cityId":value},
					    	async:false,
					    	dataType:"json",
					    	success:function(data){
					    		if(data!=null&&data.length>0){
					    			var optionHtml = '<option selected value="">请选择</option>';
					    			$.each(data,function(i){
					    				if(inforWindow.countyText === data[i].text){
					    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].text+"</option>";
					    				}else{
					    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].text+"</option>";
					    				}
									});
					    			$("#county").html(optionHtml);
					    			$("#county").select2();
							    }
					    	}
					    });
			    	}
			    }
				function setBusiness(value){
					if(value!=""){
				    	$.ajax({
					    	url:"/area/findBusiness.do",
					    	type:"post",
					    	data:{"cityId":value},
					    	async:false,
					    	dataType:"json",
					    	success:function(data){
					    		if(data!=null&&data.length>0){
					    			var optionHtml = '<option selected value="">请选择</option>';
					    			$.each(data,function(i){
					    				if(inforWindow.businessText === data[i].text){
					    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].text+"</option>";
					    				}else{
					    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].text+"</option>";
					    				}
									});
					    			$("#business").html(optionHtml);
					    			$("#business").select2();
					    		}
					    	}
					    });
			    	}
			    }
				 
				$('#province').change(function(i,o){
					inforWindow.provinceText = $(this).find('option:selected').text();
					if(inforWindow.provinceText != '请选择'){
					setCity($(this).val());
					initCountryAndBusiness();
					changeMap(isNullWord(inforWindow.provinceText),null,5);
					}else{
						inforWindow.provinceText = '';
					}
				});
				 
				$('#city').change(function(i){
					inforWindow.cityText =  $(this).find('option:selected').text();
					if(inforWindow.cityText != '请选择'){
					setCounty($(this).val());
					setBusiness($(this).val());
					changeMap(isNullWord(inforWindow.provinceText)+isNullWord(inforWindow.cityText),isNullWord(inforWindow.cityText),10);
					}else{
						inforWindow.cityText = '';
					}
				});
			    
				$('#county').change(function(i){
					inforWindow.countyText =  $(this).find('option:selected').text();
					if(inforWindow.countyText != '请选择'){
					changeMap(isNullWord(inforWindow.provinceText)+isNullWord(inforWindow.cityText)+isNullWord(inforWindow.countyText),isNullWord(inforWindow.cityText),13);
					commonSubmit($('#county'),$(this).val());
					}else{
					inforWindow.countyText = '';
					}
					
				});
				$('#business').change(function(i){
					inforWindow.businessText = $(this).find('option:selected').text();
					if(inforWindow.businessText != '请选择'){
						commonSubmit($('#business'),$(this).val());
					}else{
						inforWindow.businessText = '';
					}
				});
				$('#seachAddress').click(function(){
					inforWindow.addressDetailText = $('#address_detail').val();
					changeMap(isNullWord(inforWindow.provinceText)+isNullWord(inforWindow.cityText)+isNullWord(inforWindow.addressDetailText),isNullWord(inforWindow.cityText),18);
					});
			    
			    $('#changeDetail').click(function(e, params) {
			    	inforWindow.addressDetailText =  $('#addressId').val();
					if(newlng === ''|| newlat === ''){
						bootbox.alert('请选择新的坐标');
						return false;
					}else if(inforWindow.addressDetailText === ''){
						bootbox.alert('请输入详细地址');
						return false;
					}else{
					commonSubmit(null,newlng,newlat,inforWindow.addressDetailText);
					}
			    });
			});
			//如果tar为空  value为经度 value1为维度
			function commonSubmit(tar,value,value1,detail){
				var jsonData ="";
				var companyUserId = $('#companyUserId').val();
				var isDetails = false;
				if(tar){
					jsonData= '{ "id":"'+companyUserId+'","'+tar.attr("data-id")+'":"'+value+'"}';	
				}else{
					jsonData= '{ "id":"'+companyUserId+'","lng":"'+value+'","lat":"'+value1+'","address_detail":"'+detail+'"}';
					isDetails = true;
				}
				$.ajax({
					url:'/companyAddress/updateCompanyAddress.do',
					type:"post",
					dataType:"json",
					data:jQuery.parseJSON(jsonData),
					async:false,
					success:function(data){
						if(data != null && data.status === true && isDetails === true){
							inforWindow.initInfoWindow();
							initMap(newlng,newlat);
							newlng = null;
							newlat = null;
							$('#address_detail').val($('#addressId').val());
							bootbox.alert('修改位置成功');
						}else if(isDetails === true){
							bootbox.alert('修改详细地址失败!');
						}
					}
				});
			}
	function initCountryAndBusiness(){
				var optionHtml = '<option selected value="">请选择</option>';
				$('#business').html(optionHtml);
				$('#business').select2();
				$('#county').html(optionHtml);
				$('#county').select2();
			}
			function isNullWord(vale){
				if(vale === '请选择'){
					return '';
				}
				return vale;
			}
		</script>
	</body>
</html>
