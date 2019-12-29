package classLoader;

import java.io.*;

/**
 * Create by Dove on 2019/10/17 22:20
 */
public class PathClassLoader extends ClassLoader {
    private String classPath;

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
       if ("Student.class".startsWith(name)) {
            byte[] classdata = getData(name);
            if (classdata == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classdata, 0, classdata.length);
            }
        } else {
            return super.loadClass(name);
        }
    }

    private byte[] getData(String classname) {
        String path = classPath + File.separatorChar + classname.replace('.', File.separatorChar) + ".class";
        try {
            FileInputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            byte[] buffer = new byte[2048];
            int num=0;
            while ((num=is.read(buffer))!=-1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
