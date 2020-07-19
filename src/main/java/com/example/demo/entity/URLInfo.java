package com.example.demo.entity;



import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class URLInfo implements Serializable {
   @Id
   private String url;

   private String kkey;//状态关键字

   private String code;//正确的状态码


   private String mail;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date ddate;

    @Column(columnDefinition = "int default 0")
    private Integer errorCount;


    public Integer getErrorCount() {
        return errorCount;
    }

    public String getKkey() {
        return kkey;
    }

    public void setKkey(String kkey) {
        this.kkey = kkey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public URLInfo() {
    }
}
