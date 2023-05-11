package com.javaproject.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentDependencyImpTwo implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Segundo componente");
    }
}
