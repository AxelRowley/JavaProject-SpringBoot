package com.javaproject.springboot.fundamentos.configuration;

import com.javaproject.springboot.fundamentos.caseuse.GetUser;
import com.javaproject.springboot.fundamentos.caseuse.GetUserImp;
import com.javaproject.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    public GetUser getUser(UserService userService) {
        return new GetUserImp(userService);
    }
}
