package com.mijazz.springlearn.objects;

import javax.persistence.*;

@Entity
@Table(name = "tb_assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "astitle")
    private String astitle;
    @Column(name = "asdetail")
    private String asdetail;
    @Column(name = "asdate")
    private String asdate;
    @Column(name = "asdeadline")
    private String asdeadline;
    @Column(name = "asproperty")
    private String asproperty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAstitle() {
        return astitle;
    }

    public void setAstitle(String astitle) {
        this.astitle = astitle;
    }

    public String getAsdetail() {
        return asdetail;
    }

    public void setAsdetail(String asdetail) {
        this.asdetail = asdetail;
    }

    public String getAsdate() {
        return asdate;
    }

    public void setAsdate(String asdate) {
        this.asdate = asdate;
    }

    public String getAsdeadline() {
        return asdeadline;
    }

    public void setAsdeadline(String asdeadline) {
        this.asdeadline = asdeadline;
    }

    public String getAsproperty() {
        return asproperty;
    }

    public void setAsproperty(String asproperty) {
        this.asproperty = asproperty;
    }
}
