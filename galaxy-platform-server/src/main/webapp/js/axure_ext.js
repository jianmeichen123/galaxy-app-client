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
	//修改密码验证弹窗
	$("span[data-btn='login_infor']").on("click",function(){
		console.log("sss")
		$('.pop').remove();
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				var test = getQueryString(_url,"realName");
				
				$('.register_all_two1').html(getQueryString(_url,"realName"));
				$('.register_all_two2').html(getQueryString(_url,"deptName"));
				$('.register_all_two3').html(getQueryString(_url,"roleName"));
				//$("#hid").href=getUrl(_url);
				
				$("[data-btn='change_password']").on("click",function(){
					$('.pop').remove();
					var $self = $(this);
					var _url = $self.attr("href");
					$.getHtml({
						url:_url,//模版请求地址
						data:"",//传递参数
						okback:function(){
							//$(this).change_password();
						}//模版反回成功执行	
					});
					return false;
				});	
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
