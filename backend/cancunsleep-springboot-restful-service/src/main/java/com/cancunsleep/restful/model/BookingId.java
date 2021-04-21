package com.cancunsleep.restful.model;

import java.io.Serializable;

public class BookingId implements Serializable {

    private Long idroom;
    private Long idinnkeeper;
    private Long idclient;
    private Long iddate;

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

}
