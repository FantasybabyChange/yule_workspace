passStrength={
	progress:$(".pass_strength_progress"),
	flag:false,
	verify :function(password){
		var score = 0;
		//密码包含字母
		if (password.match(/([a-zA-Z])/)){ 
			score += 1;
		}
		if (password.length>6){ 
			score += 1;
		}
		if (password.match(/([0-9])/)){
			score += 1;
		} 
		if (password.match(/(.*[!,@,#,$,%,^,&,*,?,_,~])/)){ 
			score += 1;
		} 
		if(score>=2){
			flag = true;
		}else{
			flag = false;
		}
		this.progress.css({"width":score*90});
	}
}