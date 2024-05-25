package com.payc.tool.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yangshubao
 * Created on 2021/8/3
 */
@Component
public final class Redis2Util {

    @Autowired
    @Qualifier("secondRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    // ===========================common================================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间（秒）
     * @return boolean
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     *
     * @param key 键
     * @return long 过期时间
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return boolean true：存在 false：不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除多个缓存
     *
     * @param key 多个缓存的key
     * @return void
     */
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }


    // ========================= String ============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return java.lang.Object
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ============================ Map =================================

    /**
     * 根据redis键和hash键获取值
     *
     * @param key  redis键
     * @param item hash键
     * @return java.lang.Object
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取所有的键值对
     *
     * @param key 键
     * @return java.util.Map<java.lang.Object, java.lang.Object> 对应的多个键值
     */
    public Map<Object, Object> hGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 存HashMap
     *
     * @param key 键
     * @param map 多个键值
     * @return boolean
     */
    public boolean hSet(String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存hashmap并设置有效时间
     *
     * @param key  键
     * @param map  hash
     * @param time 有效时间(秒)
     * @return boolean
     */
    public boolean hSet(String key, Map<Object, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入hash的键和值，不存在则会直接创建一个hash
     *
     * @param key
     * @param item
     * @param value
     * @return boolean
     */
    public boolean hSet(String key, Object item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入hash的键和值，不存在则会直接创建一个hash,并设置有效时间
     *
     * @param key   redis key
     * @param item  hash键
     * @param value hash值
     * @param time  有效时间
     * @return boolean
     */
    public boolean hSet(String key, Object item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据redis的key和hash的key删除hash表中的数据，hash的key可以是多个
     *
     * @param key  redis的key
     * @param item hash的key 可以是多个
     * @return void
     */
    public void hDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================= Set =======================================

    /**
     * 根据key获取所有set的值
     *
     * @param key 键
     * @return java.util.Set<java.lang.Object>
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据key和某个值判断该set中是否存在该值
     *
     * @param key   键
     * @param value 要在set中查找的值
     * @return boolean
     */
    public boolean sHashValue(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向set中放值
     *
     * @param key    键
     * @param values set的值
     * @return long 返回的是放置成功的个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 向set中放值并设置缓存有效时间
     *
     * @param key    键
     * @param time   有效时间（秒）
     * @param values set的值
     * @return long 返回的是放置成功的个数
     */
    public long sSet(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set的长度
     *
     * @param key set
     * @return long
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除set中的元素，并返回此次移除的个数
     *
     * @param key    键
     * @param values 要移除的值
     * @return long 此次移除的个数
     */
    public long sDel(String key, Object... values) {
        try {
            Long remove = redisTemplate.opsForSet().remove(key, values);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ============================= list ==============================

    /**
     * 获取list中任意范围的值，
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置
     * @return java.util.List<java.lang.Object> 如果是0 到 -1则表示取出list的所有值
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取缓存中list的长度
     *
     * @param key 键
     * @return long list的长度
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据list索引获取值
     *
     * @param key   键
     * @param index 索引
     * @return java.lang.Object 该索引所对应的值
     */
    public Object lGet(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将实现创建好的list存入redis
     *
     * @param key   键
     * @param value list
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入多个值，没有list则会自动创建
     *
     * @param key    键
     * @param values 多个值
     * @return boolean
     */
    public boolean lSet(String key, Object... values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入list并设置有效时长
     *
     * @param key   键
     * @param value list集合
     * @param time  有效时长（秒）
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 存入多个值，没有会自动创建list
     *
     * @param key    键
     * @param time   有效时长（秒）
     * @param values 多个值
     * @return boolean
     */
    public boolean lSet(String key, long time, Object... values) {
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改对应的值
     *
     * @param key   键
     * @param index 索引
     * @param value 要修改的值
     * @return boolean
     */
    public boolean lUpdate(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从list中移除count个值为value
     *
     * @param key   键
     * @param count 想要移除的个数
     * @param value 要移除的值
     * @return long 实际移除的值
     */
    public long lDel(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
