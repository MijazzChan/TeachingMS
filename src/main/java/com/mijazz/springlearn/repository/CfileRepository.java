package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.objects.Cfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CfileRepository extends JpaRepository<Cfile, Long> {
    Iterable<Cfile> findByCfileproperty(String property);
}
