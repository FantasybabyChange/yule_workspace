<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div role="navigation" id="menu-bar" class="cont-shadow">
	<div class="mw1004 clearfix">
		<a href="#" style="float: left;">
             <img src="http://images.yuleing.com/logo/yuleing.png" alt="">
         </a>
		<div class="list-map-search pull-left">
		<div class="list-map-city">
            <input class="input-style-2" id="list-map-text" autocomplete="off" type="text"  value="${area_city_name}"/>
			<div class="destination-suggestion" style="display:none" id="list-map-suggestion">
		    	正在加载
		    </div>
		</div>
		<div style="float:right	;"><input class="input-style"  type="text" placeholder="" id="destination" name="ss" >
		    <button id='searchLocalId' class="b-button pull-right" type="submit">
		        <span  class="b-button__text">搜索</span>
		    </button>
		    </div>
		</div>
		<ul class="right-nav" id="login-nav">
		</ul>
	</div>
</div>
<form action="http://login.yuleing.com/findHeader.do" method="post" target="header" id="headerForm"></form>
<iframe src="about:blank" width="1" height="1" id="header" name="header" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
<script type="text/javascript">
	document.getElementById("headerForm").submit();
	document.domain = "yuleing.com";
	header={
		user_login_result:function(result){
			document.getElementById("login-nav").innerHTML = result;
		}
	}
</script>