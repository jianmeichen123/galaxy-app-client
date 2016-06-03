package com.galaxyinternet.test.resource;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.service.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class ResourceServiceTest
{
	@Autowired
	private ResourceService resourceService;
	@Test
	public void testAdd()
	{
		PlatformResource entity = new PlatformResource();
		entity.setParentId(0L);
		entity.setResourceMark("root");
		entity.setResourceName("根节点");
		entity.setResourceUrl("/");
		entity.setResourceType("1");
		entity.setResourceOrder(1L);
		entity.setResourceDesc("这是根节点");
		entity.setResourceStatus("1");
		entity.setProductMark("platform");
		entity.setCreatedUid(2L);
		entity.setCreatedTime(new Date().getTime());
		entity.setUpdatedUid(3L);
		entity.setUpdatedTime(new Date().getTime());
		resourceService.insert(entity);
		Assert.notNull(entity.getId());
		
	}
	@Test
	public void testQuery()
	{
		try
		{
			List<PlatformResource> list = resourceService.queryAll();
			if(list != null && list.size() > 0)
			{
				PlatformResource entity =list.iterator().next();
				PlatformResource po = resourceService.queryById(entity.getId());
				Assert.notNull(po);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.isTrue(false, e.getMessage());
		}
	}
	@Test
	public void testUpdate()
	{
		try
		{
			List<PlatformResource> list = resourceService.queryAll();
			if(list != null && list.size() > 0)
			{
				PlatformResource entity =list.iterator().next();
				entity.setParentId(entity.getParentId()+1L);
				entity.setResourceMark(entity.getResourceMark()+" update");
				entity.setResourceName(entity.getResourceName()+" update");
				entity.setResourceUrl(entity.getResourceUrl()+" update");
				entity.setResourceType("3");
				entity.setResourceOrder(4L);
				entity.setResourceDesc("这是根节点 update");
				entity.setResourceStatus("2");
				entity.setProductMark("sop");
				entity.setCreatedUid(3L);
				entity.setCreatedTime(new Date().getTime());
				entity.setUpdatedUid(4L);
				entity.setUpdatedTime(new Date().getTime());
				resourceService.updateById(entity);
				
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.isTrue(false, e.getMessage());
		}
	}
	@Test
	public void testDel()
	{
		try
		{
			resourceService.deleteAll();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.isTrue(false, e.getMessage());
		}
	}
}
