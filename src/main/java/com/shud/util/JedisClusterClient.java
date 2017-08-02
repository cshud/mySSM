package com.shud.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shud on 2017/8/2.
 */
public class JedisClusterClient {
    /*
       单例模式，懒汉
     */
    private JedisClusterClient() {
    }

    private static class JedisClusterClientHandler {
        private static final JedisClusterClient jedisClusterClient = new JedisClusterClient();
    }

    public static JedisClusterClient getInstance() {
        return JedisClusterClientHandler.jedisClusterClient;
    }

    private JedisPoolConfig getPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(100);
        config.setTestOnBorrow(true);
        return config;
    }
    public Set<HostAndPort> getRedisCluster() {
        Set<HostAndPort> redisClusters = new HashSet<HostAndPort>();
        redisClusters.add(new HostAndPort("127.0.0.1", 6379));
        redisClusters.add(new HostAndPort("127.0.0.1", 6380));
        redisClusters.add(new HostAndPort("127.0.0.1", 6381));
        return redisClusters;
    }

    public JedisCluster init() {
        JedisCluster jedisCluster = new JedisCluster(getRedisCluster(),getPoolConfig());
        return jedisCluster;
    }

    public static void main(String[] args) {
        JedisClusterClient jedisClusterClient = JedisClusterClient.getInstance();
        JedisCluster jedisCluster = jedisClusterClient.init();

        for (int i = 0; i < 10; i++) {
            jedisCluster.del(String .valueOf(i));
        }
    }
}