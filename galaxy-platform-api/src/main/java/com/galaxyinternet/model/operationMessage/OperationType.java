package com.galaxyinternet.model.operationMessage;

import com.galaxyinternet.platform.constant.PlatformConst;

/**
 * 
 * @Description: 消息提醒规范类型
 *
 */
public enum OperationType {
	
	QUERY("/galaxy/project/sp", PlatformConst.PROJECT_TYPE, "查阅过项目"), 
	CREATE("/galaxy/project/ap", PlatformConst.PROJECT_TYPE, "创建"), 
	PRO_ADD_PER("/galaxy/project/app", PlatformConst.PROJECT_TYPE, "添加团队成员"), 
	PRO_EDIT_PER("/galaxy/project/upp", PlatformConst.PROJECT_TYPE, "修改团队成员"), 
	PRO_DEL_PER("/galaxy/project/dpp", PlatformConst.PROJECT_TYPE, "删除团队成员"), 
	PRO_INFO("/galaxy/project/getProjectInfo", PlatformConst.PROJECT_TYPE, "获取项目信息"),
	PRO_FILE_UP("/galaxy/project/stageChange", PlatformConst.PROJECT_TYPE, "文档上传"),
	PRO_START_REVIEW("/galaxy/project/startReview", PlatformConst.PROJECT_TYPE, "启动内部评审"),
	APPLY_PROJECT_SCHEDULE("/galaxy/project/ges", PlatformConst.PROJECT_TYPE,"申请立项会排期"), 
	APPLY_VOTE_SCHEDULE("/galaxy/project/smp/", PlatformConst.PROJECT_TYPE, "申请投决会排期"),
	
	ADD_INTERVIEW_FILE_RECORD("/galaxy/project/progress/addFileInterview", PlatformConst.PROJECT_TYPE, "添加访谈记录"),
	ADD_INTERVIEW_RECORD("//galaxy/project/progress/addInterview", PlatformConst.PROJECT_TYPE, "添加访谈记录"),
	ADD_MEETING_FILE_RECORD("/galaxy/project/progress/addfilemeet", PlatformConst.PROJECT_TYPE, "添加会议纪要"),
	ADD_MEETING_RECORD("/galaxy/project/progress/addmeet", PlatformConst.PROJECT_TYPE, "添加会议纪要"),
	
	CLOSE("/galaxy/project/breakpro",PlatformConst.PROJECT_TYPE, "关闭"), 
	
	CLAIM_TASK("galaxy/soptask/goClaimtcPage", PlatformConst.TASK_TYPE, "认领任务"),
	COMPLETE_TASK("/galaxy/soptask/updateTaskStatus", PlatformConst.TASK_TYPE, "完成任务"),
	
	
	ADD_SCHEDULING("/galaxy/project/updateReserveTime/" + UrlNumber.one, PlatformConst.SCHEDULING_TYPE, "创建会议排期"),
	UPDATE_SCHEDULING("/galaxy/project/updateReserveTime/"+ UrlNumber.two, PlatformConst.SCHEDULING_TYPE, "更新会议排期"),
	DELETE_SCHEDULING("/galaxy/project/updateReserveTime/"+ UrlNumber.three, PlatformConst.SCHEDULING_TYPE, "删除会议排期");

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
			if (uniqueKey.contains(type.getUniqueKey())) {
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
