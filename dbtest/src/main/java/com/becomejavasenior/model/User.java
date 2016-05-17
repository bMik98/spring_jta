package com.becomejavasenior.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class User implements Entity{
    private long id;
    private Byte ftiny;
    private Short fsmall;
    private Long fbig;
    private Double fdouble;
    private Timestamp fdate;
    private Integer fyear;
    private String fchar;
    private String fvchar;
    private BigDecimal fdec;

    public User() {
    }

    public User(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Byte getFtiny() {
        return ftiny;
    }

    public void setFtiny(Byte ftiny) {
        this.ftiny = ftiny;
    }

    public Short getFsmall() {
        return fsmall;
    }

    public void setFsmall(Short fsmall) {
        this.fsmall = fsmall;
    }

    public Long getFbig() {
        return fbig;
    }

    public void setFbig(Long fbig) {
        this.fbig = fbig;
    }

    public Double getFdouble() {
        return fdouble;
    }

    public void setFdouble(Double fdouble) {
        this.fdouble = fdouble;
    }

    public Timestamp getFdate() {
        return fdate;
    }

    public void setFdate(Timestamp fdate) {
        this.fdate = fdate;
    }

    public Integer getFyear() {
        return fyear;
    }

    public void setFyear(Integer fyear) {
        this.fyear = fyear;
    }

    public String getFchar() {
        return fchar;
    }

    public void setFchar(String fchar) {
        this.fchar = fchar;
    }

    public String getFvchar() {
        return fvchar;
    }

    public void setFvchar(String fvchar) {
        this.fvchar = fvchar;
    }

    public BigDecimal getFdec() {
        return fdec;
    }

    public void setFdec(BigDecimal fdec) {
        this.fdec = fdec;
    }
}
