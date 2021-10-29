package com.zfenrir.core.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

/**
 * zfenrir redis service
 * 
 * @author zhuliang
 * @Date 2021-10-28
 *
 */
public interface ZfenrirRedisService {

    /**
     * 设置有效期
     *
     * @param key
     * @param timeout
     * @param unit
     */
    void expire(String key, long timeout, TimeUnit unit);

    /**
     * 获取所有匹配成功的key set集合
     *
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);

    /**
     * 删除key
     *
     * @param key
     */
    void delete(String key);

    /**
     * 向名称为key的hash中添加元素hashKey
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 向名称为key的hash中添加元素hashKey, 若hashKey对应value有值则不覆盖
     *
     * @param key
     * @param hashKey
     * @param value
     */
    void hSetIfAbsent(String key, String hashKey, Object value);

    /**
     * 返回名称为key的hash中hashKey对应的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    <T> T hGet(String key, String hashKey);

    /**
     * 向名称为key的hash中添加元素hashMap
     *
     * @param key
     * @param hashMap
     */
    void hMSet(String key, Map<String, Object> hashMap);

    /**
     * 返回名称为key的hash中hashKeys i对应的value 键值对
     *
     * @param key
     * @param hashKeys
     * @return
     */
    <T> Map<String, T> hMGet(String key, Collection<String> hashKeys);

    /**
     * 返回名称为key的hash中hashKeys i对应的value 集合
     *
     * @param key
     * @param hashKeys
     * @return
     */
    <T> List<T> hMGet2List(final String key, final Collection<String> hashKeys);

    /**
     * 返回名称为key的hash中所有键对应的value 键值对
     *
     * @param key
     * @return
     */
    <T> Map<String, T> hVals(String key);

    /**
     * 返回名称为key的hash中所有键对应的value 集合
     *
     * @param key
     * @return
     */
    <T> List<T> hVals2List(String key);

    /**
     * 删除名称为key的hash中键为hashKey的域
     *
     * @param key
     * @param hashKeys
     */
    void hDel(String key, Object... hashKeys);

    /**
     * 返回名称为key的hash中元素个数
     *
     * @param key
     * @return
     */
    Long hLen(String key);

    /**
     * 名称为key的hash中是否存在键为hashKey的域
     *
     * @param key
     * @param hashKey
     * @return
     */
    boolean hasKey(String key, String hashKey);

    /**
     * 名称为key的hash是否存在
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 返回名称为key的hash中所有键
     *
     * @param key
     * @return
     */
    Set<String> hKeys(String key);

    /**
     * 将名称为key的hash中hashKey的value增加Long
     *
     * @param key
     * @param hashKey
     * @param delta 变量增量
     * @return
     */
    Long hIncrBy(String key, String hashKey, Long delta);

    /**
     * 将名称为key的hash中hashKey的value增加Double
     *
     * @param key
     * @param hashKey
     * @param stepSize
     * @return
     */
    Double hIncrBy(String key, String hashKey, Double stepSize);

    /**
     * 插入字符串key value
     *
     * @param key
     * @param val
     */
    void set(String key, Object val);

    /**
     * 插入key, 并设置有效期(单位s秒)
     *
     * @param key
     * @param val
     * @param timeout
     */
    void set(String key, Object val, long timeout);

    /**
     * 获取字符串, by key
     *
     * @param key
     * @return
     */
    <T> T get(String key);

    /**
     * 获取老的值并设置新的值
     * 
     * @param key
     * @param val
     * @return
     */
    <T> T getAndSet(String key, Supplier<T> supplier);
    /**
     * 获取老的值 如果值不存在在存入新值
     * 
     * @param key
     * @param val
     * @return
     */
    <T> T getAndSetIfAbsent(String key, Supplier<T> supplier);

    /**
     * 批量获取
     * 
     * @param <T>
     * @param keys
     * @return
     */
    <T> List<T> multiGet(Collection<String> keys);

    /**
     * 不存在key在保存
     * 
     * @param key
     * @param val
     */
    boolean setIfAbsent(String key, Object val);
    
    /**
    * 在变量左边添加元素值
    * @param key
    * @param value
    * @return
    */

    <T> void leftPush(String key, T value);

    /**
    * 获取集合指定位置的值。
    * @param key
    * @param index
    * @return
    */

    <T> T index(String key, long index);

    /**

    * 获取指定区间的值。

    *

    * @param key

    * @param start

    * @param end

    * @return

    */

    <T> List<T> range(String key, long start, long end);

    /**
    * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
    * @param key
    * @param pivot
    * @param value
    * @return
    */
    <T> void leftPush(String key, String pivot, T value);

    /**
    * 向左边批量添加参数元素。
    * @param key
    * @param values
    * @return
    */

    <T> void leftPushAll(String key, List<T> values);

    /**
    * 向左边批量添加参数元素。
    * @param key
    * @param values
    * @return
    */

    <T> void rightPushAll(String key, List<T> values);

    /**
    * 向已存在的集合中添加元素。
    * @param key
    * @param value
    * @return
    */

    <T> void rightPushIfPresent(String key, T value);

    /**
    * 获取集合中元素个数。
    * @param key
    * @return
    */

    long listLength(String key);

    /**
    * 移除集合中的左边第一个元素。
    * @param key
    * @return
    */

    <T> T leftPop(String key);

    /**
    * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
    * @param key
    * @return
    */

    <T> T leftPop(String key, long timeout, TimeUnit unit);

    /**
    * 移除集合中右边的元素。
    * @param key
    * @return
    */

    <T> T  rightPop(String key);

    /**
    * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
    * @param key
    * @return
    */

    <T> T rightPop(String key, long timeout, TimeUnit unit);
    
    /**
    * 将数据放入set缓存
    * @param key 键
    * @return
    */

    <T> void sSet(String key, T value);

    /**
    * 获取变量中的值
    * @param key 键
    * @return
    */

    <T> Set<T> members(String key);

    /**
    * 随机获取变量中指定个数的元素
    *
    * @param key 键
    * @param count 值
    * @return
    */

    <T> List<T> randomMembers(String key, long count);

    /**
    * 随机获取变量中的元素
    * @param key 键
    * @return
    */

    <T> T randomMember(String key);

    /**
    * 弹出变量中的元素
    * @param key 键
    * @return
    */

    <T> T sPop(String key);

    /**
    * 获取变量中值的长度
    * @param key 键
    * @return
    */

    long sSize(String key);

    /**
    * 检查给定的元素是否在变量中。
    * @param key 键
    * @param obj 元素对象
    * @return
    */

    <T> boolean isMember(String key, T obj);

    /**
    * 转移变量的元素值到目的变量。
    * @param key 键
    * @param value 元素对象
    * @param destKey 元素对象
    * @return
    */

    <T> boolean sMove(String key, T value, String destKey);

    /**
    * 批量移除set缓存中元素
    * @param key 键
    * @param values 值
    * @return
    */

    <T> void sRemove(String key, Set<T> values);

    /**
    * 通过给定的key求2个set变量的差值
    * @param key 键
    * @param destKey 键
    * @return
    */

    <T> Set<T> difference(String key, String destKey);
    
    /**
     * zset添加元素
     * @param <T>
     * @param key
     * @param value
     * @param score
     * @return
     */
    <T> boolean zAdd(String key, T value, double score);
    /**
     * zset添加元素 不存在则添加
     * @param <T>
     * @param key
     * @param value
     * @param score
     * @return
     */
    <T> boolean zAddIfAbsent(String key, T value, double score);

    /**
     * zset添加元素
     * @param <T>
     * @param key
     * @param tuples
     * @return
     */
    Long zAdd(String key, Set<TypedTuple<Object>> tuples);
    
    /**
     * zset添加元素 不存在则添加
     * @param <T>
     * @param key
     * @param tuples
     * @return
     */
    Long zAddIfAbsent(String key, Set<TypedTuple<Object>> tuples);
    
    /**
     * zset移除元素 
     * @param key
     * @param values
     * @return
     */
    Long remove(String key, Object... values);
    
    /**
     * zset根据开始 结束取出元素
     * @param <T>
     * @param key
     * @param start
     * @param end
     * @return
     */
    <T> Set<T> zRange(String key, long start, long end);
    
    /**
     * zset根据开始 结束取出元素包含分数
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<TypedTuple<Object>> rangeWithScores(String key, long start, long end);
    /**
     * zet根据score取出数据
     * @param <T>
     * @param key
     * @param min
     * @param max
     * @return
     */
    <T> Set<T> rangeByScore(String key, double min, double max);
    
    /**
     * zet根据score取出数据
     * @param <T>
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max);
    
    /**
     * zet根据score取出数据 和偏移量取出数据
     * @param <T>
     * @param key
     * @param min
     * @param max
     * @return
     */
    <T> Set<T> rangeByScore(String key, double min, double max, long offset, long count);
   
    /**
     * zet根据score取出数据 和偏移量取出数据
     * @param <T>
     * @param key
     * @param min
     * @param max
     * @return
     */
    <T> Set<TypedTuple<T>> rangeByScoreWithScores(String key, double min, double max, long offset, long count);

    /**
     * zset在sorce之间的元素数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long count(String key, double min, double max);
    
    /**
     * zset元素数量
     * @param key
     * @return
     */
    Long zSize(String key);
    
    /**
     * 元素的分数
     * @param key
     * @param o
     * @return
     */
    Double score(String key, Object o);
}
