captcha={
	mail_auth_time:10,
	phone_auth_time:10,
	mail_auth_flag:true,
	phone_auth_flag:true,
	mail:$("#mailCount"),
    phone:$("#phoneCount"),
    phoneAuth:function(){
    	if(this.phone_auth_time>0){
    		this.phone.text(this.phone_auth_time+"秒后");
    		this.phone_auth_time--;
    		setTimeout("captcha.phoneAuth()",1000);
    	}else{
    		this.phone.text("");
    		this.phone_auth_time = 10;
    		this.phone_auth_flag = true;
    	}
    },
    mailAuth:function(){
    	if(this.mail_auth_time>0){
    		this.mail.text(this.mail_auth_time+"秒后");
    		this.mail_auth_time--;
    		setTimeout("captcha.mailAuth()",1000);
    	}else{
    		this.mail.text("");
    		this.mail_auth_time = 10;
    		this.mail_auth_flag = true;
    	}
    },
    result:function(result){
		var r = jQuery.parseJSON(result);
		if(!r.status){
			window.location.reload();
		}else{
			console.log(r.c);
			if(r.type===0){
				this.mail_auth_time = r.time;
				this.mailAuth();
			}else if(r.type===1){
				this.phone_auth_time = r.time;
				this.phoneAuth();
			}
		}
	}
}