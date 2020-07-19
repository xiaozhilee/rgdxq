package com.example.demo.entity;

public class CodeModel {
    private String kkey;
    private String code;

    public CodeModel(String kkey, String code) {
        this.kkey = kkey;
        this.code = code;
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

    public CodeModel() {
    }
}
