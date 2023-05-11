package com.javaproject.springboot.fundamentos.bean;

public class MyOperationImp implements MyOperation {
    @Override
    public int sum(int number) {
        return number + 1;
    }
}
