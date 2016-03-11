// JavaScript Document
$(function(){


			//发邮件弹窗
	$("[data-btn='email']").on("click",function(){
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){}//模版反回成功执行	
		});
		return false;
	});
	
	//用户信息弹窗
	$(".btnbox_f").on("click","button[data-btn='userinfro']",function(event){
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,
			data:"",//传递参数
			okback:function(){doSumbit();}//模版反回成功执行	
		});
		return false;
	});
});