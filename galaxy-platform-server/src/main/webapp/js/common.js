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
