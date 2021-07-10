package dove.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 对象复制
 * spring中spring-beans包中的BeanUtils类
 * 浅拷贝: 只是调用子对象的set方法，并没有将所有属性拷贝。(也就是说，引用的一个内存地址)
 */
public class BeanCopy_springBeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanCopy_springBeanUtils.class);

    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            //目标对象必须存在无惨构造
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("目标对象必须存在无惨构造");
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
