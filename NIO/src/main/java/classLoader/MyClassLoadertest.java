package classLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * Create by Dove on 2019/10/17 22:44
 */
public class MyClassLoadertest {
    public static void main(String[] args) throws ClassNotFoundException {
        PathClassLoader pathClassLoader = new PathClassLoader("C:\\Users\\Administrator.DESKTOP-0FG3OFN\\Desktop\\Java\\springCloud\\NIO\\target\\classes");

        Class<?> aClass = pathClassLoader.findClass("student.Student");

       String name = aClass.getName();
        System.out.println(name);
        URL resource = aClass.getResource("");
        System.out.println(resource.toString());

    }
}
