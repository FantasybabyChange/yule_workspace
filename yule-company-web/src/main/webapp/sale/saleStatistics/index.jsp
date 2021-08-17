<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>销售统计</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-datetimepicker.css" />
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
								<form action="/sale/saleStatistics.do" id="saleForm">
									<div data-query="">
										开始时间 :<input type="text" placeholder="开始时间" name="start_time" id="start_time" class="datetimepicker">
										结束时间 :<input type="text" placeholder="结束时间 " name="end_time" id="end_time" class="datetimepicker">
										<select id="time_type">
											<option value="2">天</option>
											<option value="1">月</option>
											<option value="0">年</option>
										</select>
											<button class="btn btn-success" type="button">查询</button>
									</div>
								</form>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div  id="main" style="height: 500px; border: 1px solid rgb(204, 204, 204); padding: 10px; background-color: transparent; cursor: default;">
									</div>
								</div>
							</div><!-- /.row -->
						</div>
					</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<%@ include file="/common/scripts.jsp" %>
		
		<!-- page specific plugin scripts -->
		<script src="http://static.yuleing.com/assets/js/date-time/moment.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
		
		<script src="http://static.yuleing.com/js/esl.js"></script>
		<script async="" src="http://static.yuleing.com/js/echarts.js" data-require-id="echarts"></script>
		<script async="" src="http://static.yuleing.com/js/echarts.js" data-require-id="echarts/chart/bar"></script>
		<script async="" src="http://static.yuleing.com/js/echarts.js" data-require-id="echarts/chart/line"></script>
		
   <script type="text/javascript">
	$(function(){
	    var myChart ;
	    var options ;
	    var commision;
	    var flag = true;
	        require.config({
	        paths:{ 
	             echarts:'http://static.yuleing.com/js/echarts',
	            'echarts/chart/bar' : 'http://static.yuleing.com/js/echarts',
	            'echarts/chart/line': 'http://static.yuleing.com/js/echarts',
	        }
	    });
	    require(
	        [
	            'echarts',
	            'echarts/chart/bar',
	            'echarts/chart/line',
	            ],
	        drawEChart
	    );
	    
	    function drawEChart(ec){
	    	myChart = ec.init(document.getElementById('main'));
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    		});
	    	options = {
	    			 toolbox: {
	    			        show : true,
	    			        orient: 'horizontal',      
	    			        x: 'right',                
	    			        y: 'top',                  
	    			        color : ['#1e90ff','#22bb22','#4b0082','#d2691e'],
	    			        backgroundColor: 'rgba(0,0,0,0)', 
	    			        borderColor: '#ccc',      
	    			        borderWidth: 0,            
	    			        padding: 5,              
	    			        showTitle: true,
	    			        feature : {
				            restore : {
	    			                show : true,
	    			                title : '还原',
	    			                color : 'black'
	    			            },
	    			            magicType: {
	    			                show : true,
	    			                title : {
	    			                    line : '动态类型切换-折线图',
	    			                    bar : '动态类型切换-柱形图',
	    			                },
	    			                type : ['line', 'bar'],
	    			            }
	    			        }
	    			    },
	    			    calculable : true,
	    			    dataZoom : {
	    			        show : true,
	    			        realtime : true,
	    			        start : 20,
	    			        end : 80
	    			    },
	    			title: {
		    			text: "贵公司各笔产品订单金额统计图",
		    			subtext: "订单总计",
	    			},
	    			tooltip: {
	    				trigger: 'axis',
	    		        textStyle : {
	    		            decoration: 'none',
	    		            fontFamily: '微软雅黑, sans-serif',
	    		            fontSize: 15,
	    		            fontStyle: '微软雅黑',
	    		            fontWeight: 'bold'
	    		        },
	    		        formatter: function (params,ticket,callback) {
	    		            var res = params[0][1] +'订单情况情况统计 : <br/>'  ;
	    		            res +='<table><tr align="center"><td>产品</td><td>订单数量</td><td>订单收入金额</td><td>利润金额</td></tr>' 
	    		            for (var i = 0, l = params.length; i < l; i++) {
	    		                res += '<tr align="center">' + '<td>'+params[i][0] + '</td>' + '<td>'+getproductCount(params[i][0],params[0][1])+'笔 </td>' + '<td>'+params[i][2]+'元</td><td>'+(params[i][2]*commision).toFixed(2)+'元</td></tr>';
	    		            }
	    		            res +='</table>';
	    		            setTimeout(function (){
	    		                callback(ticket, res);
	    		            }, 200)
	    		            return '<image src="http://images.yuleing.com/loading/loading1.gif">';
	    		        }
	    			},
	    			legend: {
	    				data: []
	    			},
	    			calculable: true,
	    			xAxis: [
		    			{
		    			type: 'category',
		    			data: []
		    			}
	    			],
	    			yAxis: [
		    			{
		    			type: 'value',
		    			 axisLabel : {
		    	                formatter: '{value} 元'
		    	            },
		    			splitArea: { show: true }
		    			}
	    			],
	    			series: [
	    			         ],

	    			};
	    	ajaxLoadData();
	    }
	    function ajaxLoadData(){
			var jsonData={};
			jsonData = {
					'end_time' : $("#end_time").val(),
					'start_time': $("#start_time").val(),
					'time_type':$("#time_type").val()
			}
	    	$.ajax({
	    		type: "post",
	    		async: false, //同步执行
	    		data:jsonData,
	    		url: "/sale/saleStatistics.do",
	    		dataType: "json", 
	    		success: function (result) {
    			if(result!=''&&result.options!=null&&result.options!=''&&result.options.series.length>0){
		    		options.xAxis[0].data = result.options.xAxis.data;
		    		options.series=result.options.series;
		    		options.legend.data = result.options.legend.data;
					var data =result.options.legend.data;
					commision = result.commision/100;
					options.title.subtext='当前时间订单总数'+result.ordersCount+'笔  订单总 计:'+result.ordersExpenseCount+'元'+' 利润:'+(result.ordersExpenseCount*commision).toFixed(2)+'元';
	/* 				data[i]=decodeURIComponent(data[i]);
					$.each(data,function(i,item){
						data[i]=decodeURIComponent(item);
					});	 */
		    		myChart.setOption(options);
		    		myChart.hideLoading();
    				}
    			else{
	    	    	myChart.showLoading({
	    	    		text: "当前时间暂无数据"
	    	    	});	
	    			}
	    		},
	    		error: function (errorMsg) {
	    	    	myChart.showLoading({
	    	    		text: "图表数据加载错误"
	    	    	});		    		
	    		}
	    		});
	    }
	    $(".btn-success").click(function(){
	    	myChart.clear();
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    		});
	    	ajaxLoadData();
	    });
	    function reDraw(){
    	   	myChart.clear();
	    	myChart.showLoading({
	    		text: "图表数据正在努力加载..."
	    		});
    		myChart.setOption(options);
    		myChart.hideLoading();
	    }
	    
	    function getproductCount(name,day){
	    	var count=0;
	    	var data = options.xAxis[0].data;
	    	$(options.series).each(function(){
	    		var obj = $(this);
				if($(this).attr("name")==name){
			     	for(var i=0;i<=data.length;i++){
			      		if(day==data[i]){
			      			count=obj.attr("productCount")[i];
			      			break;
			    		}
			    	}
				}
	    	});
	    	return count;
	    }
	});
	
    </script>
	</body>
</html>
