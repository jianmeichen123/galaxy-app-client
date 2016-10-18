
//var roleOrUser = $("#target_mark").val(); //为角色 或 用户
//var targetVal = $("#target_val").val();  //角色 或 用户 id
var roleOrUser = null;
var targetVal = 0;

/**
 * 获取可配置项，赋值到radio 
 */
function queryAvailableConfig(){
	sendGetRequest(platformUrl.queryAvailableConfig+"null/0",null,setRadioResource);
}

//设置radio选项
function setRadioResource(data){
	var result = data.result.status;
	if(result == "ERROR"){ //OK, ERROR
		layer.msg(data.result.message);
		return;
	}
	var entityList = data.entityList;
	if(entityList.length != 0 ){
		for(var i=0;i<data.entityList.length;i++){
	    	$("#resourceIdS").append("<label><input type='radio' name='resource' value='"+data.entityList[i].resourceId+"' />"+data.entityList[i].resourceName)+"</label>";
	    } 
	}
}



/**
 * 获取已配置项，展示到页面 
 */
function queryModelConfig(){
	sendGetRequest(platformUrl.queryModelConfig+"null/0",null,setModelConfig);
}

//展示到页面
function setModelConfig(data){
	var result = data.result.status;
	if(result == "ERROR"){ //OK, ERROR
		layer.msg(data.result.message);
		return;
	}
	var entityList = data.entityList;
	if(entityList.length != 0 ){
		for(var i=0;i<data.entityList.length;i++){
	    	
	    } 
	}
}




/**
 * 页面 + 操作
 * 可配置项 --> 页面配置 
 */
function configChange_1(){

	
}
/**
 * 页面 - 操作
 * 页面配置  --> 可配置项 
 */
function configChange_2(){

	
}


/**
 * 获取页面配置模版 的值
 */
function model_result(){
	var valu=$('#resourceIdS input[name="resource"]:checked ').val();
	var data={
			"resourceId":valu,
			"shapeType":1
	}
	return data;
}


/**
 * 保存配置模版的 数组 值
 */
function save_result(){
	var params = model_result();
	sendPostRequestByJsonObj(platformUrl.saveIndexModel,params,saveCallBack);
}

//保存回调
function saveCallBack(data){
	var result = data.result.status;
	if(result == "ERROR"){ //OK, ERROR
		layer.msg(data.result.message);
		return;
	}
	layer.msg("保存成功");
}


function deleteIndexConfig(resourceId){
	var data={
		"resourceId":resourceId
	};
	sendPostRequestByJsonObj(platformUrl.deleteIndexModel,params,saveCallBack);
}



