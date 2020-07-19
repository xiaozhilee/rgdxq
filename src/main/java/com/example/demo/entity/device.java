package com.example.demo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
public class device {
    @Id
    @GeneratedValue
    private int did;
    private String url;
    private String adevice;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date devicedate;

    public Date getDevicedate() {
        return devicedate;
    }

    public void setDevicedate(Date devicedate) {
        this.devicedate = devicedate;
    }

    public device(int did, String url, String adevice) {
        this.did = did;
        this.url = url;
        this.adevice = adevice;
    }

    public device() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdevice() {
        return adevice;
    }

    public void setAdevice(String adevice) {
        this.adevice = adevice;
    }

    public device(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "device{" +
                "url='" + url + '\'' +
                ", adevice='" + adevice + '\'' +
                '}';
    }
}
