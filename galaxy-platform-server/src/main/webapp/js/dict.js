/**
 * 数据字典页面js
 */
var dicts = null;
var select_tr = null;
$(function() {
	sendPostRequestByJsonObj( platformUrl.dictFindByParentCode + "xhhl", null, getDictList,sessionId);
	
	if (dicts.length > 0) {
		var parent_dict_div = $("#dict_parent");
		$(dicts).each(function() {
			var dict = $(this)[0];
			if(dict.code !=""){
				var li = '<li data-tab="nav" ' + ' code="' + dict.code + '"' + '>' + dict.name + '</li>';
				parent_dict_div.append(li);
			}
		});
		$($("#dict_parent").find("li[data-tab='nav']")[0]).addClass("on");
		showSonDict(dicts[0].code, dicts[0].name);
	}
	/**
	 * 添加一行
	 */
	$("#dcit_tab").on("click","a[action='insert_row']",function() {
		cancel();
		var tbody = $("#dict_son tbody");
		if (tbody.find("tr[action='insert']").length == 0) {
			var tr = "<tr action='insert'><td><input name='name'></td><td><input name='text'></td></tr>";
			tbody.append(tr);
		}
	});

	// 切换数据字典tab
	$("#dict_parent").on("click", "li", function() {
		var li = $(this);
		li.parent().find("li").removeClass("on");
		li.addClass("on");
		showSonDict(li.attr("code"), li.html());
	});
	// 保存
	$(".btnbox").on("click", "a[action='save']", function() {
		if ($("#dict_son tbody").find("tr[action]").length == 1) {
			var tr = $("#dict_son tbody").find("tr[action]");
			if(tr.attr("action")=="insert"){
				if (tr.find("input[name='name']").val()==""){
					layer.msg("请填写数据字典名");
					return;
				}
			}else if(tr.attr("action")=="update"){
				if (tr.find("input[name='name']").val()==""&&tr.find("input[name='text']").val()==""){
					layer.msg("字典名和备注不能全为空");
					return;
				}
				if (tr.find("input[name='name']").val()==tr.find("input[name='name']").attr("old_val")&&
						tr.find("input[name='text']").val()==tr.find("input[name='text']").attr("old_val")){
					layer.msg("字典名和备注不能全部和以前一样");
					return;
				}
			}
			save(tr);
		}
	});
	// 取消
	$(".btnbox").on("click", "a[action='cancel']", function() {
		cancel();
	});
	// 双击更新
	$("#dict_son tbody").on("dblclick","tr",function() {
		var tr = $(this);
		if(tr.attr("action")=="update"||tr.attr("action")=="insert"){
			return;
		}
		cancel();
		tr.find("td").eq(0).html("<input old_val='" + tr.find("td").eq(0).html()+ "' name='name' value='" + tr.find("td").eq(0).html() + "'>");
		tr.find("td").eq(1).html("<input old_val='" + tr.find("td").eq(1).html()+ "' name='text'  value='" + tr.find("td").eq(1).html() + "'>");
		tr.attr("action", "update");
	});

/*	$("#dcit_tab").on("click", "a[action='batchInsert']", function() {
		batchInsert();
	});*/
});


function cancel() {
	if ($("#dict_son tbody").find("tr[action]").length == 1) {
		var atr_act = $("#dict_son tbody").find("tr[action]");
		var action = atr_act.attr("action");
		// 新增的一行
		if (action == "insert") {
			atr_act.remove();
		} else if (action == "update") {
			// 跟新的一行
			atr_act.find("td").each(function() {
				var input = $(this).find("input[name]");
				$(this).text(input.attr("old_val"));
			});
			atr_act.removeAttr("action");
		}
	}
}
function save(tr) {
	var action = tr.attr("action");
	var code = tr.attr("code");
	var json = {};
	$(tr).find("input").each(function() {
		var input = $(this);
		var val  = input.val();
		var old_val  = input.attr("old_val");
		if(val!=old_val){
			json[input.attr("name")] = val;
		}
	});
	if(json["name"]==""){
		layer.msg("请填写数据字典名");
		return;
	}
    if (!/^[\u4e00-\u9fa5a-zA-Z]{1,50}$/.test(json["name"])) {
		layer.msg("数据字典名只能为中英文 长度50");
		return;
    }
	
	var url = '';
	if (action == "insert") {
		// input.parent().parent().remove();
		var parentCode = $("#dcit_tab h2").attr("code");
		json['parentCode'] = parentCode;
		url = platformUrl.dictInsert;
	} else if (action == "update") {
		json['code'] = tr.attr("code");
		url = platformUrl.dictUpdate;
	}
	select_tr = tr;
	sendPostRequestByJsonObj(url, json, saveCallBack,sessionId);
}


function saveCallBack(data){
	var action = select_tr.attr("action");
	if(data.result.status == "ERROR"){
		layer.msg(data.result.message);
	} else if (action == "insert") {
		var new_dict = data.entity;
		select_tr.find("input[name]").each(function(){
			$(this).parent().html($(this).val());
		});
		select_tr.attr("code", new_dict.code);
		select_tr.removeAttr("action");
		layer.msg("成功");
	} else if (action == "update") {
		var name = select_tr.find("input[name='name']").val();
		if (name == 'undefined' || name == undefined || name == "") {
			name = select_tr.find("input[name='name']").attr("old_val");
		}
		var text = select_tr.find("input[name='text']").val();
		if (text == 'undefined' || text == undefined ) {
			text = select_tr.find("input[name='text']").attr("old_val");
		}
		select_tr.find("td").eq(0).html(name);
		select_tr.find("td").eq(1).html(text);
		select_tr.removeAttr("action");
		layer.msg("成功");
	}
}
function getDictList(data) {
	if (data.result.status == 'OK') {
		dicts = data.pageList.content;
	}else{
		dicts = {};
	}
}

// 显示
function showSonDict(code, name) {
	sendPostRequestByJsonObj( platformUrl.dictFindByParentCode + code, null, getDictList, sessionId);
	var tbody = $("#dict_son tbody");
	$("#dcit_tab h2").html(name);
	$("#dcit_tab h2").attr("code", code)
	tbody.html("");
	if (dicts.length > 0) {
		$(dicts).each(function() {
			var dict = $(this)[0];
			var text = '';
			if (dict.text != 'undefined' && dict.text != undefined) {
				text = dict.text;
			}
			var tr = "<tr code='" + dict.code + "' ><td>" + dict.name + "</td><td>" + text + "</td></tr>";
			tbody.append(tr);
		});
	}
}
