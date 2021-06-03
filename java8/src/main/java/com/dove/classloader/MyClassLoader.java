package com.dove.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    private String path;
    private String name;

    public MyClassLoader(String name, String path) {
        super();//让系统类加载器作为父加载器
        this.name = name;
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent, String name, String path) {
        super(parent);// 指定父类加载器
        this.name = name;
        this.path = path;
    }

    @Override
    protected Class<?> findClass(final String name) {
        byte[] data = readClassFileToByteArray(name);
        return defineClass(name, data, 0, data.length);
    }

    /**
     * E://test/com/dove/classloader/ClassDemo.class
     *
     * @param name
     * @return
     */
    private byte[] readClassFileToByteArray(String name) {
        String namepath = name.replaceAll("\\.", "\\\\");
        String calssPath = this.path + namepath + ".class";
        System.out.println(calssPath);
        File classFile = new File(calssPath);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FileInputStream is = null;
        try {
            is = new FileInputStream(classFile);
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return os.toByteArray();
    }
}
