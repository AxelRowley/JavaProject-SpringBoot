package com.javaproject.springboot.fundamentos.repository;

import com.javaproject.springboot.fundamentos.dto.UserDto;
import com.javaproject.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdAsc(String name);

    List<User> findByEmailLikeOrderByIdDesc(String email);

    List<User> findByEmailContainingOrderByIdAsc(String email);

    @Query( " select new com.javaproject.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate) " +
            " from User u " +
            " where u.birthDate = :parametroFecha " +
            " and u.email = :parametroEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

    List<User> findAll();
}
