package com.shibo.Exception;

public class BlogNotFoundException extends Exception {
    //无参构造方法
    public BlogNotFoundException(Integer id) {
        super("blog not found! id-" + id);
    }
}
