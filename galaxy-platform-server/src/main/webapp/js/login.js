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
		  var b = new Base64();  
	      var nickName = b.encode($("#nickName").val());  
	      var password = b.encode($("#password").val());  
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
		 	            	location.href=platformUrl.toIndex;
		 	            }
		 	  }}); 
		} 
  }