package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.objects.Cfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface CfileRepository extends JpaRepository<Cfile, Long> {
    Iterable<Cfile> findByCfileproperty(String property);
}
