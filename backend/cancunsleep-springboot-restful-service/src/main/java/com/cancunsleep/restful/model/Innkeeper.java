package com.cancunsleep.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "innkeeper")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Innkeeper implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idinnkeeper", unique = true, nullable = false)
    private Long idinnkeeper;

    @Column(name = "nameinnkeeper", length = 255)
    private String nameinnkeeper;

    @Column(name = "surnameinnkeeper", length = 255)
    private String surnameinnkeeper;

    @Column(name = "roleinnkeeper", length = 255)
    private String roleinnkeeper;

    public Innkeeper() {}

    public Innkeeper(Long idinnkeeper, String nameinnkeeper, String surnameinnkeeper, String roleinnkeeper) {
        this.idinnkeeper = idinnkeeper;
        this.nameinnkeeper = nameinnkeeper;
        this.surnameinnkeeper = surnameinnkeeper;
        this.roleinnkeeper = roleinnkeeper;
    }

    public Long getIdinnkeeper() {
        return idinnkeeper;
    }

    public void setIdinnkeeper(Long idinnkeeper) {
        this.idinnkeeper = idinnkeeper;
    }

    public String getNameinnkeeper() {
        return nameinnkeeper;
    }

    public void setNameinnkeeper(String nameinnkeeper) {
        this.nameinnkeeper = nameinnkeeper;
    }

    public String getSurnameinnkeeper() {
        return surnameinnkeeper;
    }

    public void setSurnameinnkeeper(String surnameinnkeeper) {
        this.surnameinnkeeper = surnameinnkeeper;
    }

    public String getRoleinnkeeper() {
        return roleinnkeeper;
    }

    public void setRoleinnkeeper(String roleinnkeeper) {
        this.roleinnkeeper = roleinnkeeper;
    }

    @Override
    public String toString() {
        return "Innkeeper{" +
                "idinnkeeper=" + idinnkeeper +
                ", nameinnkeeper='" + nameinnkeeper + '\'' +
                ", surnameinnkeeper='" + surnameinnkeeper + '\'' +
                ", roleinnkeeper='" + roleinnkeeper + '\'' +
                '}';
    }

}
