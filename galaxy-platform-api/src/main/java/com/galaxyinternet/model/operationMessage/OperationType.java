package com.galaxyinternet.model.operationMessage;

public enum OperationType {

	QUERY("", OperationType.PROJECT_TYPE, "查看"), 
	CREATE("", OperationType.PROJECT_TYPE, "创建"), 
	CLOSE("",OperationType.PROJECT_TYPE, "关闭"), 
	CEO_REVIEW_SCHEDULE("", OperationType.PROJECT_TYPE,"CEO评审排期"), 
	APPLY_PROJECT_SCHEDULE("", OperationType.PROJECT_TYPE,"申请立项会排期"), 
	APPLY_VOTE_SCHEDULE("", OperationType.PROJECT_TYPE, "申请投决会排期"),
	SIGNED_INVESTMENT_INTENT("", OperationType.PROJECT_TYPE, "签署投资意向书",OperationType.MODULE_BROADCAST_MESSAGE),
	OPEN_DUE_DILIGENCE_INVESTIGATION("", OperationType.PROJECT_TYPE, "尽职调查开启"),
	SIGNED_INVESTMENT_AGREEMENT("", OperationType.PROJECT_TYPE, "签署投资协议",OperationType.MODULE_BROADCAST_MESSAGE),
	INTO_DELIVERY_STAGE("", OperationType.PROJECT_TYPE, "进入交割阶段"),
	ADD_INTERVIEW_RECORD("", OperationType.PROJECT_TYPE, "添加访谈记录"),
	ADD_MEETING_RECORD("", OperationType.PROJECT_TYPE, "添加回忆纪要"),
	CLAIM_TASK("", OperationType.TASK_TYPE, "认领任务"),
	COMPLETE_TASK("", OperationType.TASK_TYPE, "完成任务");
	
	public static final String REQUEST_SCOPE_PROJECT_NAME = "galaxy_project_name";
	static final String PROJECT_TYPE = "项目";
	static final String TASK_TYPE = "任务";
	static final Integer MODULE_BROADCAST_MESSAGE = 1;//消息广播

	private OperationType(String uniqueKey, String type, String content,Integer module) {
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
	public static Integer getModule(Long roleId){
		if(null == roleId) return null;
		if(roleId.longValue() == 1 || roleId.longValue() == 2){//高管：董事长，CEO
			return OperationType.MODULE_BROADCAST_MESSAGE;
		}
		return null;
	}

}
