package com.mijazz.springlearn.objects;

import javax.persistence.*;

@Entity
@Table(name = "tb_cfile")
public class Cfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cfileid;

    @Column(name = "cfilename")
    private String cfilename;

    @Column(name = "cfilepath")
    private String cfilepath;

    @Column(name = "cfileproperty")
    private String cfileproperty;

    public Cfile(String cfilename, String cfileproperty, String cfilepath) {
        this.cfilename = cfilename;
        this.cfileproperty = cfileproperty;
        this.cfilepath = cfilepath;
    }

    public Cfile() {

    }

    public long getCfileid() {
        return cfileid;
    }

    public void setCfileid(long cfileid) {
        this.cfileid = cfileid;
    }

    public String getCfilename() {
        return cfilename;
    }

    public void setCfilename(String cfilename) {
        this.cfilename = cfilename;
    }

    public String getCfilepath() {
        return cfilepath;
    }

    public void setCfilepath(String cfilepath) {
        this.cfilepath = cfilepath;
    }

    public String getCfileproperty() {
        return cfileproperty;
    }

    public void setCfileproperty(String cfileproperty) {
        this.cfileproperty = cfileproperty;
    }
}
