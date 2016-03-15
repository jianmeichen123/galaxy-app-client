package com.galaxyinternet.platform.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.model.operationMessage.OperationMessage;
import com.galaxyinternet.model.operationMessage.OperationType;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.OperationMessageService;

/**
 * @description 消息提醒拦截器
 * @author keifer
 * @used 
 * 1.在你的controller中的方法中加入注解@Logger <br/>
 * 2.在方法处理前或后，在reqeust中设置操作的项目名称。例如：request.setParameter(OperationType.REQUEST_SCOPE_PROJECT_NAME,"星河互联创业项目") <br/>
 * 3.需要在springmvc配置文件中添加如下配置 <br/>
 * {@code
 * <mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**" />
			<bean class="com.galaxyinternet.platform.annotation.MessageHandlerInterceptor" />
		</mvc:interceptor>
	</mvc:interceptors>
}
 */
public class MessageHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	OperationMessageService operationMessageService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			String uniqueKey = request.getRequestURL().toString();
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Logger logger = method.getAnnotation(Logger.class);
			if (logger != null) {
				OperationType type = OperationType.getObject(uniqueKey);
				if (null != type) {
					User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
					OperationMessage entity = new OperationMessage();
					entity.setContent(type.getContent());
					entity.setDepartment(user.getDepartmentName());
					entity.setOperator(user.getRealName());
					entity.setRole(user.getRole());
					entity.setType(type.getType());
					entity.setProjectName(request.getParameter(OperationType.REQUEST_SCOPE_PROJECT_NAME));
					Integer module = type.getModule();
					entity.setModule(module == null ? OperationType.getModule(user.getRoleId()) : module);
					operationMessageService.insert(entity);
				}
			}
			OperationLog operationLog = method.getAnnotation(OperationLog.class);
			if (operationLog != null) {
				// TODO 这里添加SOP中操作操作日志的逻辑
			}
		}
		super.afterCompletion(request, response, handler, ex);
	}
}
