package com.galaxyinternet.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.filter.FilterUtil;
import com.galaxyinternet.framework.core.utils.SessionUtils;
import com.galaxyinternet.framework.core.utils.StringEx;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.user.User;


/**
 * 附：每个用户登陆成功点，都会查询出该用户所拥有的资源项，Session中存一份，前端JS存一份
 * 菜单类型的资源项
 * 显示：由请求后台生成菜单接口生成
 * 请求：根据url后附带的mark在此拦截器校验
 * 按钮类型的资源项
 * 显示：根据前端JS存的资源项进行校验
 * 请求：根据url后附带的mark在此拦截器校验
 * @author vector
 */
public class CheckAuthorityFIlter implements Filter{
	
	/**
	 * 对所有请求都校验
	 * 此项配置为true时，checkUrls则无效
	 */
	static boolean checkAllUrl = false;
	/**
	 * 必须进行校验点
	 */
	static String[] checkUrls = {};

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		boolean checked = false;
		if(checkAllUrl){
			checked = true;
		}else{
			String url = request.getRequestURI();
			boolean loginFlag = true;
			loginFlag = FilterUtil.judgeFile(url);
			if (!loginFlag) {
				chain.doFilter(request, response);
				return;
			}
			for (String u : checkUrls) {
				if (url.contains(StringEx.replaceSpecial(u))) {
					checked = true;
					break;
				}
			}
		}
		
		
		String resourceMark = SessionUtils.getValueFromRequest(request, Constants._session_resource_mark_key_);
		User user  = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if(checked){
			if(StringUtils.isNotBlank(resourceMark)){
				List<PlatformResource> allResourceToUser = user.getAllResourceToUser();
				for(PlatformResource resource : allResourceToUser){
					if(resource.getResourceMark().equals(resourceMark)){
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		checkUrls = FilterUtil.getWebXmlConfigParamters(config,"checkUrls");
		checkAllUrl = FilterUtil.decrypEncrypAllRequeset(config, "checkAllUrl");
	}
	
	
	@Override
	public void destroy() {
		
	}

}
