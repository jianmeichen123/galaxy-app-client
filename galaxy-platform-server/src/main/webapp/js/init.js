/**
 * 
 */
$(function () {
	$('#data-table').bootstrapTable({
		queryParamsType: 'size|page', // undefined
		pageSize:10,
		showRefresh : false ,
		sidePagination: 'server',
		method : 'post',
		pagination: true,
        search: false,
	});
/*	//用户信息弹窗
	$(".btnbox_f").on("click","button[data-btn='userinfro']",function(event){
		console.log(event);
		var $self = $(this);
		var _url = $self.attr("href");
		$.getHtml({
			url:_url,
			data:"",//传递参数
			okback:function(){doSumbit();}//模版反回成功执行	
		});
		return false;
	});*/
});

