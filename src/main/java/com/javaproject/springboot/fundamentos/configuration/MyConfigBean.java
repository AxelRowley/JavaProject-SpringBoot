package com.javaproject.springboot.fundamentos.configuration;

import com.javaproject.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigBean {
    @Bean
    public MyBean beanOperationOne() {
        return new MyBeanImpTwo();
    }
    @Bean
    public MyOperation beanOperationTwo() {
        return new MyOperationImp();
    }
    @Bean
    public MyBeanWithDependency beanOperationTree(MyOperation myOperation) {
        return new MyBeanWithDependencyImp(myOperation);
    }
}
