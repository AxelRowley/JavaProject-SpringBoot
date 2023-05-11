package com.javaproject.springboot.fundamentos.bean;

public class MyBeanImpOne implements MyBean{
    @Override
    public void imprimir() {
        System.out.println("Primer implementacion del bean");
    }
}
