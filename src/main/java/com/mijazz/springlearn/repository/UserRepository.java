package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.securities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginname(String loginname);
}
