package com.javaproject.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentDependencyImpOne implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Primer componente");
    }
}
