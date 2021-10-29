package com.zfenrir.core.redis.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.zfenrir.core.redis.ZfenrirRedisService;

public class ZfenrirRedisServiceImpl implements ZfenrirRedisService {

    private RedisTemplate<String, Object> redisTemplate;

    public ZfenrirRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置有效期
     *
     * @param key
     * @param timeout
     * @param unit
     */
    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        if (unit == null) {
            unit = TimeUnit.SECONDS;
        }
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取所有匹配成功的key set集合
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 向名称为key的hash中添加元素hashKey
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 向名称为key的hash中添加元素hashKey, 若hashKey对应value有值则不覆盖
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hSetIfAbsent(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 返回名称为key的hash中hashKey对应的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T hGet(String key, String hashKey) {
        return (T)redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 向名称为key的hash中添加元素hashMap
     *
     * @param key
     * @param hashMap
     */
    @Override
    public void hMSet(String key, Map<String, Object> hashMap) {
        redisTemplate.opsForHash().putAll(key, hashMap);
    }

    /**
     * 返回名称为key的hash中hashKeys i对应的value 键值对
     *
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public <T> Map<String, T> hMGet(String key, Collection<String> hashKeys) {
        Map<String, T> hashMap = new HashMap<>();
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        List<T> valueList = opsForHash.multiGet(key, hashKeys);
        int i = 0;
        for (String hashKey : hashKeys) {
            hashMap.put(hashKey, valueList.get(i));
            i++;
        }
        return hashMap;
    }

    /**
     * 返回名称为key的hash中hashKeys i对应的value 集合
     *
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public <T> List<T> hMGet2List(final String key, final Collection<String> hashKeys) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.multiGet(key, hashKeys);
    }

    /**
     * 返回名称为key的hash中所有键对应的value 键值对
     *
     * @param key
     * @return
     */
    @Override
    public <T> Map<String, T> hVals(String key) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.entries(key);
    }

    /**
     * 返回名称为key的hash中所有键对应的value 集合
     *
     * @param key
     * @return
     */
    @Override
    public <T> List<T> hVals2List(String key) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.values(key);
    }

    /**
     * 删除名称为key的hash中键为hashKey的域
     *
     * @param key
     * @param hashKeys
     */
    @Override
    public void hDel(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 返回名称为key的hash中元素个数
     *
     * @param key
     * @return
     */
    @Override
    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 名称为key的hash中是否存在键为hashKey的域
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 名称为key的hash是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 返回名称为key的hash中所有键
     *
     * @param key
     * @return
     */
    @Override
    public Set<String> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key).stream().map(t -> (String)t).collect(Collectors.toSet());
    }

    /**
     * 将名称为key的hash中hashKey的value增加Long
     *
     * @param key
     * @param hashKey
     * @param delta 变量增量
     * @return
     */
    @Override
    public Long hIncrBy(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 将名称为key的hash中hashKey的value增加Double
     *
     * @param key
     * @param hashKey
     * @param stepSize
     * @return
     */
    @Override
    public Double hIncrBy(String key, String hashKey, Double stepSize) {
        return redisTemplate.opsForHash().increment(key, hashKey, stepSize);
    }

    @Override
    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    @Override
    public void set(String key, Object val, long timeout) {
        redisTemplate.opsForValue().set(key, val, timeout, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAndSet(String key, Supplier<T> supplier) {
        return (T)redisTemplate.opsForValue().getAndSet(key, supplier.get());
    }

    @Override
    public <T> T getAndSetIfAbsent(String key, Supplier<T> supplier) {
        T t = get(key);
        if (t == null) {
            t = supplier.get();
            set(key, t);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys).stream().map(o -> (T)o).collect(Collectors.toList());
    }

    @Override
    public boolean setIfAbsent(String key, Object val) {
        return Objects.equals(redisTemplate.opsForValue().setIfAbsent(key, val), Boolean.TRUE);
    }

    @Override
    public <T> void leftPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T index(String key, long index) {
        return (T)redisTemplate.opsForList().index(key, index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end).stream().map(o -> (T)o).collect(Collectors.toList());
    }

    @Override
    public <T> void leftPush(String key, String pivot, T value) {
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    @Override
    public <T> void leftPushAll(String key, List<T> values) {
        redisTemplate.opsForList().leftPushAll(key, values.toArray());
    }

    @Override
    public <T> void rightPushAll(String key, List<T> values) {
        redisTemplate.opsForList().rightPushAll(key, values.toArray());
    }

    @Override
    public <T> void rightPushIfPresent(String key, T value) {
        redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    @Override
    public long listLength(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T leftPop(String key) {
        return (T)redisTemplate.opsForList().leftPop(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T leftPop(String key, long timeout, TimeUnit unit) {
        return (T)redisTemplate.opsForList().leftPop(key, timeout, unit);

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T rightPop(String key) {
        return (T)redisTemplate.opsForList().rightPop(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T rightPop(String key, long timeout, TimeUnit unit) {
        return (T)redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    @Override
    public <T> void sSet(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> members(String key) {
        return redisTemplate.opsForSet().members(key).stream().map(o -> (T)o).collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> randomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count).stream().map(o -> (T)o).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T randomMember(String key) {
        return (T)redisTemplate.opsForSet().randomMember(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T sPop(String key) {
        return (T)redisTemplate.opsForSet().pop(key);
    }

    @Override
    public long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public <T> boolean isMember(String key, T obj) {
        return redisTemplate.opsForSet().isMember(key, obj);
    }

    @Override
    public <T> boolean sMove(String key, T value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    @Override
    public <T> void sRemove(String key, Set<T> values) {
        redisTemplate.opsForSet().remove(key, values.toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> difference(String key, String destKey) {
        return redisTemplate.opsForSet().difference(key, destKey).stream().map(o -> (T)o).collect(Collectors.toSet());
    }

    @Override
    public <T> boolean zAdd(String key, T value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    public <T> boolean zAddIfAbsent(String key, T value, double score) {
        return redisTemplate.opsForZSet().addIfAbsent(key, value, score);
    }

    @Override
    public Long zAdd(String key, Set<TypedTuple<Object>> tuples) {
        return redisTemplate.opsForZSet().add(key, tuples);
    }

    @Override
    public  Long zAddIfAbsent(String key, Set<TypedTuple<Object>> tuples) {
        return redisTemplate.opsForZSet().addIfAbsent(key, tuples);
    }

    @Override
    public Long remove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end).stream().map(o -> (T)o).collect(Collectors.toSet());
    }

    @Override
    public Set<TypedTuple<Object>> rangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> rangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max).stream().map(o -> (T)o).collect(Collectors.toSet());
    }

    @Override
    public Set<TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> rangeByScore(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count).stream().map(o -> (T)o).collect(Collectors.toSet());
    }

    @Override
    public  Set<TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Long count(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    @Override
    public Long zSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Double score(String key, Object o) {
        return redisTemplate.opsForZSet().score(key, o);
    }

}
