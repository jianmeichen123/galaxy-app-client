/**
 * 数据字典页面js
 */
$(function(){
	var parentDicts = getDictList("XHHL");
	if(parentDicts.length > 0 ){
		var parent_dict_div = $("#dict_parent"); 
		$(parentDicts).each(function(){
			 var dict = $(this)[0];
			 var li = '<li data-tab="nav" '+' code="'+dict.code+'"'+'>'+dict.name+'</li>';
			 parent_dict_div.append(li);
		  });
		showSonDict(parentDicts[0].code,parentDicts[0].name);
	}
	//切换数据字典父类型
	$("#dict_parent").on("click","li",function(){
		var li = $(this);
		showSonDict(li.attr("code"),li.html());
	});

	/**
	 * 添加一行
	 */
	$("#dcit_tab").on("click","a[action='insert_row']",function(){
		var tbody = $("#dict_son tbody");
		if(tbody.find("tr[action='insert']").length == 0 ){
			var tr = "<tr action='insert'><td><input name='name'></td><td><input name='text'></td></tr>";
			tbody.append(tr);
		}
	});
	
	$("#dict_parent").on("click","li",function(){
		var li = $(this);
		showSonDict(li.attr("code"),li.html());
	});
	
	$(".btnbox").on("click","a[action='save']",function(){
		if($("#dict_son tbody").find("tr[action]").length == 1 ){
			var tr = $("#dict_son tbody").find("tr[action]");
			save(tr);
		}
	});
	$(".btnbox").on("click","a[action='cancel']",function(){
		if($("#dict_son tbody").find("input[name='name']").length == 1 ){
			var input = $("#dict_son tbody").find("input[name='name']");
			var code = input.parent().attr("code");
			if(code == "undefined" || code == undefined){
				input.parent().parent().remove();
			}else{
				var old_val = input.attr("old_val");
				input.parent().html(old_val);
			}
		}
	});
	$("#dict_son tbody").on("dblclick","tr",function(){
		var  tr = $(this);
		if($("#dict_son tbody").find("tr[action]").length == 1 ){
			var tr = $("#dict_son tbody").find("tr[action]");
			var code = tr.attr("code");
			//新增的一行
			if(code == "undefined" || code == undefined|| code == "insert"){
				tr.remove();
			}else{
				//跟新的一行
					tr.find("input").each(function(){
						$(this).parent().html($(this).attr("old_val"));
					});
			}
		}
		tr.find("td")[0].html("<input old_val='"+tr.find("td")[0].html()+"' name=''>");
		tr.find("td")[0].html("<input old_val='"+tr.find("td")[0].html()+"' name=''>");
	});
	
	
	$("#dcit_tab").on("click","a[action='batchInsert']",function(){
		batchInsert();
	});
});

/*function batchInsert(){
	var parentCode = $("#dcit_tab h2").attr("code");
	var json = {};
	json['parentCode']= parentCode;
	
	$.ajax({
		url : "galaxy/dict/batchInsert",
		data:JSON.stringify(json),
		async : false,
		type : 'POST',
	    contentType:"application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error:function(){     
	    }, 
		success : function(data) {
			if(data.result.status == "OK"){
				var name = $("#dcit_tab h2").html();
				showSonDict(parentCode,name);
			}
		}
	});	
	
}*/

function save(tr){
	var action = tr.attr("action");
	var code = tr.attr("code");
	var json = {};
	$(tr).find("input").each(function(){
		var input = $(this);
		json[input.attr("name")] = input.val(); 
	});
	var url = '';
	if(action == "insert"){
		//input.parent().parent().remove();
		var parentCode = $("#dcit_tab h2").attr("code");
		json['parentCode'] = parentCode; 
		url = platformUrl.dictInsert;
	}else{
		json['code'] = td.attr("code");
		url = platformUrl.dictUpdate;
	}
	$.ajax({
		url : url,
		data:JSON.stringify(json),
		async : false,
		type : 'POST',
	    contentType:"application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error:function(){     
	    }, 
		success : function(data) {
			if(action == "insert"){
				 if(data.result.status == "OK"){
					 console.log("insert");
					 var new_dict  = data.entity;
					 tr.find("input[name='name']").parent().html(new_dict.name);
					 tr.find("input[name='text']").parent().html(new_dict.text);
					 tr.attr("code",new_dict.code);
					 tr.removeAttr("action");
				 }
			}else if(action == "update"){
				 if(data.result.status == "OK"){
					 input.parent().html(input.val());
					 tr.removeAttr("action");
				 }
			}
		}
	});	
}

function getDictList(parentCode){
	var dicts ;
	$.ajax({
		url : platformUrl.dictFindByParentCode+parentCode,
		async : false,
		type : 'POST',
	    contentType:"application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error:function(){     
	    }, 
		success : function(data) {
			if(data.result.status == 'OK'){
				dicts = data.pageList.content; 
			}
		}
	});	
	return dicts;
}

//显示
function showSonDict(code,name){
	var sonDicts = getDictList(code);
	var tbody = $("#dict_son tbody");
	$("#dcit_tab h2").html(name);
	$("#dcit_tab h2").attr("code",code)
	tbody.html("");
	if(sonDicts.length > 0){
		$(sonDicts).each(function(){
			var dict = $(this)[0];
			var tr = "<tr code='"+dict.code+"' ><td>"+dict.name+"</td><td>"+dict.text+"</td></tr>";
			tbody.append(tr);
		  });
	}
}

