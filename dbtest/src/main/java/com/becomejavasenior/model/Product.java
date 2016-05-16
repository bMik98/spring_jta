package com.becomejavasenior.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private long id;
    private byte ftiny;
    private short fsmall;
    private long fbig;
    private double fdouble;
    private Timestamp fdate;
    private int fyear;
    private String fchar;
    private String fvchar;
    private BigDecimal fdec;

    public Product() {
    }

    public Product(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getFtiny() {
        return ftiny;
    }

    public void setFtiny(byte ftiny) {
        this.ftiny = ftiny;
    }

    public short getFsmall() {
        return fsmall;
    }

    public void setFsmall(short fsmall) {
        this.fsmall = fsmall;
    }

    public long getFbig() {
        return fbig;
    }

    public void setFbig(long fbig) {
        this.fbig = fbig;
    }

    public double getFdouble() {
        return fdouble;
    }

    public void setFdouble(double fdouble) {
        this.fdouble = fdouble;
    }

    public Timestamp getFdate() {
        return fdate;
    }

    public void setFdate(Timestamp fdate) {
        this.fdate = fdate;
    }

    public int getFyear() {
        return fyear;
    }

    public void setFyear(int fyear) {
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
