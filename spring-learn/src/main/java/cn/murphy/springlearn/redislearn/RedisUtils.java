package cn.murphy.springlearn.redislearn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private static JedisPool jedisPool;


    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig,"192.172.50.32",6379);
    }


    public static Jedis getJedis(){
       return jedisPool.getResource();
    }




}
