datatable = {
	table:$("table[data-url]"),
	defaultPageNo:function(){
		this.jsonData["pageNo"] = 1;
	},
	jsonData:{},
	query:$("div[data-query]"),
	initPageEvent:function(){
		$(".pagination").find("a[data-page]").click(function(){
			$(".pagination").find("a[data-page]").off('click');
			datatable.jsonData["pageNo"] = $(this).attr("data-page");
			datatable.getQueryData();
			datatable.getHtml();
			datatable.jsonData = {};
		});
	},
	initQueryEvent:function(){
		this.query.find("button").click(function(){
			var pageNo = $(".pagination").find(".active").find("a[data-page]").attr("data-page");
			if(pageNo!=undefined&&pageNo!=""){
				datatable.jsonData["pageNo"] = $(".pagination").find(".active").find("a[data-page]").attr("data-page");
			}else{
				datatable.defaultPageNo();
			}
			datatable.getQueryData();
			datatable.getHtml();
			datatable.jsonData = {};
		});
	},
	getHtml:function(){
		$.ajax({
			url:this.table.attr("data-url"),
			data:this.jsonData,
			type:this.table.attr("data-type"),
			dataType:"json",
			async:false,
			beforeSend :this.beforeSend,
			success:function(data){
				if(null!=data){
					if($("#div_salesman_pay").length!=0){
						$("#div_salesman_pay").html("您在"+data.salesmanBalanceVO.month+"  通过预乐网的提成为"+data.salesmanBalanceVO.bonus+"元.");
					}
					datatable.table.find("tbody").html(data.tbody);
					datatable.table.find("tfoot").html(data.tfoot);
				}
			},
			complete :this.complete
		});
		this.initPageEvent();
	},
	getQueryData:function(){
		this.query.find("input:text").each(function(){
			var val = $(this).val();
			if(val!=""&&val!=undefined){
				datatable.jsonData[$(this).attr("name")] = val;
			}
		});
		var i = 0;
		this.query.find("input:checkbox:checked").each(function(){
			var val = $(this).val();
			if(val!=""&&val!=undefined){
				datatable.jsonData[$(this).attr("name")+"["+i+"]"] = val;
				i++;
			}
		});
		this.query.find("select").each(function(){
			var val = $(this).val();
			if(val!=""&&val!=undefined){
				datatable.jsonData[$(this).attr("name")] = val;
			}
		});
	},
	beforeSend:function(){},
	complete:function(){}
}
$(function(){
	datatable.defaultPageNo();
	datatable.getHtml();
	datatable.initQueryEvent();
});