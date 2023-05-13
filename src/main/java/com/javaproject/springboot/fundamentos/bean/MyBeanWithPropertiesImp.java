package com.javaproject.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImp implements MyBeanWithProperties {

    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImp (String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre + "-" + apellido;
    }
}
