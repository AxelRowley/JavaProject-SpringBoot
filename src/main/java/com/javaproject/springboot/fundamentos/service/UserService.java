package com.javaproject.springboot.fundamentos.service;

import com.javaproject.springboot.fundamentos.entity.User;
import com.javaproject.springboot.fundamentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepositoryDepend;

    public UserService(UserRepository userRepositoryDepend) {
        this.userRepositoryDepend = userRepositoryDepend;
    }

    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: " + user))
                .forEach(userRepositoryDepend::save);
                //.forEach(user -> userRepositoryDepend.save(user));
    }

    public List<User> getAllUsers() {
        return userRepositoryDepend.findAll();
    }
}
