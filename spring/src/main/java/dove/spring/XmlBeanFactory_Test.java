package dove.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class XmlBeanFactory_Test {

    public static void main(String[] args) {
        //1. 根据 Xml 配置文件创建 Resource 资源对象，该对象中包含了 BeanDefinition 的信息

        ClassPathResource resource = new ClassPathResource("application-context.xml");
        // 2. 创建 DefaultListableBeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //创建 XmlBeanDefinitionReader 读取器，用于载入 BeanDefinition。
        // 之所以需要 BeanFactory 作为参数，是因为会将读取的信息回调配置给 factory
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // XmlBeanDefinitionReader 执行载入 BeanDefinition 的方法，最后会完成 Bean 的载入和注册。
// 完成后 Bean 就成功的放置到 IOC 容器当中，以后我们就可以从中取得 Bean 来使用

        reader.loadBeanDefinitions(resource);

        Person person = (Person)factory.getBean("person");
        System.out.println(person);

    }
}
