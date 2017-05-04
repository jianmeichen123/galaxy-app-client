package com.galaxyinternet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.model.auth.LoginResult;
import com.galaxyinternet.model.resource.PlatformResource;

public class AuthRequest {
	
	private String authURI;
	private RestTemplate template = new RestTemplate();
	
	public LoginResult login(String userName, String password)
	{
		String uri = authURI + "/login/userLogin";
		Map<String, String> urlVariables = new HashMap<>();
		userName = PWDUtils.decodePasswordByBase64(userName);
		password = PWDUtils.decodePasswordByBase64(password);
		urlVariables.put("userName", userName);
		urlVariables.put("passWord", password);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, String>> request = new HttpEntity<>(urlVariables, headers);
		ResponseEntity<LoginResult> rtn = template.postForEntity(uri, request, LoginResult.class);
		return rtn.getBody();
	}
	
	public List<PlatformResource> getResource(PlatformResource entity)
	{
		String uri = authURI + "/user/getUserResource";
		ParameterizedTypeReference<ArrayList<PlatformResource>> ref = new ParameterizedTypeReference<ArrayList<PlatformResource>>() {};  
		ResponseEntity<ArrayList<PlatformResource>> rtn = template.exchange(uri, HttpMethod.POST, new HttpEntity<>(entity), ref);
		return rtn.getBody();
	}
	
	public List<Long> selectRoleIdByUserId(Long userId,String companyId)
	{
		String uri = authURI + "/role/selectRoleIdByUserId?userId="+userId+"&companyId="+companyId;
		ParameterizedTypeReference<ArrayList<Long>> ref = new ParameterizedTypeReference<ArrayList<Long>>() {};  
		ResponseEntity<ArrayList<Long>> rtn = template.exchange(uri, HttpMethod.GET, new HttpEntity<>(null), ref);
		return rtn.getBody();
	}
	

	public String getAuthURI() {
		return authURI;
	}

	public void setAuthURI(String authURI) {
		this.authURI = authURI;
	}
	
	

}
