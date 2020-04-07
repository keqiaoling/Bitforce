package com.example.myapplication.utils;

/**
 * 用来给ListUtils做函数钩子的接口。
 * @author zhangchao
 *
 */
public interface ListUtilsHook<T> {
    public boolean test(T t);
}