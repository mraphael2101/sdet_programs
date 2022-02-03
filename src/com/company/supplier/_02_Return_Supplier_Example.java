package com.company.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public class _02_Return_Supplier_Example<T> {

    public static void main(String[] args) {
        _02_Return_Supplier_Example<String> obj = new _02_Return_Supplier_Example();
        List<String> list = obj.supplier().get();

        // This list was initialised via a Supplier that takes no arguments and returns a result
        System.out.println(list.size());
    }

    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<>();

        // Alternatively, use a constructor reference
        // return ArrayList::new;
    }

}
