package com.cancunsleep.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "date")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Date implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddate", unique = true, nullable = false)
    private Long iddate;

    @Column(name = "epochdate")
    private Long epochdate;

    @Column(name = "date", length = 255)
    private String date;

    public Date() {}

    public Date(Long iddate, Long epochdate, String date) {
        this.iddate = iddate;
        this.epochdate = epochdate;
        this.date = date;
    }

    public Long getIddate() {
        return iddate;
    }

    public void setIddate(Long iddate) {
        this.iddate = iddate;
    }

    public Long getEpochdate() {
        return epochdate;
    }

    public void setEpochdate(Long epochdate) {
        this.epochdate = epochdate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date{" +
                "iddate=" + iddate +
                ", epochdate=" + epochdate +
                ", date='" + date + '\'' +
                '}';
    }

}
