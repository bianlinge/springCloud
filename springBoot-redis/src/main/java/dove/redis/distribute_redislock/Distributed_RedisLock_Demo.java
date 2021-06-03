package dove.redis.distribute_redislock;

/**
 * 分布式锁 -基于redis分布式锁demo
 */
public class Distributed_RedisLock_Demo {
    public void test(String key) {
        CommonRedisHelper redisHelper = new CommonRedisHelper();
        //分布式锁
        boolean lock = redisHelper.lock(key);
        if (lock) {
            // 执行事务逻辑操作 事务逻辑操作
            redisHelper.delete(key);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while (failCount <= 5) {
                // 等待100ms重试
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisHelper.lock(key)) {
                    // 执行逻辑操作 事务逻辑操作
                    redisHelper.delete(key);
                } else {
                    failCount++;
                }
            }
            throw new RuntimeException("现在创建的人太多了, 请稍等再试");
        }
    }

}
