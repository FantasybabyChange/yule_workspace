<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css">
<style>
.div_txt {
	border: 1px solid #c3c3c3;
	width: 90%;
	font-family: Tahoma;
}
</style>
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>消息${parentIdtest}</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1" >
						<div>
							<form action="/adminMessage/findAdminMessage.do" method="get" data-query-form="">	
								<span>消息标题:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="text-input " name="title" value="${adminMessageMongoQuery.title }" /></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否查看<select name="is_read"><option  value="">全部</option><option  value="0" >已查看</option><option  value="1">未查看</option></select>
								<br/>
								<span>创建开始时间:<input type="text" class="text-input  datetimepicker" name="start_time" value="${adminMessageMongoQuery.start_time }" /></span>
								<span>创建结束时间:<input type="text" class="text-input  datetimepicker" name="end_time" value="${adminMessageMongoQuery.end_time }" /></span>
								
								<input type="submit" class="button" value="检索">
							</form>
						</div>
						<table>
							<thead>
								<tr>
									<th><input class="check-all" type="checkbox" /></th>
									<th>消息标题</th>
									<th>消息状态</th>
									<th>创建时间</th>
									<th>是否删除</th>
									<th>操作</th>
								</tr>
							</thead>
							${htmls }
						</table>
					</div>
					<div class="tab-content" id="tab2" style="display: none;">
						<form action="/adminMessage/insertAdminMessage.do" method="post" validform="">
							<fieldset>
								<div>
									<label>消息接收者</label> <input type="hidden" />
									<div id="fatherDiv" class="div_txt">
									<input id="keyword" type="text" class="float:left;"
											style="border:none;width:100%" />
									</div>
								</div>
								<div style="margin-top: 10px;">
									<label>消息标题</label> <input class="text-input small-input"
										type="text" name="title" nullmsg="请输入消息标题!" datatype="">
								</div>
								<div style="margin-top: 10px;">
									<label>消息内容</label>
									<textarea name="content" cols="79" rows="15" nullmsg="请输入消息内容!" datatype=""></textarea>
								</div>
								<p>
									<input class="button" type="submit" value="发送" />
								</p>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
	<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
	<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
	<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
	<script type="text/javascript" src="http://static.yuleing.com/js/jquery.autocomplete.min.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
	<script type="text/javascript">
	selectValue("select[name='is_read']","${adminMessageMongoQuery.is_read}");
		$(function() {
			$("form[validform]").submit(function(){
				var keyvalue = $('#keyword').val();
				var datalength = $('div[data-keyword]').length
				if( datalength === 0 && keyvalue.length === 0){
					$('#fatherDiv').after('<span class="input-notification error png_bg">请选择发送的企业</span>');
					return false;	
				}else if(datalength === 0 && keyvalue.length > 0){
					$('#keyword').next().remove();
					$('#fatherDiv').after('<span class="input-notification error png_bg">没有找到输入的企业</span>');
					return false;	
				}else{
					$('#keyword').next().remove();
					return true;
				}
			});
			$("#fatherDiv").click(function() {
				$("#keyword").focus();
			});
			$("#keyword").click(function() {
				$('div[data-keyword]').css({
					background : 'white',
					color : 'black'
				});
				$('div[data-keyword="del"]').attr("data-keyword", "");
			});
			$("#fatherDiv").keydown(function(event) {
				if(event.keyCode===32){
					return false;
				}
				var keyword = $("#keyword");
				keywordValue = keyword.val();
				switch (event.keyCode) {
				case 8:
					var delDiv = $('div[data-keyword="del"]');
					if (delDiv.length === 0
							&& (keywordValue === null
									|| keywordValue.length === 0 || keywordValue === '')) {
						var preKeywordDiv = keyword.prev();
						preKeywordDiv.css({
							color : "white",
							background : "black"
						});
						preKeywordDiv.attr("data-keyword","del");
					} else {
						delDiv.remove();
					}
					break;
				case 46:
					$('div[data-keyword="del"]').remove();
					break;
				default:
					$('div[data-keyword]').css({
						background : 'white',
						color : 'black'
					});
					$('div[data-keyword="del"]').attr("data-keyword", "");
					break;
					
				}
			});
			$('div[data-keyword]').live('click', function() {
				$('div[data-keyword]').css({
					background : 'white',
					color : 'black'
				});
				$('div[data-keyword]').attr("data-keyword", "");
				$(this).css({
					color : "white",
					background : "black"
				});
				$(this).attr("data-keyword", "del");
			});
			var companys = jQuery.parseJSON('${usersJson}');
			var jsons = "[";
			$.each(companys, function(i, o) {
				jsons += '{"label":"' + decodeURIComponent(o.label)
						+ '","value":"' + o.value + '","pinyin":"' + o.pinyin
						+ '"},';
			});
			if (jsons.length > 1) {
				jsons = jsons.substring(0, jsons.length - 1);
			}
			jsons += "]";
			$('#keyword').autocomplete(jQuery.parseJSON(jsons), {
				max : 10, //列表里的条目数
				minChars : 0, //自动完成激活之前填入的最小字符
				width : 400, //提示的宽度，溢出隐藏
				scrollHeight : 300, //提示的高度，溢出显示滚动条
				matchContains : true, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
				multiple : true,
				selectFirst : false,
				multipleSeparator : ";",
				//mustMatch : true,
				//autoFill : false, //自动填充
				formatItem : function(row, i, max) {
					if (null != row.label && row.label != undefined) {
						return row.label;
					}
					return "没有找到结果!";
				},
				formatMatch : function(row, i, max) {
					return row.label + row.pinyin;
				},
				formatResult : function(row) {
					return row.label;
				}
			})
			.result(function(event, row, formatted) {
				if (row != 'No Records.') {
					var rowDIv = "<div data-keyword='show' style='float:left;border:1px black solid;margin:2px 2px 2px 2px;'><b>"
							+ row.label
							+ "</b><input type=\"hidden\" name=\"adminUserId\" style='display:none;' value=\""+row.value +"\" ></div>";
					$("#keyword").before(rowDIv);
				}
				$("#keyword").val("");
			});
		});
	</script>
<script type="text/javascript">
$(function(){
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
</script>	
</body>
</html>