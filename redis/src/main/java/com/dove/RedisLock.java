package com.dove;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * redis实现分布式锁
 */
public class RedisLock {
    public String getLock(String key, int timeout) {
        try {
            Jedis jedis = RedisMannger.getJedis();

            String value = UUID.randomUUID().toString();
            long end = System.currentTimeMillis() + timeout;
            while (System.currentTimeMillis() < end) {//阻塞
                if (jedis.setnx(key, value) == 1) {
                    jedis.expire(key, timeout);
                    //锁设置成功 redis操作成功
                    return value;
                }
                if (jedis.ttl(key) == -1) {//检测过期时间
                    jedis.expire(key, timeout);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean releaseLock(String key, String value) {

        try {
            Jedis jedis = RedisMannger.getJedis();

            while (true) {
                //watch一般是和事务一起使用，当对某个key进行watch后如果其他的客户端对这个key进行了更改，那么本次事务会被取消，
                // 事务的exec会返回null。jedis.watch(key)都会返回OK
                jedis.watch(key);
                if (value.equals(jedis.get(key))) {
                    Transaction transaction = jedis.multi();//命令用于标记一个事务块的开始
                    transaction.del(key);
                    List<Object> exec = transaction.exec();// EXEC 命令原子性(atomic)地执行
                    if (exec == null) {
                        continue;
                    }
                    return true;
                }
                jedis.unwatch();
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) throws InterruptedException {
        RedisLock redisLock=new RedisLock();
        String lockId=redisLock.getLock("lock:ccc",1000);
        if(null!=lockId){
            System.out.println(lockId+"获得锁成功");
        }

        redisLock.releaseLock("lock:ccc",lockId);
        String l=redisLock.getLock("lock:ccc",1000);
        System.out.println(l);

    }
}
