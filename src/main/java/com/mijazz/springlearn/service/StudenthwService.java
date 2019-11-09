package com.mijazz.springlearn.service;

import com.mijazz.springlearn.objects.Studenthw;
import com.mijazz.springlearn.repository.StudenthwRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class StudenthwService {
    @Resource
    StudenthwRepository studenthwRepository;

    @Transactional
    public void save(Studenthw studenthw){
        studenthwRepository.save(studenthw);
    }

    @Transactional
    public Iterable<Studenthw> getall(){
        return studenthwRepository.findAll();
    }

    @Transactional
    public Iterable<Studenthw> getbyloginname(String loginname){
        return studenthwRepository.findByHwowner(loginname);
    }

    @Transactional
    public Iterable<Studenthw> getbypropertyid(long id){
        return studenthwRepository.findByHwpropertyOrderByHwsubmitdateDesc(id);
    }

    @Transactional
    public Iterable<Studenthw> getbyproperty(String property){
        return studenthwRepository.findByHwpropertynameOrderByHwsubmitdateDesc(property);
    }

}
