package com.cancunsleep.restful.model;

import java.io.Serializable;

public class BookingDto implements Serializable {

    private Long idroom;
    private Long idinnkeeper;
    private Long idclient;
    private Long iddate;
    private boolean available;
    private String staystart;
    private String staystop;
    private Long duration;
    private boolean busied;
    private String comment;

    public Long getIdroom() {
        return idroom;
    }

    public void setIdroom(Long idroom) {
        this.idroom = idroom;
    }

    public Long getIdinnkeeper() {
        return idinnkeeper;
    }

    public void setIdinnkeeper(Long idinnkeeper) {
        this.idinnkeeper = idinnkeeper;
    }

    public Long getIdclient() {
        return idclient;
    }

    public void setIdclient(Long idclient) {
        this.idclient = idclient;
    }

    public Long getIddate() {
        return iddate;
    }

    public void setIddate(Long iddate) {
        this.iddate = iddate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStaystart() {
        return staystart;
    }

    public void setStaystart(String staystart) {
        this.staystart = staystart;
    }

    public String getStaystop() {
        return staystop;
    }

    public void setStaystop(String staystop) {
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

}
