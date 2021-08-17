$(function () {

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
    	//autoUpload: true,//是否自动上传
    	type:"post",
    	iframe: false,
    	url:"/adminGallery/upload.do",
    	formData:{"params":'{"init":[{"name":"150_150","is_watermark":"false","keep":"true"}],"noinit":[{"name":"50_50","is_watermark":"false","keep":"true"},{"name":"860_460","is_watermark":"true","keep":"false"}]}'},
        dataType: "json",  
        singleFileUploads:false,
        maxNumberOfFiles:9999,
        limitMultiFileUploads:9999,
        //limitMultiFileUploadSize:99999999,
        sequentialUploads: true,
        previewCrop:true,
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        //disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
        maxFileSize: 2000000,
        minFileSize: 500000,
        //acceptFileTypes: /(\.|\/)(gif|jpe?g|png|jpg|gif)$/
    });
    $('#fileupload')
    .bind('fileuploadadd', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadsubmit', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadsend', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploaddone', function (e, data) {
    	var results = data.result;
    	//console.log(results);
    	if(results!=null&&results.length>0){
    		var htmls = "";
    		for(var i=0;i<results.length;i++){
    			var result = results[i];
    			console.log(result);
    			htmls += "<input type=\"hidden\" name=\"name\" value=\""+result.name+"\" />";
    			htmls += "<input type=\"hidden\" name=\"system_name\" value=\""+result.system_name+"\" />";
    			htmls += "<input type=\"hidden\" name=\"path\" value=\""+result.path+"\" />";
    			htmls += "<input type=\"hidden\" name=\"type\" value=\""+result.type+"\" />";
    			htmls += "<input type=\"hidden\" name=\"size\" value=\""+result.size+"\" />";
	    	}
    		var form = $("#insertGalleryForm", window.parent.document);
    		form.append(htmls);
	    	form.submit();
    	}
    	//console.log(data.result);
    })
    .bind('fileuploadfail', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadalways', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadprogress', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadprogressall', function (e, data) {
    	//console.log(data.result);
    })
    .bind('fileuploadstart', function (e) {
    	//console.log("start");
    })
    .bind('fileuploadstop', function (e) {
    	//window.parent.location.reload();
    	//console.log("stop");
    })
    .bind('fileuploadchange', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploadpaste', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploaddrop', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploaddragover', function (e) {
    	console.log(data.result);
    })
    .bind('fileuploadchunksend', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploadchunkdone', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploadchunkfail', function (e, data) {
    	console.log(data.result);
    })
    .bind('fileuploadchunkalways', function (e, data) {
    	console.log(data.result);
    }).bind('fileuploadStart', function (file) {
    	console.log("wwww"+data.result);
    });
});
