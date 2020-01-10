package com.shibo.utils;

import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: yangjianbo
 * @Date: Created in 2019/10/30 20:43
 */
public class RedissonLockUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedissonLockUtil.class);

    private static Redisson redisson = RedissonManager.getRedisson();

    private static final String LOCK_FLAG = "jobclose_";

    /**
     * 根据name对进行上锁操作，redissonLock 阻塞的，采用的机制发布/订阅
     * @param key
     */
    public static void lock(String key){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁 ：1分钟
        lock.lock(1, TimeUnit.MINUTES);
        logger.info("lock key:{}", lockKey);
    }

    /**
     * 根据name对进行解锁操作
     * @param key
     */
    public static void unlock(String key){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        if (lock.isHeldByCurrentThread())
        {
            lock.unlock();
            logger.info("unlock , key:" + lockKey);
        }
    }

    /**
     * @param key
     * @param millisToWait 等待获取锁的时间--单位：秒
     */
    public static boolean tryLock(String key, long millisToWait) {
        String lockKey = LOCK_FLAG + key;
        logger.info("get redis lock start , key:" + lockKey);
        RLock lock = redisson.getLock(lockKey);
        logger.info("get redis lock end , key:" + lockKey);
        try {
            return  lock.tryLock(millisToWait,5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("try lock error,key is:", lockKey, e);
        }
        logger.info("get redis lock false , key:"+lockKey);
        return false;
    }
}
