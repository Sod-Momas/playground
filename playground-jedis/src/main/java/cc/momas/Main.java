package cc.momas;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Main {
    public static void main(String[] args) {
//    String host = "localhost";
        String host = "10.1.18.8";
        try (JedisPool pool = new JedisPool(host, 6379)) {
            Jedis jedis = pool.getResource();
            jedis.sadd("planets", "Venus");
        }
    }
}