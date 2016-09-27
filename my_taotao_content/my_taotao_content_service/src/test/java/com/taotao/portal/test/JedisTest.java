package com.taotao.portal.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	//测试单机版交给spring管理
	
	private ApplicationContext app;
	@Before
	public void init(){
		//加载spring配置文件
		app=new ClassPathXmlApplicationContext("classpath*:applicationContext-jedis.xml");
	}
	@Test
	public void springRedisOne(){
		//获取连接池对象
		JedisPool jedisPool = (JedisPool) app.getBean("jedisPool");
		//从连接池中获取jedis对象
		Jedis jedis = jedisPool.getResource();
		//使用jedis对象操作redis数据库
		jedis.set("email","aa@qq.com");
		System.out.println(jedis.get("email"));
	}
	
	//测试集群版交给spring管理
	@Test
	public void springClusterJedisTest(){
		//从spring容器中获取几区你对象代理对象
		JedisCluster jedisCluster = (JedisCluster) app.getBean("jedisCluster");
		//使用集群对象操作集群数据库
		jedisCluster.set("address", "北京市海淀区");
		System.out.println(jedisCluster.get("address"));
		
	}
}
