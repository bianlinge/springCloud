package thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by Dove on 2019/9/28 10:01
 */
public class MyThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    //设置线程工厂的名字
    public MyThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = "From myThreadFactoty"+whatFeatureOfGroup+"-worker-";
    }

    // @Override
    public Thread newThread(Runnable task) {
        //设置线程的名字
      /*  String name = namePrefix+nextId.getAndIncrement();
        Thread thread = new Thread(null,task,name,0,false);
        System.out.println(thread.getName());
        return thread;*/
        return null;
    }
}
