
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
            $("#resourceIdS").append("<label><input type='radio' name='resource' value='"+data.entityList[i].resourceId+"' data-code='"+data.entityList[i].resourceMark+"' data-url='"+data.entityList[i].contentUrl+"'/>"+data.entityList[i].resourceName)+"</label>";
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
function model_result(id,configOrder){
    var item = $('#resourceIdS input[name="resource"]:checked ');
    var valu=item.val();
    var code = item.data('code')
    var url = item.data('url');
    var data={
        "id":id,
        "resourceId":valu,
        "resourceCode": code,
        "contentUrl":url
    }
    return data;
}


/**
 * 保存配置模版的 数组 值
 */
function save_result(id,configOrder){
    var params = model_result(id,configOrder);
    sendPostRequestByJsonObj(platformUrl.saveIndexModel,params,function(data){
        var result = data.result.status;
        if(result == "ERROR"){ //OK, ERROR
            layer.msg(data.result.message);
            return;
        }
        layer.msg("保存成功");
    });
    return true;
}

function deleteIndexConfig(resourceId){
    var datas={
        "resourceId":resourceId
    };
    sendPostRequestByJsonObj(platformUrl.deleteIndexModel,datas,function(data){
        var result = data.result.status;
        if(result == "ERROR"){ //OK, ERROR
            layer.msg(data.result.message);
            return;
        }
        layer.msg("删除配置成功");

    });
    return true;
}

function addBlock(shapeType,configOrder){
    var datas={
        "shapeType":shapeType,
        "configOrder":configOrder
    };
    var reslut2;
    sendPostRequestByJsonObj(platformUrl.saveModel,datas,function(data){
        var result = data.result.status;
        if(result == "ERROR"){ //OK, ERROR
            layer.msg(data.result.message);
            return;
        }
        //	layer.msg("删除配置成功");
        reslut2=data.userData.arr;
    });

    return reslut2;

}

