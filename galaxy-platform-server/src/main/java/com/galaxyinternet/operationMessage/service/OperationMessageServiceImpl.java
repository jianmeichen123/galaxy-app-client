package com.galaxyinternet.operationMessage.service;

import static com.galaxyinternet.utils.ValidationUtil.isMoreThan;
import static com.galaxyinternet.utils.ValidationUtil.isNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.OperationMessageBo;
import com.galaxyinternet.dao.operationMessage.OperationMessageDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.handler.MessageHandler;
import com.galaxyinternet.model.operationMessage.OperationMessage;
import com.galaxyinternet.service.OperationMessageService;
@Service("com.galaxyinternet.service.OperationMessageService")
public class OperationMessageServiceImpl extends BaseServiceImpl<OperationMessage>implements OperationMessageService,InitializingBean,ApplicationContextAware {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext context;
	private List<MessageHandler> handlers = null;
	@Autowired
	private OperationMessageDao operationMessageDao;
	
	@Override
	protected BaseDao<OperationMessage, Long> getBaseDao() {
		return this.operationMessageDao;
	}

	@Override
	public Long insert(OperationMessage entity) {
		isNull(OperationMessage.COMMENT,entity);
/*		isEmptyOrMoreThan(OperationMessage.DEPARTMENT, entity.getDepartment(), 80);
		isEmptyOrMoreThan(OperationMessage.ROLE, entity.getRole(), 50);
		isEmptyOrMoreThan(OperationMessage.TYPE, entity.getType(), 50);
		isEmptyOrMoreThan(OperationMessage.PROJECT_NAME, entity.getProjectName(), 200);
		isEmptyOrMoreThan(OperationMessage.OPERATOR, entity.getOperator(), 100);
		isEmptyOrMoreThan(OperationMessage.CONTENT, entity.getContent(), 500);*/
		isMoreThan(OperationMessage.DEPARTMENT, entity.getDepartment(), 80);
		isMoreThan(OperationMessage.ROLE, entity.getRole(), 50);
		isMoreThan(OperationMessage.TYPE, entity.getType(), 50);
		isMoreThan(OperationMessage.PROJECT_NAME, entity.getProjectName(), 200);
		isMoreThan(OperationMessage.OPERATOR, entity.getOperator(), 100);
		isMoreThan(OperationMessage.CONTENT, entity.getContent(), 500);
		
		Integer module = entity.getModule();
		//isNull(OperationMessage.MODULE,module);
		module = module == null ? 0 : module;
		//if(!(module==1||module==2||module==3)){
			//throwPlatformException(MessageStatus.OPERATION_MESSAGE_MODULE_ERROR);
		//}
		entity.setModule(module);
		entity.setCreatedTime(System.currentTimeMillis());
		return operationMessageDao.insert(entity);
	}
	
/*	@Override
	public Page<OperationMessage> queryPageList(OperationMessageBo query, Pageable pageable) {
		isNull(OperationMessage.COMMENT,query);
		if(query.getModule()!= null &&query.getModule() == 2){
			query.setModule(null);
			List<String> projectIds = operationMessageDao.selectProjecIdsByOperatorId(query.getBelongUid());
			if(projectIds.size() == 0){
				return new Page<OperationMessage>(new ArrayList<OperationMessage>(), pageable,(long) 0);
			}
			query.setProjectIds(projectIds);
		}
		return operationMessageDao.selectPageList(query, pageable);
	}*/

	@Override
	public Long selectCount(OperationMessage query) {
		return operationMessageDao.selectCount(query);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.context = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		 Map<String, MessageHandler> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, MessageHandler.class, true, false);
		 if(map != null)
		 {
			 handlers = new ArrayList<MessageHandler>(map.values());
			 OrderComparator.sort(handlers);
		 }
		
	}

	@Override
	public OperationMessage process(OperationMessage message)
	{
		if(handlers != null)
		{
			for(MessageHandler handler : handlers)
			{
				if(handler.support(message))
				{
					return handler.handle(message);
				}
			}
		}
		return message;
	}

	
	

}
