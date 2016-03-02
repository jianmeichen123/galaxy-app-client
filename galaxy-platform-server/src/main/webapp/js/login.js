
$(document).ready(function(){ 
		var logincookie = $.cookie("autologin");
		if(logincookie != null && logincookie !=""){
			var nickName = logincookie.split(":")[0];
			var time = logincookie.split(":")[1];
			var password = logincookie.split(":")[2];
			ajaxSubmit(nickName,password);
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
		 if(autologin){
			 saveCookie(nickName,password);
		 }
	      ajaxSubmit(nickName,password);
		} 
  }
 
 function ajaxSubmit(nickName,password){
	 $.ajax({
 	     cache: true,
 	     type: "POST",
 	     dataType:"json",
 	     data:{nickName:nickName,password:password},
 	     url:platformUrl.toLogin,
 	     async: false,
 	     error: function(request) {
 	         alert("Connection error");
 	     },
 	     success: function(data) {
 	           if(data.result.status=='ERROR'){
 	            	layer.msg(data.result.message);
 	            }else{
 	            	var sessionId = data.header.sessionId;
 	            	var userId = data.header.userId;
 	            	var loginName = data.header.loginName;
 	            	//alert("登录成功！"+sessionId);
 	            	location.href=platformUrl.toIndex + "?sid=" + sessionId;
 	            }
 	  }}); 
 }
 
 function saveCookie(nickName,password){
	 var Expires = 12*30*24*60*60*1000;
	 var mydate = new Date();
	 var cookievalue= nickName+":"+mydate.getMilliseconds()+":"+password;
	 $.cookie('autologin', cookievalue, { expires: Expires }); //设置带时间的cookie
 }
 
