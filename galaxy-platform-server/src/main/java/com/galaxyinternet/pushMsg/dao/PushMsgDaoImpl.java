package com.galaxyinternet.pushMsg.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.pushMsg.PushMsgDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.pushmsg.PushMsg;

@Repository("pushMsgDao")
public class PushMsgDaoImpl extends BaseDaoImpl<PushMsg, Long> implements PushMsgDao {

	
	
}