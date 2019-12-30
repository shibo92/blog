package com.shibo.annotation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * @author shibo
 * @date 19-12-11 下午9:21
 */
public class HandleUserType {
    public static void getUserType() {
        String packageName = "com.shibo.handler";
        String packageDirName = packageName.replace('.', '/');
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if ("file".equals(url.getProtocol())) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String filePath) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(file -> (file.isDirectory()) || (file.getName().endsWith(".class")));
        /*
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        */
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                // 如果是目录 则继续扫描
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath());
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                System.out.println(getAnnotationDesc(packageName, className));
            }
        }

    }

    private static String getAnnotationDesc(String packageName, String className) {
        // 这里用class.forName会触发static方法，改用loadClass
        //Class.forName(packageName + '.' +className)
        Class<?> clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
            // 获取@User注解
            User userAnnotation = clazz.getAnnotation(User.class);
            // 做测试，只获取注解数组中第一个值
            return userAnnotation.userType()[0].getTypeName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
