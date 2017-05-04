package com.galaxyinternet.test.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.bo.PlatformResourceBo;
import com.galaxyinternet.model.auth.LoginResult;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.utils.AuthRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class AuthRequestTest {
	@Autowired
	private AuthRequest request;
	
	@Test
	public void testLogin()
	{
		LoginResult rtn = request.login("miaofu", "rh4vw1");
		System.err.println(rtn);
	}
	@Test
	public void testGetRoleId()
	{
		List<Long> ids = request.selectRoleIdByUserId(1L, "1");
		System.err.println(ids);
	}
	@Test
	public void getResource()
	{
		PlatformResourceBo query = new PlatformResourceBo();
		query.setUserId(1L);
		query.setParentId(0L);
		query.setResourceType("1");
		query.setCompanyId("1");
		
		List<PlatformResource> list = request.getResource(query);
		System.err.println(list);
	}
	
}
