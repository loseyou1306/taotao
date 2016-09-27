package com.taotao.my.content.jedis.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.my.content.jedis.dao.JedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Repository(value="jedisDao")
public class JedisDaoImpl implements JedisDao {
	//注入单机版连接对象
	@Resource(name="jedisPool")
	private JedisPool jedisPool; 
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String set = jedis.set(key, value);
		return set;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		return value;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long incr = jedis.incr(key);
		return incr;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long decr = jedis.decr(key);
		return decr;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long del = jedis.del(key);
		return del;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long hset = jedis.hset(key, field, value);
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String hget = jedis.hget(key, field);
		return hget;
	}

	@Override
	public Long hdel(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		Long hdel = jedis.hdel(key, field);
		return hdel;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long expire = jedis.expire(key, seconds);
		return expire;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long ttl = jedis.ttl(key);
		return ttl;
	}

}
