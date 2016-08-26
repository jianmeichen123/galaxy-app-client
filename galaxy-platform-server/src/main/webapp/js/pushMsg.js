//首页相关js

	//选择投资人
	
	var deptList = {};
	
	function callbackFun(data) {
		deptList = data.entityList;
	}
	
	$(function(){
	
	$("[data-btn='popup_pushMsg']").on("click",function(){
		
		var $self = $(this);
		var _url = $self.attr("href");
		var _name= $self.attr("data-name");
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$("#popup_name").html(_name);
				
				//获取departmentList
				sendGetRequest(platformUrl.getDepartList, null, callbackFun);
				var tbodyList = $('#investor');
				$(deptList).each(
					function() {
					var templ = $(this)[0];
					var temp="<div class='people_all' data-name='"+templ.name+"'><span class='people_title'></span><input type='checkbox' id="+templ.id+" value='"+templ.name+"'/><label for="+templ.id+" class='lable'>"+templ.name+"</label></div>";
					temp = temp+("<ul class='list-unstyled people_all_ul'>");
					sendGetRequest(platformUrl.queryUserByDept+"/"+templ.id, null, function(data){
						var memberList =data.entityList;
						$(memberList).each(function(){
							var member = $(this)[0];
							temp = temp+"<li ><input type='checkbox' name='"+templ.name+"'  parent= '"+templ.name+"'  id='li_"+member.id+"' data_id='li_"+member.id+"' value='"+member.realName+"' item_id='"+member.id+"' /><label for='li_"+member.id+"' class='lable' >"+member.realName+"</label></li>"
						})
					});
					
					
					temp = temp+("</ul>");
					tbodyList.append(temp);
									
					})
			}//模版反回成功执行	
		});
		return false;
	});
	});
	
	function submitInvestor(){
		//遍历所选投资人
		var list = $("#investorCheck").find("li");
		var memberList=[];
		var userIdList=[];
		$(list).each(function(){
			var name = $(this).attr("value");
			var id = $(this).attr("item_id");
			//连接投资机构 和投资人
			memberList.push(name);
			userIdList.push(id);
		})
		console.log(userIdList);
		var userStr = memberList.toString();
		var content = $("#content").val();
		if(userStr==null ||userStr==""){
			layer.alert("请选择通知对象！");
			return false;
		}
		if(userIdList.length==0){
			layer.alert("请选择通知对象！");
			return false;
		}
		if(content==null||content==""){
			layer.alert("请输入通知内容！");
			return false;
		}else if(content.length>170){
			layer.alert("通知内容最大为170个字！");
			return false;
		}
		
		var jsonData ={userStr:userStr,content:content,userIdList:userIdList};
		sendPostRequestByJsonObj(platformUrl.insertPushMsg,jsonData, function(data){
			if(data.result.status=="OK"){
				layer.alert("推送成功!",function(){
					history.go(0);
					//$("#data-table").bootstrapTable('refresh', {url: 'galaxy/pushMsg/queryMsgList'});  
				})
			}else{
				layer.alert("推送失败！")
			}
			
		    
		});
		//给投资人表单域赋值
	}
	
	function cancel(){
		history.go(0);
	}
