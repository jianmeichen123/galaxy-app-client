package com.galaxyinternet.test.privilege;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.mongodb.MongoRespData;
import com.galaxyinternet.framework.core.utils.GSONUtil;
import com.galaxyinternet.model.privilege.bo.PrivilegeBo;
import com.galaxyinternet.privilege.service.PrivilegServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class PrivilegeTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	PrivilegServiceImpl service;

	@Test
	public void testSave() {
		MongoRespData<PrivilegeBo> result = service.getPrivilegeById("3964190666784770");
		PrivilegeBo bo = result.getEntity();
		bo.set_id("3964190666784769");
		service.insertPrivilege(GSONUtil.toJson(bo));

	/*	PrivilegeBo pb = new PrivilegeBo();
		pb.set_id("ddddddd");
		pb.setUserId(4444444L);
		service.getRepository().save(pb);*/
		
	}

	@Test
	public void testUpdate() {
		MongoRespData<PrivilegeBo> reult = service.updatePrivilege("{\"_id\":\"3964190666784769\",\"userId\":55555}");
		Assert.assertEquals(reult.getEntity().getUserId().longValue(), 55555L);
	}

	@Test
	public void testDelete() {
		service.deletePrivilegeById("3964190666784769");
	}

	@Test
	public void testFindById() {
		MongoRespData<PrivilegeBo> result = service.getPrivilegeById("3964190666784769");
		Assert.assertEquals(result.getResult().getStatus(), Status.OK);
	}

	@Test
	public void testFindByUserId() {
		MongoRespData<PrivilegeBo> result = service.getPrivilegeByUserId(55555L);
		Assert.assertEquals(result.getResult().getStatus(), Status.OK);
	}
}