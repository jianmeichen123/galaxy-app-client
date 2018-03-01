package com.galaxyinternet.user.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.department.DepartmentDao;
import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.cache.Cache;
import com.galaxyinternet.framework.cache.CacheHelper;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.platform.constant.PlatformConst;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.util.SafeEncoder;

@Service
public class BaseInfoCacheService implements InitializingBean
{
	@Autowired
	private Cache cache;
	@Autowired
	private DepartmentDao depDao;
	@Autowired
	private UserDao userDao;
	
	public void setCache()
	{
		ShardedJedis jedis = null;
		try
		{
			CacheHelper helper = new CacheHelper();
			jedis = cache.getJedis();
			Set<String> depIds = jedis.smembers(PlatformConst.CACHE_DEP_IDS);
			ShardedJedisPipeline pip = jedis.pipelined();
			//删除旧数据 - 部门
			for(String depId : depIds)
			{
				pip.del(PlatformConst.CACHE_PREFIX_DEP+depId);
				pip.del(PlatformConst.CACHE_PREFIX_DEP_USERS+depId);
			}
			pip.del(PlatformConst.CACHE_DEP_IDS);
			pip.sync();
			//重新添加缓存 - 部门
			List<Department> depList = depDao.selectView();
			if(depList != null && depList.size()>0)
			{
				pip = jedis.pipelined();
				for(Department dep : depList)
				{
					pip.sadd(PlatformConst.CACHE_DEP_IDS, dep.getId()+"");
					pip.hset(SafeEncoder.encode(PlatformConst.CACHE_PREFIX_DEP+dep.getId()), SafeEncoder.encode("name"), helper.objectToBytes(dep.getName()));
					if(dep.getManagerId() != null)
					{
						pip.hset(SafeEncoder.encode(PlatformConst.CACHE_PREFIX_DEP+dep.getId()), SafeEncoder.encode("manager"), helper.objectToBytes(dep.getManagerId()));
					}
				}
				pip.sync();
			}
			
			Set<String> userIds = jedis.smembers(PlatformConst.CACHE_USER_IDS);
			pip = jedis.pipelined();
			//删除旧数据 - 用户
			for(String userId : userIds)
			{
				pip.del(PlatformConst.CACHE_PREFIX_USER+userId);
			}
			pip.del(PlatformConst.CACHE_USER_IDS);
			pip.sync();
			List<User> userList = userDao.selectView(new User());
			if(userList != null && userList.size() > 0)
			{
				pip = jedis.pipelined();
				for(User user : userList)
				{
					pip.sadd(PlatformConst.CACHE_USER_IDS, user.getId()+"");
					pip.hset(SafeEncoder.encode(PlatformConst.CACHE_PREFIX_USER+user.getId()), SafeEncoder.encode("realName"), helper.objectToBytes(user.getRealName()));
					pip.sadd(PlatformConst.CACHE_PREFIX_DEP_USERS+user.getDepartmentId(), user.getId()+"");
				}
				pip.sync();
			}
			
		} 
		finally
		{
			if(jedis != null)
			{
				cache.returnJedis(jedis);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		setCache();
	}
	
}
