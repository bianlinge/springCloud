package dove.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 自定义线程池工具类
 *
 * @author E1026
 * @create 2019-05-20 9:03
 **/
public class ExecutorServiceUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExecutorServiceUtil.class);

    /**
     * 用于记录每次请求用时
     */
    private static ThreadLocal<Long> storageTime = new ThreadLocal<>();

    /**
     * Runtime.getRuntime().availableProcessors(): 获取cpu核心数
     */

    public static ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2,
             0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            Executors.defaultThreadFactory()) {
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            storageTime.set(System.currentTimeMillis());
            logger.info(" threadName [" + t.getName() + "]" + " execute task.");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (Objects.nonNull(t)) {
                logger.info("Successful call complete. " + "invoke method use time: " + (System.currentTimeMillis() - storageTime.get()) + " ms ");
            } else {
                logger.info("call fail.  " + "invoke method use time: " + (System.currentTimeMillis() - storageTime.get()) + " ms ");

            }
            storageTime.remove();
        }
    };


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}



