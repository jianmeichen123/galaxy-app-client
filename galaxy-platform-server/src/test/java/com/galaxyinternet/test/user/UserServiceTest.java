package com.galaxyinternet.test.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.RoleService;
import com.galaxyinternet.service.UserRoleService;
import com.galaxyinternet.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class UserServiceTest   {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private DepartmentService departmentService;

	
	@Test
	public void testQuery() throws Exception {
		List<User> userList = userService.queryAll();
		System.out.println(userList.size());
	}
	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setNickName("test");
		long  value = userService.insert(user);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = new User();
		user.setNickName("test");
		long  value = userService.updateById(user);
	}
	
	/**
	 * 重置密码
	 * @author zhaoying
	 * @throws Exception
	 */
	@Test
	public void resetPwd() throws Exception {
		User user = new User();
		user.setNickName("test");
		long  value = userService.insert(user);
		userService.resetPwd(value);
	}
	@Test
	public void getDepartmentByUserId() throws Exception {
		User user = new User();
		user.setNickName("test");
		long  value = userService.insert(user);
		userService.getDepartmentByUserId(value);
	}
	
	@Test
	public void selectRoleIdByUserId() throws Exception {
		User user = new User();
		user.setNickName("test");
		long  value = userService.insert(user);
		userRoleService.selectRoleIdByUserId(value);
	}
	
	@Test
	public void queryListByType() throws Exception {
	    Integer type = 1;
		departmentService.queryListByType(type);
	}
	
	@Test
	public void test() throws Exception {
		System.out.println("oo");
	}
	
}