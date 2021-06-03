package dove.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象复制
 * cglib3.2.8包中的net.sf.cglib.beans.BeanCopier类
 * 性能最好 建议使用此方式
 */
public class BeanCopy_cglib {

	private static final Logger logger = LoggerFactory.getLogger(BeanCopy_cglib.class);

	public static <T> T copyObject(Object source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
		T target = null;
		try {
			//需要复制的对象必须要有无参构造器
			target = targetClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			logger.error("实例化失败:{}", e.getMessage());
			e.printStackTrace();
		}
		beanCopier.copy(source, target, null);
		return target;
	}

	public static <T> List<T> copyObjectList(List<?> sourceList, Class<T> targetClass) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return new ArrayList<>();
		}
		List<T> list = new ArrayList<>(sourceList.size());
		for (Object source : sourceList) {
			list.add(copyObject(source, targetClass));
		}
		return list;
	}


}
