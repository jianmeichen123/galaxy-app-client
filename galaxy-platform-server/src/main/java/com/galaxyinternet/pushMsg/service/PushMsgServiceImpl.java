package com.galaxyinternet.pushMsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.pushMsg.PushMsgDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.pushmsg.PushMsg;
import com.galaxyinternet.service.PushMsgService;
@Service("com.galaxyinternet.service.PushMsgService")
public class PushMsgServiceImpl extends BaseServiceImpl<PushMsg>implements PushMsgService{
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private  PushMsgDao msgDao;
	
	@Override
	protected BaseDao<PushMsg, Long> getBaseDao() {
		return this.msgDao;
	}



}
