/**
 * 总的发送ajax请求的方法
 * 
 * @param reqType
 *            请求方法的类型：post和get
 * @param reqUrl
 *            请求地址
 * @param jsonData
 *            json数据
 * @param sessionId
 *            用户的sessionid
 * @param callbackFun
 *            处理成功后的回调方法
 */
function sendAjaxRequest(reqType, reqUrl, jsonData, sessionId, callbackFun) {
	$.ajax({
		cache : true,
		type : reqType,
		dataType : "json",
		data : jsonData,
		url : reqUrl,
		contentType : "application/json; charset=UTF-8",
		beforeSend : function(xhr) {
			if (sessionId) {
				xhr.setRequestHeader("sessionId", sessionId);
			}
		},
		async : false,
		error : function(request) {
			layer.msg("connetion error");
		},
		success : function(data) {
			if (data.result.status == 'ERROR') {
				layer.msg(data.result.message);
			} else {
				callbackFun(data);
			}
		}
	});
}

/**
 * 发送post请求
 * 
 * @param reqUrl
 *            请求地址
 * @param jsonData
 *            请求json
 * @param sessionId
 *            请求头中需携带的sessionid
 * @param callbackFun
 *            处理成功后的回调方法
 */
function sendPostRequest(reqUrl, jsonData, sessionId, callbackFun) {
	sendAjaxRequest("POST", reqUrl, jsonData, sessionId, callbackFun);
}

/**
 * 发送get请求
 * 
 * @param reqUrl
 *            请求地址
 * @param jsonData
 *            请求json
 * @param sessionId
 *            请求头中需携带的sessionid
 * @param callbackFun
 *            处理成功后的回调方法
 */
function sendGetRequest(reqUrl, jsonData, sessionId, callbackFun) {
	sendAjaxRequest("GET", reqUrl, jsonData, sessionId, callbackFun);
}

/**
 * 使用localstage存储数据 <br/>
 * 注意：IE、Firefox测试的时候需要把文件上传到服务器上（或者localhost），直接点开本地的HTML文件，是不行的。
 * 
 * DataStrore.
 */
DataStrore = {
	storage : window.localStorage,
	checkBrowerSupport : function() {
		if (window.localStorage) {
			alert('This browser supports localStorage');
			return true;
		} else {
			alert('This browser does not support localStorage');
			return false;
		}
	},
	addElement : function(key, value) {
		dataStrore.stroage.setItem(key, value);
	},
	getElement : function(key) {
		dataStrore.stroage.getItem(key);
	},
	removeElement : function(key) {
		dataStrore.stroage.removeItem(key);
	},
	removeAll : function() {
		dataStrore.stroage.clear();
	},
	showKeysAndValues : function() {
		for (var i = 0; i < dataStrore.storage.length; i++) {
			document.write(storage.key(i) + " : "
					+ storage.getItem(storage.key(i)) + "<br>");
		}
	}
}
