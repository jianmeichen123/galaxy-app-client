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
	$(".btnbox_f").on("click","a[data-btn='userinfro']",function(event){
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,
			data:"",//传递参数
			okback:function(){doSumbit();}//模版反回成功执行	
		});
		return false;
	});
	$("[data-btn='change_password']").on("click",function(){
		$('.pop').remove();
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				// $(this).change_password();
			}//模版反回成功执行	
		});
		return false;
	});	
	//权限添加角色
	$("[data-btn='role_add']").on("click",function(){ 
		var $self = $(this);
		var _url = $self.attr("href");
		var _name= $self.attr("data-name");
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				doSumbit();
			//	$("#popup_name").html(_name);
			}//模版反回成功执行	
		});
		return false;
	});

});

function getQueryString(url,name){

    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");

    var r = url.substr(1).match(reg);

    if(r!=null)return  unescape(r[2]); return null;

}
