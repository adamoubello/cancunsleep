package com.cancunsleep.restful.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@IdClass(BookingId.class)
public class Booking implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "idroom_idroom", referencedColumnName = "idroom")
    private Room idroom;

    @Id
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "idinnkeeper_idinnkeeper", referencedColumnName = "idinnkeeper")
    private Innkeeper idinnkeeper;

    @Id
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "idclient_idclient", referencedColumnName = "idclient")
    private Client idclient;

    @Id
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "iddate_iddate", referencedColumnName = "iddate")
    private Date iddate;

    @Column(name = "available")
    @Field(termVector = TermVector.YES)
    private boolean available;

    @Column(name = "staystart")
    @Field(termVector = TermVector.YES)
    private Long staystart;

    @Column(name = "staystop")
    @Field(termVector = TermVector.YES)
    private Long staystop;

    @Column(name = "duration")
    @Field(termVector = TermVector.YES)
    private Long duration;

    @Column(name = "busied")
    @Field(termVector = TermVector.YES)
    private boolean busied;

    @Column(name = "comment", length = 255)
    @Field(termVector = TermVector.YES)
    private String comment;

    public Booking() {}

    public Booking(Room idroom, Innkeeper idinnkeeper, Client idclient, Date iddate
            , boolean available, Long staystart, Long staystop, Long duration, boolean busied, String comment) {
        this.idroom = idroom;
        this.idinnkeeper = idinnkeeper;
        this.idclient = idclient;
        this.iddate = iddate;
        this.available = available;
        this.staystart = staystart;
        this.staystop = staystop;
        this.duration = duration;
        this.busied = busied;
        this.comment = comment;
    }

    public Room getIdroom() {
        return idroom;
    }

    public void setIdroom(Room idroom) {
        this.idroom = idroom;
    }

    public Innkeeper getIdinnkeeper() {
        return idinnkeeper;
    }

    public void setIdinnkeeper(Innkeeper idinnkeeper) {
        this.idinnkeeper = idinnkeeper;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public Date getIddate() {
        return iddate;
    }

    public void setIddate(Date iddate) {
        this.iddate = iddate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Long getStaystop() {
        return staystop;
    }

    public void setStaystop(Long staystop) {
        this.staystop = staystop;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public boolean isBusied() {
        return busied;
    }

    public void setBusied(boolean busied) {
        this.busied = busied;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getStaystart() {
        return staystart;
    }

    public void setStaystart(Long staystart) {
        this.staystart = staystart;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "idroom=" + idroom +
                ", idinnkeeper=" + idinnkeeper +
                ", idclient=" + idclient +
                ", iddate=" + iddate +
                ", available=" + available +
                ", staystart=" + staystart +
                ", staystop=" + staystop +
                ", duration=" + duration +
                ", busied=" + busied +
                ", comment='" + comment + '\'' +
                '}';
    }

}
