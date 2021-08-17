<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>企业</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>企业交通地理</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
				<div class="tab-content default-tab" id="tab1">
								<input type="hidden" name="id" value="${companyAddressVO.id }" />
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
											<br/>
											<div>
											<div class="profile-info-name"> 地址搜索 </div>
												<div class="profile-info-value">
													<input id="address_detail"  value="${companyAddressVO.address_detail}"/>
													<button id="seachAddress" class="btn btn-xs blue">搜索</button><span style="color:red;">确定位置后 请在地图上点击右键选择设为企业坐标</span>
											</div>
											</div>
									<div id="l-map" style="margin-left:11px;width:98%;height:400px;"></div>
							<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" charset="UTF-8"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.area.js" charset="UTF-8"></script>
<script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js" ></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/baiduMap.js"></script>
<script type="text/javascript">
jQuery(function($) {
   inforWindow.image_path = '${image_path}';
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
    				if(inforWindow.provinceText === data[i].name){
    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].name+'省'+"</option>";
    					setCity(data[i].id);
    				}else{
    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].name+'省'+"</option>";
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
		    				if(inforWindow.cityText === data[i].name){
		    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].name+'市'+"</option>";
		    					setCounty(data[i].id);
		    					setBusiness(data[i].id);
		    				}else{
		    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].name+'市'+"</option>";
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
		    				if(inforWindow.countyText === data[i].name){
		    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].name+"</option>";
		    				}else{
		    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
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
		    				if(inforWindow.businessText === data[i].name){
		    					optionHtml += "<option selected value='"+data[i].id+"'>"+data[i].name+"</option>";
		    				}else{
		    					optionHtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
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
		initCountryAndBusiness();
		setCity($(this).val());
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
				$('#address_detail').val(inforWindow.addressDetailText);
				alert('修改位置成功');
			}else if(isDetails === true){
				alert('修改详细地址失败!');
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