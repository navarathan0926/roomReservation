package com.example.roomreservation.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rooms", schema = "userlogin")
public class RoomsEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roomNo")
    private int roomNo;
    @Basic
    @Column(name = "acStatus")
    private String acStatus;
    @Basic
    @Column(name = "roomType")
    private String roomType;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "availability")
    private String availability;

    @Override
    public String toString() {
        return String.valueOf(roomNo);
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomsEntity that = (RoomsEntity) o;
        return roomNo == that.roomNo && Objects.equals(acStatus, that.acStatus) && Objects.equals(roomType, that.roomType) && Objects.equals(price, that.price) && Objects.equals(availability, that.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, acStatus, roomType, price, availability);
    }
}
