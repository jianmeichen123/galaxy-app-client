/**
 * 数据字典页面js
 */
$(function() {
	var parentDicts = getDictList("XHHL");
	if (parentDicts.length > 0) {
		var parent_dict_div = $("#dict_parent");
		$(parentDicts).each(
				function() {
					var dict = $(this)[0];
					var li = '<li data-tab="nav" ' + ' code="' + dict.code + '"' + '>' + dict.name + '</li>';
					parent_dict_div.append(li);
				});
		showSonDict(parentDicts[0].code, parentDicts[0].name);
	}
	// 切换数据字典父类型
	$("#dict_parent").on("click", "li", function() {
		var li = $(this);
		showSonDict(li.attr("code"), li.html());
	});

	/**
	 * 添加一行
	 */
	$("#dcit_tab")
			.on("click",
				"a[action='insert_row']",
				function() {
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
		}
		save(tr);
	});
	// 取消
	$(".btnbox").on("click", "a[action='cancel']", function() {
		cancel();
	});
	// 双击更新
	$("#dict_son tbody").on(
			"dblclick",
			"tr",
			function() {
				var tr = $(this);
				cancel();
				tr.find("td").eq(0).html(
						"<input old_val='" + tr.find("td").eq(0).html()
								+ "' name='name' value='"
								+ tr.find("td").eq(0).html() + "'>");
				tr.find("td").eq(1).html(
						"<input old_val='" + tr.find("td").eq(1).html()
								+ "' name='text'  value='"
								+ tr.find("td").eq(1).html() + "'>");
				tr.attr("action", "update");
			});

	$("#dcit_tab").on("click", "a[action='batchInsert']", function() {
		batchInsert();
	});
});

/*
 * function batchInsert(){ var parentCode = $("#dcit_tab h2").attr("code"); var
 * json = {}; json['parentCode']= parentCode;
 * 
 * $.ajax({ url : "galaxy/dict/batchInsert", data:JSON.stringify(json), async :
 * false, type : 'POST', contentType:"application/json; charset=UTF-8", dataType :
 * "json", cache : false, error:function(){ }, success : function(data) {
 * if(data.result.status == "OK"){ var name = $("#dcit_tab h2").html();
 * showSonDict(parentCode,name); } } });
 *  }
 */

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
		if(val!=""&&val!=old_val){
			json[input.attr("name")] = val;
		}
	});
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
	$.ajax({
				url : url,
				data : JSON.stringify(json),
				async : false,
				type : 'POST',
				contentType : "application/json; charset=UTF-8",
				dataType : "json",
				cache : false,
				error : function() {
				},
				success : function(data) {
					if(data.result.status == "ERROR"){
						layer.msg(data.result.message);
					} else if (action == "insert") {
						var new_dict = data.entity;
						tr.find("input[name='name']").parent().html(
								new_dict.name);
						tr.find("input[name='text']").parent().html(
								new_dict.text);
						tr.attr("code", new_dict.code);
						tr.removeAttr("action");
						layer.msg("成功");
					} else if (action == "update") {
						var name = json["name"];
						if (name == 'undefined' || name == undefined
								|| name == "") {
							name = tr.find("input[name='name']").attr(
									"old_val");
						}
						var text = json["text"];
						if (text == 'undefined' || text == undefined
								|| text == "") {
							text = tr.find("input[name='text']").attr(
									"old_val");
						}
						tr.find("td").eq(0).html(name);
						tr.find("td").eq(1).html(text);
						tr.removeAttr("action");
						layer.msg("成功");
					}
				}
			});
}

function getDictList(parentCode) {
	var dicts;
	$.ajax({
		url : platformUrl.dictFindByParentCode + parentCode,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
		},
		success : function(data) {
			if (data.result.status == 'OK') {
				dicts = data.pageList.content;
			}
		}
	});
	return dicts;
}

// 显示
function showSonDict(code, name) {
	var sonDicts = getDictList(code);
	var tbody = $("#dict_son tbody");
	$("#dcit_tab h2").html(name);
	$("#dcit_tab h2").attr("code", code)
	tbody.html("");
	if (sonDicts.length > 0) {
		$(sonDicts).each(
				function() {
					var dict = $(this)[0];
					var text = '';
					if (dict.text != 'undefined' && dict.text != undefined) {
						text = dict.text;
					}
					var tr = "<tr code='" + dict.code + "' ><td>" + dict.name
							+ "</td><td>" + text + "</td></tr>";
					tbody.append(tr);
				});
	}
}
