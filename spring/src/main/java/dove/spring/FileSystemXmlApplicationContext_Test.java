package dove.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class FileSystemXmlApplicationContext_Test {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext contex = new FileSystemXmlApplicationContext("classpath:application-context.xml");
        Object person = contex.getBean("person");
        System.out.println(person);
    }

}
