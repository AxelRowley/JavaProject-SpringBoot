package com.javaproject.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImp implements MyBeanWithDependency {

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImp.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImp(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Insegro al metodo printWithDependency");

        int number = 10;
        LOGGER.debug("El numero pasado como parametro a la dependencia operacion es: " + number);

        System.out.println("Resultado:" + myOperation.sum(number));
        System.out.println("Implementacion Bean con dependencia");
    }
}
