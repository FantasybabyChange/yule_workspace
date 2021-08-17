user={
	account:"",
	login : $("#login_message"),
	register :  $("#register_message"),
	register_phone_auth :  $("#register_phone_auth_message"),
	login_captcha :  $("#login_captcha"),
	login_form : $("form[target='login']"),
	register_form : $("form[target='register']"),
	phone_div : $("#phone_div"),
	mail_div : $("#mail_div"),
	login_captcha_html:'验证码'+
		'<input type="text" class="input-text" placeholder="请输入密码" required autofocus>'+
		'<img alt="验证码" title="验证码" style="cursor: pointer;" src="http://login.yuleing.com/captcha.jpg" data-src="http://login.yuleing.com/captcha.jpg" />',
	login_error_num:1,
	login_result:function(result){
		var r = jQuery.parseJSON(result);
		if(!r.status){
			if(this.login_error_num>=5){
				this.login_captcha.html(this.login_captcha_html);
				this.login_captcha.show();
				this.login_captcha.find("input").attr("name","captcha");
				this.login_captcha.find("img").click();
			}
			this.login.html(r.message);
			this.login.show();
			this.login_error_num++;
		}else{
			$("#redirect").submit();
		}
	},
	register_result:function(result){
		var r = jQuery.parseJSON(result);
		if(!r.status){
			this.register.html(r.message);
			this.register.show();
			this.register_form.find("img").click();
		}else{
			console.log(r.c);
			$(".pane-wrapper-tab-item").removeClass("active");
			var account = this.register_form.find("input:text[name='account']").val();
			this.account = account;
			$(".mui-login-wapper").hide();
			if (r.type===0){
				captcha.mail_auth_flag = false;
				captcha.mail_auth_time = r.time;
				this.mail_div.find("#account").text(account);
				$("#mail_div").show();
				captcha.mailAuth();
			} else if(r.type===1){
				captcha.phone_auth_flag = false;
				captcha.phone_auth_time = r.time;
				this.phone_div.find("#account").text(account);
				$("#phone_div").show();
				captcha.phoneAuth();
			}
		}
	},
	register_phone_auth_result:function(result){
		var r = jQuery.parseJSON(result);
		if(!r.status){
			this.register_phone_auth.html(r.message);
			this.register_phone_auth.show();
		}else{
			$("#redirect").submit();
		}
	}
}

$(function(){
	$("input[name='account']").mailAutoComplete();
    	$("img[data-src]").on("click",function(){
		$(this).attr("src",$(this).attr("data-src")+"?time="+new Date().getTime());
	});
	
	if(null!=user.login_form){
		$("form[target='login']").find(".mui-btn").click(function(){
			$("form[target='login']").submit();
	    });
	    
		$("#mail_div").find("a[data-captcha]").click(function(){
	    	if(captcha.mail_auth_flag){
	    		captcha.mail_auth_flag = false;
	    		$("form[target='mail_captcha']").html("<input type=\"hidden\" name=\"account\" value=\""+user.account+"\">");
	    		$("form[target='mail_captcha']").submit();
	    	}
	    });
	    $("#phone_div").find("a[data-captcha]").click(function(){
	    	if(captcha.phone_auth_flag){
	    		captcha.phone_auth_flag = false;
	    		$("form[target='phone_captcha']").html("<input type=\"hidden\" name=\"account\" value=\""+user.account+"\">");
	    		$("form[target='phone_captcha']").submit();
	    	}
	    });
	}
	if(null!=user.register_form){		
		$("form[target='register_phone_auth']").find(".index-order-btn").click(function(){
	    	$("form[target='register_phone_auth']").submit();
	    });
	    
	    user.register_form.find("input[name='password']").keyup(function(){
			//passStrength.verify($(this).val());
		});
	    
	    user.register_form.find(".index-order-btn").click(function(){
			user.register_form.submit();
		});
	}
    $("form[validform]").submit(function(){
    	var flag = true;
    	var account = $("input:text[name='account']");
    	if(account===null||account===""){
    		$("register_message").text("请输入帐号!");
    		flag = false;
    	}
    	return flag;
    });
    
});