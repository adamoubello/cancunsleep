package com.cancunsleep.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "client")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idclient", unique = true, nullable = false)
    private Long idclient;

    @Column(name = "nameclient", length = 255)
    private String nameclient;

    @Column(name = "surnameclient", length = 255)
    private String surnameclient;

    @Column(name = "genderclient", length = 2)
    private String genderclient;

    @Column(name = "birthdayclient", length = 255)
    private String birthdayclient;

    public Client() {}

    public Client(Long idclient, String nameclient, String surnameclient, String genderclient, String birthdayclient) {
        this.idclient = idclient;
        this.nameclient = nameclient;
        this.surnameclient = surnameclient;
        this.genderclient = genderclient;
        this.birthdayclient = birthdayclient;
    }

    public Long getIdclient() {
        return idclient;
    }

    public void setIdclient(Long idclient) {
        this.idclient = idclient;
    }

    public String getNameclient() {
        return nameclient;
    }

    public void setNameclient(String nameclient) {
        this.nameclient = nameclient;
    }

    public String getSurnameclient() {
        return surnameclient;
    }

    public void setSurnameclient(String surnameclient) {
        this.surnameclient = surnameclient;
    }

    public String getGenderclient() {
        return genderclient;
    }

    public void setGenderclient(String genderclient) {
        this.genderclient = genderclient;
    }

    public String getBirthdayclient() {
        return birthdayclient;
    }

    public void setBirthdayclient(String birthdayclient) {
        this.birthdayclient = birthdayclient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idclient=" + idclient +
                ", nameclient='" + nameclient + '\'' +
                ", surnameclient='" + surnameclient + '\'' +
                ", genderclient='" + genderclient + '\'' +
                ", birthdayclient='" + birthdayclient + '\'' +
                '}';
    }

}
