package com.zfenrir.core.redis;

import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;

public class ZfenrirStringRedisSerializer extends StringRedisSerializer{

    /**
     * key前缀
     */
    private String keyPrefix;
    
    public ZfenrirStringRedisSerializer(@NonNull String keyPrefix) {
        if (keyPrefix == null) {
            throw new IllegalArgumentException();
        }
        this.keyPrefix = keyPrefix;
    }
    
    @Override
    public String deserialize(byte[] bytes) {
        String allKey = new String(bytes);
        int indexOf = allKey.indexOf(keyPrefix);
        if (indexOf == -1) {
            //说明缺失前缀
            return super.deserialize(bytes);
        }
        return super.deserialize(allKey.substring(indexOf).getBytes());
    }
    
    @Override
    public byte[] serialize(String string) {
        return super.serialize(keyPrefix.concat(string));
    }
}
