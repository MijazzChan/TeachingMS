package com.mijazz.springlearn.service;

import com.mijazz.springlearn.repository.UserRepository;
import com.mijazz.springlearn.securities.Role;
import com.mijazz.springlearn.securities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    @Resource
    UserRepository userRepository;

    public User findbyloginname(String loginname) {
        return userRepository.findByLoginname(loginname);
    }

    public void savebyloginname(User user) {
        System.out.println(user.getPassword());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        log.warn("User Credential has been updated, Name= " + user.getLoginname());
    }

    @Transactional
    public long count() {
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String loginname) throws UsernameNotFoundException {
        User user = userRepository.findByLoginname(loginname);
        if (user == null) {
            throw new UsernameNotFoundException("Non-Exist User");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        log.info("User Login Via Spring Security, Roles= " + roles.toString() + ", Name= " + loginname);
        return new org.springframework.security.core.userdetails.User(user.getLoginname(), user.getPassword(), authorities);
    }
}
