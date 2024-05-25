package com.payc.tool.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author yangshubao
 * Created on 2021/8/2
 */

@Slf4j
public class RedisLock implements AutoCloseable {

    private RedisTemplate redisTemplate;
    private String key;
    /**
     * 秒
     */
    private String value;

    /**
     * 过期时间，单位秒
     */
    private int expireTime;

    public RedisLock(RedisTemplate redisTemplate, String key, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.expireTime = expireTime;
        this.value = UUID.randomUUID().toString();
    }

    /**
     * 获取分布式锁
     *
     * @return
     */
    public boolean getLock() {
        RedisCallback<Boolean> redisCallback = connection -> {
            //设置NX
            RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.ifAbsent();
            //设置过期时间
            Expiration expiration = Expiration.seconds(expireTime);
            //序列化key
            byte[] redisKey = redisTemplate.getKeySerializer().serialize(key);
            //序列化value
            byte[] redisValue = redisTemplate.getValueSerializer().serialize(value);
            //执行setnx操作（获取锁的操作）
            Boolean result = connection.set(redisKey, redisValue, expiration, setOption);
            return result;
        };
        boolean lock = (boolean) redisTemplate.execute(redisCallback);
        log.info("获取锁结果{}", lock);
        return lock;
    }

    public Boolean unLock() {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);
        List<String> keys = Arrays.asList(key);
        Boolean result = (Boolean) redisTemplate.execute(redisScript, keys, value);
        log.info("释放锁的结果：" + result);
        return result;
    }

    @Override
    public void close() throws Exception {
        unLock();
    }
}
