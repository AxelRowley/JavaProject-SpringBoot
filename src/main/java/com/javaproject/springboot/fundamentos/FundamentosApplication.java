package com.javaproject.springboot.fundamentos;

import com.javaproject.springboot.fundamentos.bean.MyBean;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithDependency;
import com.javaproject.springboot.fundamentos.bean.MyBeanWithProperties;
import com.javaproject.springboot.fundamentos.component.ComponentDependency;
import com.javaproject.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency compDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDep;
	@Autowired
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	/*
	Inyeccion de dependecias
	 */
	public FundamentosApplication(@Qualifier("componentDependencyImpTwo") ComponentDependency comDep, MyBean myBean, MyBeanWithDependency myBeanWithDependency/*, MyBeanWithProperties myBeanWithProperties*/,UserPojo userPojo) {
		this.compDependency = comDep;
		this.myBean = myBean;
		this.myBeanWithDep = myBeanWithDependency;
		//this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		compDependency.saludar();
		myBean.imprimir();
		myBeanWithDep.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try {
			//error
			int value = 10 / 0;
			LOGGER.info("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividor por cero " + e.getMessage() + e.getStackTrace());
		}

	}

}
