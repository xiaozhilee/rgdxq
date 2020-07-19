package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Expense implements Serializable {
    @Id
    @GeneratedValue
    private long serId;

    private String url;

    private String expense;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @DateTimeFormat(style = "YYYY-MM-DD hh-mm-ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date ddate;

    private Integer ttype;//1代表正常，0代表异常

    private String message;//0异常情况 具体信息 ，正常情况不填



    private String device;

    public Integer getType() {
        return ttype;
    }

    public void setType(Integer type) {
        this.ttype = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSerId() {
        return serId;
    }

    public void setSerId(long serId) {
        this.serId = serId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date date) {
        this.ddate = date;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "serId=" + serId +
                ", url='" + url + '\'' +
                ", expense='" + expense + '\'' +
                ", date=" + ddate +
                ", type=" + ttype +
                ", message='" + message + '\'' +
                ", device='" + device + '\'' +
                '}';
    }
}
