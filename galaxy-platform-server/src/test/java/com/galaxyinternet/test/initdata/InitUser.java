package com.galaxyinternet.test.initdata;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.core.WorkbookReader;
import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.constants.UserConstant;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.test.user.UserDB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class InitUser extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	private BaseService<Department> service;
	
	@Autowired
	private UserDao userDao;
	private String oriPass = "123456";
	private String address = "北京市海淀区上地创新大厦";
	
	@Test
	public void initUser() throws Exception {
		//读取用户的excel
		List<UserDB> userList = WorkbookReader.readIgnoreErrors("user.xlsx", "user.properties", UserDB.class);
		//读取部门的excel
		//List<Department> departmentList = WorkbookReader.readIgnoreErrors("department.xlsx", "department.properties", Department.class);
		List<Department> departmentList = service.queryAll();
		for(int i = 0; i<userList.size(); i++){
			UserDB user = userList.get(i);
			//设置性别
			if(user.getNickName().equals("男")){
				user.setGender(false);
			}else if(user.getNickName().equals("女")){
				user.setGender(true);
			}
			user.setNickName(null);
			//设置状态
			user.setStatus(UserConstant.NORMAL);
			//设置原始密码
			user.setOriginPassword(oriPass);
			//设置登录密码
			user.setPassword(PWDUtils.genernateNewPassword(oriPass));
			//设置地址
			user.setAddress(address);
			//设置昵称
			user.setNickName(user.getEmail());
			//设置departmentId
			Long departmentCode = user.getDepartmentCode();
			if(null==departmentCode){
				user.setDepartmentId(null);
			}else{
				for(int j = 0; j<departmentList.size(); j++){
					Long departmentId = departmentList.get(j).getId();
					if(departmentCode.longValue()==departmentId.longValue()){
						user.setDepartmentId(departmentId);
						break;
					}
				}
			}
			userDao.insert(user);
		}		
	}
}