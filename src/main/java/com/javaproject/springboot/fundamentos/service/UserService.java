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
    private UserRepository userRepositoryDep;

    public UserService(UserRepository userRepositoryDepend) {
        this.userRepositoryDep = userRepositoryDepend;
    }

    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: " + user))
                .forEach(userRepositoryDep::save);
                //.forEach(user -> userRepositoryDepend.save(user));
    }

    public List<User> getAllUsers() {
        return userRepositoryDep.findAll();
    }

    public User save(User newUser) {
        return userRepositoryDep.save(newUser);
    }

    public void delete(Long id) {
        userRepositoryDep.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepositoryDep.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepositoryDep.save(user);
                        }
                ).orElse(null);
    }
}
