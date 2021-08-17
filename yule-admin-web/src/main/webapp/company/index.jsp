<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
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
					<h3>企业管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
						<form  action="/company/findCompany.do" method="get" data-query-form="">
							<fieldset>
								<span>帐号:<input type="text" class="text-input" name="account" value="${companyManagerQuery.account }" ></span>
								<span>名称:<input type="text" class="text-input" name="name" value="${companyManagerQuery.name }"></span><br/>
								<span>创建开始时间:<input type="text" class="text-input datetimepicker" name="start_time" value="${companyManagerQuery.start_time }"></span>
								<span>创建结束时间:<input type="text" class="text-input datetimepicker" name="end_time" value="${companyManagerQuery.end_time }"></span><br/>
								是否删除<select name="is_delete"><option  value="">全部</option><option  value="0" >未删除</option><option  value="1">已删除</option></select>
								状态<select name="status"><option  value="">全部</option><option  value="0" >启用</option><option  value="1">未启用</option></select>
								${search_html }
								<input type="submit" class="button" value="检索"/>
							</fieldset>
						</form>
						</div>	
						<table>
							<thead>
								<tr>
									<th><input class="check-all" type="checkbox" /></th>
									<th>企业代码</th>									
									<th>用户名</th>
									<th>企业名称</th>
									<th>企业类型</th>
									<th>企业档次</th>
									<th>上次登录时间</th>
									<th>是否删除</th>
									<th>创建时间</th>
									<th>排序号</th>
									<th style="width:30%">操作</th>
								</tr>
							</thead>
							${htmls}
						</table>
					</div>
					<div class="tab-content" id="tab2" style="display: none;">
						<form action="/company/insertCompany.do" method="post"  ajaxvalidform="" data-url="/company/verifyCompany.do">
							<fieldset>
								<p>
								<label>企业分类:</label>${categoryHtml}
								</p>
								<p>
									<label>企业档次</label> 
									${gradeHtml}
								</p>
								<p>
									<label>企业名称:</label> <input class="text-input"
										type="text" id="small-input" name="name" nullmsg="请输入企业名称!" datatype="" errormsg="" ajaxdata="" /> 
								</p>
								<p>
									<label>帐号:</label> <input class="text-input"
										type="text" id="small-input" name="account" nullmsg="请输入用户名!" datatype="" errormsg="" ajaxdata="" /> 
								</p>
								<p>
									<label>密码:</label> <input class="text-input"
										type="password" id="small-input" name="password" nullmsg="请输入密码!" datatype="" errormsg="" /> 
								</p>
								<p>
							       <label>托管类型</label> 
									<select name="cooperatory_type" id="cooperator_type">
									<option value="0">企业托管</option>
									<option value="1">合作伙伴托管</option>
									</select>
								</p>
								<p style="display:none;" id="salesmanP">
							       <label>合作伙伴</label> 
									<select id="salesman" style="width:120px;" name="salesman_id" >
									<option value="">暂无销售人员</option>
									</select>
								</p>
								<p>
							       <label>企业支付方式</label> 
									<select name="pay_type" id="pay_type">
									<option value="0">线下支付</option>
									<option value="1">在线支付</option>
									</select>
								</p>
								<p>
									<label>提成</label> 
									<input class="text-input" type="text" name="commision" value="${company.commision}" nullmsg="请输入提成!" datatype="n" errormsg="请输入数字!" /> 
								</p>
								<p>
									<label>折扣</label> 
									<input class="text-input" type="text" name="rebast" value="${company.rebast }" nullmsg="请输入折扣!" datatype="n" errormsg="请输入数字!" /> 
								</p>
								<p>
									<input class="button" type="submit" value="新增" />
								</p>
							</fieldset>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js" ></script>
<script type="text/javascript" >
$(function(){
	selectValue("select[name='company_category_id']","${companyManagerQuery.company_category_id}");
	selectValue("select[name='company_grade_id']","${companyManagerQuery.company_grade_id}");
	selectValue("#company_category_id","${company.company_category_id}");
	selectValue("#company_grade_id","${company.company_grade_id}");
	selectValue("select[name='is_delete']","${companyManagerQuery.is_delete}");
	selectValue("select[name='status']","${companyManagerQuery.status}");
});
$(function(){
	$("input[name='order']").change(function(){
		var tar =$(this);
		var jsonData = {};
		jsonData["id"]=tar.attr("data-id");
		jsonData["order"]=tar.val();
		var cfm = window.confirm("确定要修改该企业排序号吗？");
		if(cfm==true){
			$.ajax({
				url:tar.attr("data-url"),
				type:"post",
				dataType:"json",
				data:jsonData,
				async:false,
				success:function(data){
					if(data.status){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
				},
			})
		}

	});
	
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
	$('#cooperator_type').change(function(){
		if($(this).val() === '1'){
			$('#salesmanP').show();
		}else{
			$('#salesmanP').hide();
		}
	});
	$.ajax({
		url:"/salesmanLogin/findSalesmanAjax.do",
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data != null){
				var salesmans = data.salesmans;
				if(salesmans){
				var optionHtml = '';
    			$.each(salesmans,function(i){
    				optionHtml += "<option selected value='"+salesmans[i].id+"'>"+salesmans[i].name+"</option>";
				});
    			$('#salesman').html(optionHtml);
				}
				$('#salesman').select2();
			}
			}
		});
	
	
	
	$("form[ajaxvalidform]").find("input[name='name']").blur(function(){
		var name = $(this).val();
		if(null !=name && name.length > 0 ){
		var flag = confirm("是否自动赋值给账户");
		if(flag === true){
			$.ajax({
				url:"/company/findPinYinByName.do",
				type:"post",
				dataType:"json",
				data:{'name':name},
				async:false,
				success:function(data){
					if(data.status === 0){
						$("form[ajaxvalidform]").find("input[name='account']").val(data.name);
					}
				}
				});
			}
		}
		
		
	});
});
</script>
</body>
</html>