package com.example.springbootdemo.controller;

import java.util.ArrayList;
import java.util.List;

public class Practice {

    public static <T> List<T> makeList(T... elements) {
        List<T> list = new ArrayList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }
}
