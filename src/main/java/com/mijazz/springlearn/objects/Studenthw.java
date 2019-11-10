package com.mijazz.springlearn.objects;

import javax.persistence.*;

@Entity
@Table(name = "tb_studenthw")
public class Studenthw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "hwpath")
    private String hwpath;
    @Column(name = "hwproperty")
    private long hwproperty;
    @Column(name = "hwpropertyname")
    private String hwpropertyname;
    @Column(name = "hwsubmitdate")
    private String hwsubmitdate;
    @Column(name = "hwowner")
    private String hwowner;

    public Studenthw() {

    }

    public Studenthw(String hwpath, String hwowner, String hwsubmitdate, long hwproperty) {
        this.hwpath = hwpath;
        this.hwowner = hwowner;
        this.hwsubmitdate = hwsubmitdate;
        this.hwproperty = hwproperty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHwpath() {
        return hwpath;
    }

    public void setHwpath(String hwpath) {
        this.hwpath = hwpath;
    }

    public long getHwproperty() {
        return hwproperty;
    }

    public void setHwproperty(long hwproperty) {
        this.hwproperty = hwproperty;
    }

    public String getHwpropertyname() {
        return hwpropertyname;
    }

    public void setHwpropertyname(String hwpropertyname) {
        this.hwpropertyname = hwpropertyname;
    }

    public String getHwsubmitdate() {
        return hwsubmitdate;
    }

    public void setHwsubmitdate(String hwsubmitdate) {
        this.hwsubmitdate = hwsubmitdate;
    }

    public String getHwowner() {
        return hwowner;
    }

    public void setHwowner(String hwowner) {
        this.hwowner = hwowner;
    }
}
