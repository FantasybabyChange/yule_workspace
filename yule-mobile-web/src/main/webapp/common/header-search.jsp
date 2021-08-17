<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="mui-search-wapper mui-input-group">
	<h3>搜索</h3>
	<h5>夜总会,娱乐会所,演艺会所,酒吧,量贩KTV及更多…</h5>
	<form search-form="" action="/search/searchCompany.do" method="get">
	<div class="mui-input-row mui-mb10">
		<input id="destination" type="search" class="mui-input" name="name"/>
		<span class="mui-icon mui-icon-clear mui-hidden"></span>
		<span class="mui-icon mui-icon-location"></span>
	</div>
	<div class="mui-input-row mui-mb10" >
		<div class="mui-date-wapper mui-pull-left">
			<h6>企业分类</h6>
			<select class="mui-people-select" name="company_category" id="companyCategory">
				<option value="">请选择</option>
			</select>
		</div>
		<div class="mui-date-wapper mui-pull-right">
			<h6>企业档次</h6>
				<select class="mui-people-select" name="company_grade" id="companyGrade">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	<div class="mui-btn-search-wapper">
<%-- 	<input type="hidden" id="search_id" name="area_city_id"value="${companyQuery.area_city_id }"/>
    <input type="hidden" id="search_name" name="area_city_name"  value="${companyQuery.area_city_name }"/>
 --%>    
 	<input type="hidden" id="latId" name="lat"  value="${companyQuery.lat}"/>
    <input type="hidden" id="lngId" name="lng"  value="${companyQuery.lng}"/>
	<button  type="submit" class="mui-btn mui-btn-search mui-btn-block" >搜 索</button>
	</div>
	</form>
</div>
