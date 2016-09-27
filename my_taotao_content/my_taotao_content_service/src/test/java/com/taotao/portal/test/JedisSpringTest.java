package com.taotao.portal.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisSpringTest {
	//连接单机版的redis集群
	@Test
	public void linkRedisClusterOne(){
		//创建jedis对象,连接redis服务器
		Jedis jedis = new Jedis("192.168.93.128", 6379);
		//向redis服务器设置值
		jedis.set("username", "詹姆斯刘能");
		System.out.println("username:"+jedis.get("username"));
	}
	
	//使用连接池连接单机版redis
	@Test
	public void linkRedisOnePool(){
		//创建连接池配置对象
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		//设置最大连接数
		poolConfig.setMaxTotal(1000);
		//设置最大空闲数
		poolConfig.setMaxIdle(20);
		JedisPool jedisPool = new JedisPool(poolConfig, "192.168.93.128", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("hobby", "我喜欢java");
		System.out.println(jedis.get("hobby"));
		
	}
	//使用连接池连接redis集群
	@SuppressWarnings("resource")
	@Test
	public void linkRedisClusterPool(){
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(20);
		//创建set集合,封装集群节点:8个
		Set<HostAndPort> nodes=new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.93.128", 7001));
		nodes.add(new HostAndPort("192.168.93.128", 7002));
		nodes.add(new HostAndPort("192.168.93.128", 7003));
		nodes.add(new HostAndPort("192.168.93.128", 7004));
		nodes.add(new HostAndPort("192.168.93.128", 7005));
		nodes.add(new HostAndPort("192.168.93.128", 7006));
		nodes.add(new HostAndPort("192.168.93.128", 7007));
		nodes.add(new HostAndPort("192.168.93.128", 7008));
		JedisCluster jedisCluster = new JedisCluster(nodes, poolConfig);
		jedisCluster.set("username", "太极张三丰!!");
		System.out.println(jedisCluster.get("username"));
		
	}
}
