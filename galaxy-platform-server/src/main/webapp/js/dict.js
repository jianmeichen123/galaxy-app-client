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
	
	$("#dcit_tab").on("click","a[action='insert_row']",function(){
		var tbody = $("#dict_son tbody");
		var tr = "<tr><td><input name='name'></td></tr>";
		if(tbody.find("input[name='name']").length == 0 ){
			tbody.append(tr);
		}
	});
	
	$("#dict_parent").on("click","li",function(){
		var li = $(this);
		showSonDict(li.attr("code"),li.html());
	});
	
	$(".btnbox").on("click","a[action='save']",function(){
		if($("#dict_son tbody").find("input[name='name']").length == 1 ){
			var input = $("#dict_son tbody").find("input[name='name']");
			save(input);
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
		var update_input = "<input name='name' old_val='"+tr.find("td").html()+"'>";
		tr.find("td").html(update_input);
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

function save(input){
	var parentCode = $("#dcit_tab h2").attr("code");
	var td = input.parent();
	var code = td.attr("code");
	var json = {};
	var action = "insert";
	json['name'] = input.val(); 
	if(code == "undefined" || code == undefined){
		//input.parent().parent().remove();
		json['parentCode'] = parentCode; 
	}else{
		json['code'] = td.attr("code");
		action = "update";
	}
	$.ajax({
		url : "galaxy/dict/"+action,
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
					 console.log(new_dict);
					 var parent = input.parent();
					 parent.html(new_dict.name);
					 parent.attr("code",new_dict.code);
				 }
			}else if(action == "update"){
				 if(data.result.status == "OK"){
					 input.parent().html(input.val());
				 }
			}
		}
	});	
}

function getDictList(parentCode){
	var dicts ;
	$.ajax({
		url : "galaxy/dict/findByParentCode/"+parentCode,
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
			var tr = "<tr><td code='"+dict.code+"' >"+dict.name+"</td></tr>";
			tbody.append(tr);
		  });
	}
}

