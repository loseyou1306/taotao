package com.taotao.my.content.jedis.dao;

public interface JedisDao {
	/**抽取jedis常用方法
	 * 
	 */
	//strings
	public String set(String key,String value);
	//获取值
	public String get(String key);
	//自增
	public Long incr(String key);
	//自减
	public Long decr(String key);
	//删除
	public Long del(String key);
	
	//hashset
	public Long hset(String key, String field,String value);
	//获取值
	public String hget(String key, String field);
	//删除
	public Long hdel(String key, String field);
	
	//设置过期时间
	public Long expire(String key,int seconds); 
	//获取过期剩余时间
	public Long ttl(String key); 
}
