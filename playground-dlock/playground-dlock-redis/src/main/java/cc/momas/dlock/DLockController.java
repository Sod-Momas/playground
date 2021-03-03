package cc.momas.dlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author Sod-Momas
 * @since 2021-02-06
 */
@RestController
public class DLockController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(DLockController.class);

    @RequestMapping("/trylock")
    public String getLock(@RequestParam("name") String name) {
        Boolean lock = Boolean.FALSE;
        try {
            lock = redisTemplate.opsForValue().setIfAbsent(name, "");
            if (lock != null && lock) {
                logger.info("获取锁成功，开始执行任务");
                // 设置过期时间,防止挂了锁没释放,但也要注意时间不能太短，防止任务没有执行完就释放锁
                redisTemplate.expire(name, Duration.ofMinutes(30));
                run();
                return "执行完成";
            }
            logger.info("获取锁失败");
            return "没有获取到锁";
        } finally {
            if (lock != null && lock) {
                redisTemplate.delete(name);
                logger.info("任务结束，释放锁!");
            } else {
                logger.info("没有获取到锁，无需释放锁!");
            }
        }
    }

    @RequestMapping("/clean")
    public String cleanLock(@RequestParam("name") String name) {
        final Boolean delete = redisTemplate.delete(name);
        if (delete != null && delete) {
            logger.info("清除成功");
        } else {
            logger.info("清除失败");
        }
        return "清除锁完成";
    }

    private void run() {
        try {
            Thread.sleep(5000);
            logger.info("处理业务中");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
