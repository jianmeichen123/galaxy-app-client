package com.galaxyinternet.model.operationMessage;

import com.galaxyinternet.platform.constant.PlatformConst;

/**
 * 
 * @Description: 消息提醒规范类型
 *
 */
public enum OperationType {
	
	UPDATE					("/galaxy/project/editProject",		PlatformConst.PROJECT_TYPE, "编辑"), 
	
	PRO_START_REVIEW		("/galaxy/project/startReview", 	PlatformConst.PROJECT_TYPE, "启动内部评审"),
	
	APPLY_CEO_SCHEDULE  	("/galaxy/project/incm", 			PlatformConst.PROJECT_TYPE, "申请CEO评审会会议排期"),
	APPLY_PROJECT_SCH		("/galaxy/project/inlx", 			PlatformConst.PROJECT_TYPE, "申请立项会会议排期"),
	APPLY_PROJECT_TJH		("/galaxy/project/intj", 			PlatformConst.PROJECT_TYPE, "申请投决会会议排期"),
	
	
	_3_add_p1_	("/galaxy/project/progress/addFileInterview", 	PlatformConst.PROJECT_TYPE, "添加"),
	_3_add_p2_	("/galaxy/project/stageChange/"+UrlNumber.one, 	PlatformConst.PROJECT_TYPE, "添加"),
	EDIT_VIEW_FILE_RECORD	("/galaxy/project/progress/updateInterview", 	PlatformConst.PROJECT_TYPE, "编辑"),
	_4_add_p1_	("/galaxy/project/progress/addfilemeet",		PlatformConst.PROJECT_TYPE, "添加"),
	_4_add_p2_	("/galaxy/project/stageChange/"+UrlNumber.two,		PlatformConst.PROJECT_TYPE, "添加"),
	_4_add_p3_	("/galaxy/project/stageChange/"+UrlNumber.three,		PlatformConst.PROJECT_TYPE, "添加"),
	_4_add_p4_	("/galaxy/project/stageChange/"+UrlNumber.four,		PlatformConst.PROJECT_TYPE, "添加"),
	_4_add_p5_	("/galaxy/project/stageChange/"+UrlNumber.eight,		PlatformConst.PROJECT_TYPE, "添加"),
	EDIT_MEET_FILE_RECORD	("/galaxy/project/progress/updatemeet",			PlatformConst.PROJECT_TYPE, "编辑"),
	
	
	
	CLAIM_TASK				("/galaxy/soptask/goClaimtcPage", 				PlatformConst.TASK_TYPE, "认领任务"),
	COMPLETE_TASK			("/galaxy/soptask/updateTaskStatus", 			PlatformConst.TASK_TYPE, "完成任务"),
	SUBMIT_TASK              ("/galaxy/soptask/submitTask",                 PlatformConst.TASK_TYPE, "完成任务"),
	
	ADD_SCHEDULING			("/galaxy/project/updateReserveTime/"+ UrlNumber.one, 	PlatformConst.SCHEDULING_TYPE, "创建会议排期"),
	UPDATE_SCHEDULING		("/galaxy/project/updateReserveTime/"+ UrlNumber.two, 	PlatformConst.SCHEDULING_TYPE, "更新会议排期"),
	DELETE_SCHEDULING		("/galaxy/project/updateReserveTime/"+ UrlNumber.three, PlatformConst.SCHEDULING_TYPE, "删除会议排期"),
	FILE_BUSINESSPLAN("/galaxy/sopFile/commonUploadFile/" + UrlNumber.one,PlatformConst.PROJECT_TYPE,"上传"),
	FILE_INTERESTLETTER("/galaxy/sopFile/commonUploadFile/" + UrlNumber.two,PlatformConst.PROJECT_TYPE,"更新"),

	TASK_URGED				("/galaxy/taskprocess/taskUrged/"+UrlNumber.one, 		PlatformConst.TASK_TYPE, "催办"),
	TASK_ADD_FILE			("/galaxy/taskprocess/uploadFile/"+UrlNumber.one,		PlatformConst.TASK_TYPE, "上传"),
	TASK_UPDATE_FILE		("/galaxy/taskprocess/uploadFile/"+UrlNumber.two, 		PlatformConst.TASK_TYPE, "更新"),
	
	_5_2_			("/galaxy/project/stageChange/"+UrlNumber.five,		PlatformConst.PROJECT_TYPE, "上传"),
	_5_4_			("/galaxy/project/stageChange/"+UrlNumber.seven,		PlatformConst.PROJECT_TYPE, "上传"),
	_5_8_			("/galaxy/project/stageChange/"+UrlNumber.nine,		PlatformConst.PROJECT_TYPE, "上传"),
	_5_9_			("/galaxy/project/stageChange/"+UrlNumber.twelve,		PlatformConst.PROJECT_TYPE, "上传"),
	_5_12_			("/galaxy/project/stageChange/"+UrlNumber.ten,		PlatformConst.PROJECT_TYPE, "上传"),
	_5_13_			("/galaxy/project/stageChange/"+UrlNumber.eleven,		PlatformConst.PROJECT_TYPE, "上传"),
	
	_6_1_			("/galaxy/project/ap",		PlatformConst.PROJECT_TYPE, "进入接触访谈阶段"),
	_6_2_			("/galaxy/project/startReview",		PlatformConst.PROJECT_TYPE, "进入内部评审阶段"),
	_6_3_			("/galaxy/project/stageChange/"+UrlNumber.two,		PlatformConst.PROJECT_TYPE, "进入CEO评审阶段"),
	_6_4_			("/galaxy/project/ges",		PlatformConst.PROJECT_TYPE, "进入立项会阶段"),
	_6_5_			("/galaxy/project/stageChange/"+UrlNumber.four,		PlatformConst.PROJECT_TYPE, "进入投资意向书阶段"),
	_6_6_			("/galaxy/project/stageChange/"+UrlNumber.six,		PlatformConst.PROJECT_TYPE, "进入尽职调查阶段"),
	_6_7_			("/galaxy/project/smp",		PlatformConst.PROJECT_TYPE, "进入投资决策会阶段"),
	_6_8_			("/galaxy/project/stageChange/"+UrlNumber.eight,		PlatformConst.PROJECT_TYPE, "进入投资协议阶段"),
	_6_9_p1_			("/galaxy/project/stageChange/"+UrlNumber.eleven,		PlatformConst.PROJECT_TYPE, "进入股权交割阶段"),
	_6_9_p2_			("/galaxy/project/stageChange/"+UrlNumber.twelve,		PlatformConst.PROJECT_TYPE, "进入股权交割阶段"),
	_6_10_p1_			("/galaxy/soptask/submitTask/"+UrlNumber.four,		PlatformConst.PROJECT_TYPE, "进入投后运营阶段"),
	_6_10_p2_			("/galaxy/soptask/submitTask/"+UrlNumber.six,		PlatformConst.PROJECT_TYPE, "进入投后运营阶段"),
	
	
	PRO_HEALTH			("/galaxy/health/addhealth",		PlatformConst.PROJECT_TYPE, "项目健康度"),
	ADD_OPERAT_MEETING		("/galaxy/project/postOperation/saveMeeting/"+UrlNumber.one,		PlatformConst.PROJECT_TYPE, "添加"),
	EDIT_OPERAT_MEETING		("/galaxy/project/postOperation/saveMeeting/"+UrlNumber.two,		PlatformConst.PROJECT_TYPE, "编辑"),
	
	
	_15_1_	    ("/galaxy/projectTransfer/applyTransfer",		PlatformConst.PROJECT_TYPE, "移交"),
	_15_2_		("/galaxy/projectTransfer/undoTransfer",		PlatformConst.PROJECT_TYPE, "撤销移交"),
	_15_3_		("/galaxy/projectTransfer/rejectTransfer",		PlatformConst.PROJECT_TYPE, "接受"),
	_15_4_	    ("/galaxy/projectTransfer/receiveTransfer",		PlatformConst.PROJECT_TYPE, "拒绝");

	private OperationType(String uniqueKey, String type, String content, Integer module) {
		this.uniqueKey = uniqueKey;
		this.type = type;
		this.content = content;
		this.module = module;
	}

	private OperationType(String uniqueKey, String type, String content) {
		this.uniqueKey = uniqueKey;
		this.type = type;
		this.content = content;
	}

	public static OperationType getObject(String uniqueKey) {
		OperationType[] types = OperationType.values();
		OperationType result = null;
		for (OperationType type : types) {
			if (type.getUniqueKey()!=null && type.getUniqueKey().trim().length()>0 && uniqueKey.contains(type.getUniqueKey())) {
				result = type;
				break;
			}
		}
		return result;
	}

	private String type;
	private String content;
	private String uniqueKey;
	private Integer module;

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public Integer getModule() {
		return module;
	}

	public static Integer getModule(Long roleId) {
		if (null == roleId)
			return null;
		if (roleId.longValue() == 1 || roleId.longValue() == 2) {// 高管：董事长，CEO
			return PlatformConst.MODULE_BROADCAST_MESSAGE;
		}
		return null;
	}

}
