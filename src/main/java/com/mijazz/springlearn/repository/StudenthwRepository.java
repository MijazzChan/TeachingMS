package com.mijazz.springlearn.repository;

import com.mijazz.springlearn.objects.Studenthw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudenthwRepository extends JpaRepository<Studenthw, Long> {
    List<Studenthw> findByHwowner(String loginname);

    List<Studenthw> findByHwproperty(long propertyid);

    List<Studenthw> findByHwpropertyname(String propertyname);

    List<Studenthw> findByHwpropertyOrderByHwsubmitdateDesc(long id);

    List<Studenthw> findByHwpropertynameOrderByHwsubmitdateDesc(String propertyname);
}
