package com.javaproject.springboot.fundamentos.bean;

public class MyBeanWithDependencyImp implements MyBeanWithDependency {

    private MyOperation myOperation;

    public MyBeanWithDependencyImp(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        System.out.println("Implementacion Bean con dependencia");
        int number = 10;
        System.out.println("Resultado:" + myOperation.sum(number));
    }
}
