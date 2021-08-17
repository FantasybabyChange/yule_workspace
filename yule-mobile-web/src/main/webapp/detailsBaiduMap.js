var infoWindow = null;
var map = null;
var loadingIndex ;
//消息窗口
//初始化地图
function initMap(cityName){
	map = new BMap.Map("l-map");
	var myGeo = new BMap.Geocoder();
	map.clearOverlays();
	var opts = {anchor: BMAP_ANCHOR_TOP_RIGHT}
	map.centerAndZoom(cityName, 11);
	map.enableScrollWheelZoom(); //启用滚轮放大缩小
	map.addControl(new BMap.NavigationControl(opts));    
	map.addControl(new BMap.ScaleControl());
}

//信息框对象
inforWindow={
		image_path : '',
		company_name : '',
		addressDetailText:'',
		getInfoWindow:function(){
			oldAddress = this.addressDetailText;
			var companyContent ='<strong>'+this.company_name+'</strong><div>'
		    +'<span style="float:left;"><img id="companyImg" src="'+this.image_path+'" data-trigger="preview"  width="50" height="50"></span>'
		    +'<div>'
		    +'详细地址:'+this.addressDetailText+'</div>'
		    +'</div>';   
		infoWindow = new BMap.InfoWindow(companyContent,{offset:new BMap.Size(0,-20)});  // 创建信息窗口对象
		}
	}
//标注对象
markerObject={
		 lastMarker:null,
		 company_id:null,
		 blueMarkerIcon : new BMap.Icon("http://static.yuleing.com/www/images/mapIconHotel.png", new BMap.Size(32,39)),
		 redMarkerIcon : new BMap.Icon("http://static.yuleing.com/www/images/mapIconHotel2.png", new BMap.Size(32,39)),
		 getLable : function(value){
				return  new BMap.Label(value,{offset:new BMap.Size(20,-10)});
			},
		 addMarker: function(point,title,companyId){
			  var marker = new BMap.Marker(point);
			  map.addOverlay(marker);
			  if(markerObject.company_id === companyId){
				  markerObject.lastMarker = marker;
				  marker.setIcon(markerObject.blueMarkerIcon);
				  markerObject.getCompanyInfo(companyId);
				  map.openInfoWindow(infoWindow,point);
			  }else{
				  marker.setIcon(markerObject.redMarkerIcon);
			  }
			  marker.setTitle(title);
			  marker.companyId = companyId;
			  marker.addEventListener("click",this.click);
			 
		},
		click:function (e){
			if(this != markerObject.lastMarker){
			this.setIcon(markerObject.blueMarkerIcon);
			markerObject.getCompanyInfo(this.companyId);
			if(markerObject.lastMarker != null){
				markerObject.lastMarker.setIcon(markerObject.redMarkerIcon);
			}
			markerObject.lastMarker = this;
			//this.openInfoWindow(infoWindow);
			}
		},
		initMapMarker:function(cityId,cityName){
			if(cityId === '' || cityId === null){
				cityId = '610100';
			}
			if(!cityName){
				cityName = '西安';
			}
			$.ajax({
				url:'/companyDetails/searchCompanyMap.do',
				data:{'area_city_id':cityId},
				type:'post',
				dataType:"json",
				async:false,
				success:function(data){
				if(null!=data){
				var markers = data.companyMarker;
				initMap(cityName);
				$.each(markers,function(i){
					var lng = markers[i].lng;
					var lat = markers[i].lat;
					var companyId = markers[i].companyId;
					var companyName = markers[i].companyName;
					var point = new BMap.Point(lng, lat);
					markerObject.addMarker(point,companyName,companyId);
				});
				}
				}
			});
		},
		getMarkerById:function(id){
			var allOverlay = map.getOverlays();
			for (var i = 0; i < allOverlay.length -1; i++){
				if(allOverlay[i].companyId == id){
					return allOverlay[i];
				}
			}
			
		},getCompanyInfo:function(companyId){
			$.ajax({
				url:'/companyDetails/searchCompanyById.do',
				data:{'area_city_id':"610100",'companyId':companyId},
				type:'post',
				dataType:"json",
				async:false,
				success:function(data){
				if(null!=data){
					//inforWindow.image_path=data.image_path;
					inforWindow.company_name=data.companyName;
					inforWindow.addressDetailText= data.companyAddress;
					var htmlInfo = '<h4>'+inforWindow.company_name+'</h4>'
								+'<p>'+inforWindow.addressDetailText+'</p>'; 
					$('#infoSpan').html(htmlInfo);
					//inforWindow.getInfoWindow();
					}
				}
			});
		}
	}
