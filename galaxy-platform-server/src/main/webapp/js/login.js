
/*$(document).ready(function(){ 
		var logincookie = $.cookie("autologin");
		if(logincookie != null && logincookie !=""){
			var nickName = logincookie.split(":")[0];
			var password = logincookie.split(":")[2];
			var jsonData={"nickName":nickName,"password":password};
			sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,callbackFun);
		}
})
*/
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
		 sendPostRequestByJsonObj(platformUrl.toLogin,jsonData,logincallback);
		} 
  }
 
/* function saveCookie(nickName,password){
	 var cookietime = new Date(); 
	 var cookievalue= nickName+":"+cookietime.getMilliseconds()+":"+password;
	 $.cookie('autologin', cookievalue,{expires: 30*24});
 }*/
 
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
			},
			error : function(request) {
				alert("connetion error");
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
	})
