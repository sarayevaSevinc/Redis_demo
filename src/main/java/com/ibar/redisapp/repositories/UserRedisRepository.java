package com.ibar.redisapp.repositories;

import com.ibar.redisapp.config.Serializer;
import com.ibar.redisapp.entities.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
@Repository
public class UserRedisRepository {
    private final String REDIS_KEY = "demo";
    private final HashOperations hashOperations;
    private RedisTemplate<String, Object> redisTemplate;

    public UserRedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(Serializer.createRedisSerializerForLongClass());

        redisTemplate.setHashValueSerializer(Serializer.createRedisSerializerForUserClass());
        this.hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire(REDIS_KEY,60, TimeUnit.MINUTES);
    }

    public Object addUser(User user) {
        log.info("adding user to redis service has started...");
        this.hashOperations.put(REDIS_KEY, user.getId(), user);
        log.info("adding user to redis service has ended...");
        return this.hashOperations.get(REDIS_KEY, user.getId());
    }

    public Map<Long, User> getAll() {
        return this.hashOperations.entries(REDIS_KEY);
    }

    public User getUser(long userid) {
        log.info("getting user from redis service has started ...");
        return (User) this.hashOperations.get(REDIS_KEY, userid);
    }
}
