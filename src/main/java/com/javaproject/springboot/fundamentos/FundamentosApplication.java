package com.javaproject.springboot.fundamentos;

import com.javaproject.springboot.fundamentos.bean.MyBean;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithDependency;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithProperties;
import com.javaproject.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency compDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDep;
	@Autowired
	private MyBeanWithProperties myBeanWithProperties;

	/*
	Inyeccion de dependecias
	 */
	public FundamentosApplication(@Qualifier("componentDependencyImpTwo") ComponentDependency comDep, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties) {
		this.compDependency = comDep;
		this.myBean = myBean;
		this.myBeanWithDep = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		compDependency.saludar();
		myBean.imprimir();
		myBeanWithDep.printWithDependency();
		System.out.println(myBeanWithProperties.function());;
	}
}
