package com.cancunsleep.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "room")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room implements java.io.Serializable {

    private static final long serialVersionUID = 4910225916550731446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idroom", unique = true, nullable = false)
    private Long idroom;

    @Column(name = "numberroom")
    private Long numberroom;

    @Column(name = "bedroom")
    private Long bedroom;

    @Column(name = "nameroom", length = 255)
    private String nameroom;

    public Room() {}

    public Room(Long idroom, Long numberroom, Long bedroom, String nameroom) {
        this.idroom = idroom;
        this.numberroom = numberroom;
        this.bedroom = bedroom;
        this.nameroom = nameroom;
    }

    public Long getIdroom() {
        return idroom;
    }

    public void setIdroom(Long idroom) {
        this.idroom = idroom;
    }

    public Long getNumberroom() {
        return numberroom;
    }

    public void setNumberroom(Long numberroom) {
        this.numberroom = numberroom;
    }

    public Long getBedroom() {
        return bedroom;
    }

    public void setBedroom(Long bedroom) {
        this.bedroom = bedroom;
    }

    public String getNameroom() {
        return nameroom;
    }

    public void setNameroom(String nameroom) {
        this.nameroom = nameroom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idroom=" + idroom +
                ", numberroom=" + numberroom +
                ", bedroom=" + bedroom +
                ", nameroom='" + nameroom + '\'' +
                '}';
    }

}
