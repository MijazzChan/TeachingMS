package com.mijazz.springlearn.EasyRegister;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCyptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("toor"));
        System.out.println(bCryptPasswordEncoder.encode("resu"));
    }
}
