package sla.data;

import redis.clients.jedis.Jedis;

public class KeyValueCache {

	private final static int COMMON_EXPIRE_SECOND =  24 * 60 * 60; // 1일 정도 캐시에 저장해둔다.
	private Jedis jedis;
	public KeyValueCache(Jedis jedis,String password){
		this.jedis=jedis;
		this.jedis.auth(password);
	}
	public void setStringWithKey(String key,String value){
		System.out.println("setex "+key+" "+COMMON_EXPIRE_SECOND+" "+value);
		jedis.setex(key, COMMON_EXPIRE_SECOND, value);
	}
	public String getStringByKey(String key){
		//jedis.expire(key, COMMON_EXPIRE_SECOND); //읽는 순간 만료시간 재설정 
		return jedis.get(key);
	}
	public String getStringAndResetExpireByKey(String key){
		jedis.expire(key, COMMON_EXPIRE_SECOND); //읽는 순간 만료시간 재설정 
		return jedis.get(key);
	}
	public boolean exists(String key){
		return jedis.exists(key);
	}
	
	public String generateIpKey(String ip,String shortUrl){
		return "url:"+shortUrl+":"+ip;
	}
}
