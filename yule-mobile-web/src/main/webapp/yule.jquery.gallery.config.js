var colorbox_params = {
	rel: 'colorbox',
	reposition:true,
	scalePhotos:true,
	scrolling:false,
	previous:'<i class="ace-icon fa fa-arrow-left"></i>',
	next:'<i class="ace-icon fa fa-arrow-right"></i>',
	close:'&times;',
	current:'{current} of {total}',
	maxWidth:'100%',
	maxHeight:'100%',
	onOpen:function(){
		$overflow = document.body.style.overflow;
		document.body.style.overflow = 'hidden';
	},
	onClosed:function(){
		document.body.style.overflow = $overflow;
	},
	onComplete:function(){
		$.colorbox.resize();
	}
};
$(function(){
	var $overflow = '';
	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").append("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
	
	$("#company").find("a[data-remove]").on('click',function(){
		var tar = $(this);
		$.ajax({
			url:"/companyGallery/deleteCompanyGallery.do",
			type:"post",
			data:{"id":tar.attr("data-id")},
			dataType:"json",
			async:false,
			success:function(data){
				if(data.status){
					tar.parent().parent().remove();
				}
			}
		});
	});
	$("#product").find("a[data-remove]").on('click',function(){
		var tar = $(this);
		$.ajax({
			url:"/productGallery/deleteProductGallery.do",
			type:"post",
			data:{"id":tar.attr("data-id")},
			dataType:"json",
			async:false,
			success:function(data){
				if(data.status){
					tar.parent().parent().remove();
				}
			}
		});
	});
});