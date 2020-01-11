package com.shibo.redisson;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * @author: shibo
 * @Date: Created in 2019/10/30 20:46
 */
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    private static RedissonClient redisson;

    /**
     * 通过配置文件初始化
     */
    static{
        logger.info("init redisson ...");
        Config config = null;
        try {
            config = Config.fromJSON(ResourceUtils.getFile("classpath:config/redisson-config.yml"));
        } catch (Exception e) {
            logger.error("RedissonClient init failed !", e);
        }
        redisson = Redisson.create(config);
    }


    /**
     * 获取Redisson的实例对象
     * @return
     */
    public static Redisson getRedisson(){
        if(redisson == null){
            logger.info("RedissonClient init failed !");
            return null;
        }
        return (Redisson) redisson;
    }

}
