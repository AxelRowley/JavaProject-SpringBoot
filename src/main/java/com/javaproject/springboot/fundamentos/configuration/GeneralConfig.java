package com.javaproject.springboot.fundamentos.configuration;

import com.javaproject.springboot.fundamentos.bean.MyBeanWithProperties;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithPropertiesImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Value("${value.nombre}")
    private String nombre;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties beanFunction() {
        return new MyBeanWithPropertiesImp(nombre, apellido);
    }
}
