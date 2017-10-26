
/*$(document).ready(function(){ 
		var logincookie = $.cookie("autologin");
		if(logincookie != null && logincookie !=""){
			var email = logincookie.split(":")[0];
			var password = logincookie.split(":")[2];
			var jsonData={"email":email,"password":password};
			sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,callbackFun);
		}
})
*/
function checkform(){
    var email =$("#email").val();
    var password =$("#password").val();
    
    if(email==""){
		$("#email").focus();
        layer.tips('请输入用户名', '#email');
        return false;
    }
    if(password==""){
		$("#password").focus();
        layer.tips('请输入密码', '#password');
        return false;
    }
    return true;
 }

 function login(){
	
	 if(checkform()){
		 //判断是否勾选了自动登录
		 var autologin = $("input[type='checkbox']").is(':checked');
		 var b = new Base64();  
	     var email = b.encode($("#email").val());  
	     var password = b.encode($("#password").val());  
	     var aclient=myBrowser();
	     var jsonData={"email":email,"password":password,"aclient":myBrowser(aclient)};
	     
		 /*if(autologin){
			 saveCookie(email,password);
		 }*/
		 sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,logincallback);
		} 
  }
 
 function myBrowser(){
	    var userAgent =  window.navigator.userAgent; //取得浏览器的userAgent字符串
	    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
	    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
	    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
	    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
	    var isChrome = userAgent.indexOf("Chrome") > -1; //判断是否Chrome浏览器
	    var is360 = userAgent.indexOf("360") > -1; //判断是否360浏览器
	    if (isIE) {
	        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
	        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
	        reIE.test(userAgent);
	        var fIEVersion = parseFloat(RegExp["$1"]);
	        IE55 = fIEVersion == 5.5;
	        IE6 = fIEVersion == 6.0;
	        IE7 = fIEVersion == 7.0;
	        IE8 = fIEVersion == 8.0;
	        if (IE55) {
	            return "IE55";
	        }
	        if (IE6) {
	            return "IE6";
	        }
	        if (IE7) {
	            return "IE7";
	        }
	        if (IE8) {
	            return "IE8";
	        }
	    }else if (isFF) {
	        return "Firefox";
	    }else if (isOpera) {
	        return "Opera";
	    }else if (isChrome) {
	        return "Chrome";
	    }else if (is360) {
	        return "360";
	    }else{
	    	return "other";
	    }
	    
	}

 function logincallback(data){
	 if(data.result.status=="OK"){
		 var sessionId = data.header.sessionId;
		 var userId = data.header.userId;
		forwardIndexWithHeader(platformUrl.toIndex,sessionId,userId);
		return false;
	 }else{
		 layer.msg(data.result.message);
	 }
 }
 function keylogin(){
	 if (event.keyCode == 13)
	  {
	    login();
	  }
 }
 function logout(){
		$.ajax({
			url : platformUrl.logout,
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			async : false,
			beforeSend : function(xhr) {
				if (sessionId) {
					xhr.setRequestHeader("sessionId", sessionId);
				}
				if(userId){
					xhr.setRequestHeader("guserId", userId);
				}
			},
			error : function(request) {
			},
			success : function(data) {
				if(data.result.status=="OK"){
					location.href=platformUrl.toLoginPage;
				}
			}
		}); 
	} 
 
 $(function(){
		var source=$(".ritmin").attr("source");
		var li =$(".lft li a[target='"+source+"']").parent().addClass("on");
	});
