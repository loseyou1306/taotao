package com.taotao.my.content.jedis.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.my.content.jedis.dao.JedisDao;

import net.sf.jsqlparser.expression.NullValue;
import redis.clients.jedis.JedisCluster;
@Repository(value="jedisClusterDao")
public class JedisClusterDaoImpl implements JedisDao {
	//获取集群版reis的jedisCluster对象
	@Resource(name="jedisCluster")
	private JedisCluster jedisCluster;
	@Override
	public String set(String key, String value) {
		String set = jedisCluster.set(key, value);
		return set;
	}

	@Override
	public String get(String key) {
		String value = jedisCluster.get(key);
		return value;
	}

	@Override
	public Long incr(String key) {
		Long incr = jedisCluster.incr(key);
		return incr;
	}

	@Override
	public Long decr(String key) {
		Long decr = jedisCluster.decr(key);
		return decr;
	}

	@Override
	public Long del(String key) {
		Long del = jedisCluster.del(key);
		return del;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Long hset = jedisCluster.hset(key, field, value);
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		String hget = jedisCluster.hget(key, field);
		return hget;
	}

	@Override
	public Long hdel(String key, String field) {
		Long hdel = jedisCluster.hdel(key, field);
		return hdel;
	}

	@Override
	public Long expire(String key, int seconds) {
		Long expire = jedisCluster.expire(key, seconds);
		return expire;
	}

	@Override
	public Long ttl(String key) {
		Long ttl = jedisCluster.ttl(key);
		return ttl;
	}

}
