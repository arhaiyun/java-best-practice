package com.exodus.fundamentals;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/4 10:09
 * <p>
 * https://www.zhihu.com/question/45022217
 * <p>
 * 如何打破双亲委派机制，步骤如下：
 * （1）继承ClassLoader
 * （2）重写findClass()方法
 * （3）调用defineClass()方法
 */
public class MyClassLoader extends ClassLoader {
    private String classpath;

    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classDate = getClassBinaryData(name);
            if (classDate == null) {
            } else { // defineClass方法将字节码转化为类
                return defineClass(name, classDate, 0, classDate.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassBinaryData(String className) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException { // 自定义类加载器的加载路径
        System.out.println(ClassLoader.getSystemClassLoader()); // jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
        System.out.println(ClassLoader.getSystemClassLoader().getParent()); // jdk.internal.loader.ClassLoaders$PlatformClassLoader@36f6e879
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent()); // null - bootstrap classloader 为c语言编写

        MyClassLoader myClassLoader = new MyClassLoader("D:\\lib"); // 包名+类名
        System.out.println(myClassLoader.getParent());
//        Class c = myClassLoader.loadClass("com.test.Test");
//        if (c != null) {
//            Object obj = c.newInstance();
//            Method method = c.getMethod("say", null);
//            method.invoke(obj, null);
//            System.out.println(c.getClassLoader().toString());
//        }
    }
}
