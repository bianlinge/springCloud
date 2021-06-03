package dove.bean;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 使用apache.commons.beanutils.BeanUtils
 * 建议不要用,性能较差
 * 
 */
public class BeanCopy_Apache {
    private static final Logger logger = LoggerFactory.getLogger(BeanCopy_Apache.class);

    public static <T> T copyBean(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            //目标对象必须存在无惨构造
            target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            logger.error("对象转换错误");
            e.printStackTrace();
        }
        return target;
    }

    //对象转map
    private static Map<Object, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }
}
