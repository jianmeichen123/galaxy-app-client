
$(document).ready(function(){ 
		var logincookie = $.cookie("autologin");
		if(logincookie != null && logincookie !=""){
			var nickName = logincookie.split(":")[0];
			var password = logincookie.split(":")[2];
			alert(nickName+password);
			var jsonData={"nickName":nickName,"password":password};
			sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,callbackFun,null);
		}
})

function checkform(){
    var username =$("#nickName").val();
    var password =$("#password").val();
    
    if(username==""){
		$("#nickName").focus();
        layer.tips('请输入用户名', '#nickName');
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
	     var nickName = b.encode($("#nickName").val());  
	     var password = b.encode($("#password").val());  
	     var jsonData={"nickName":nickName,"password":password};
		 if(autologin){
			 saveCookie(nickName,password);
		 }
		 sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,callbackFun,null);
		} 
  }
 
 function saveCookie(nickName,password){
	 var mydate = new Date();
	 var cookievalue= nickName+":"+mydate.getMilliseconds()+":"+password;
	 $.cookie('autologin', cookievalue,{expires: 1000*60*60*24});
 }
 
 function callbackFun(data){
	var sessionId = data.header.sessionId;
  	var userId = data.header.userId;
  	var loginName = data.header.loginName;
  	location.href=platformUrl.toIndex + "?sid=" + sessionId;
 }
