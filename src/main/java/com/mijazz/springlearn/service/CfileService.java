package com.mijazz.springlearn.service;

import com.mijazz.springlearn.objects.Cfile;
import com.mijazz.springlearn.repository.CfileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CfileService {
    @Resource
    private CfileRepository cfileRepository;

    @Transactional
    public void save(Cfile cfile) {
        cfileRepository.save(cfile);
    }

    @Transactional
    public void saveall(List<Cfile> cfiles) {
        cfileRepository.saveAll(cfiles);
    }

    @Transactional
    public Iterable<Cfile> getall() {
        return cfileRepository.findAll();
    }

    @Transactional
    public Iterable<Cfile> getbyproperty(String property) {
        return cfileRepository.findByCfileproperty(property);
    }
}
