package com.example.roomreservation.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "userlogin")
public class ReservationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reserved_id")
    private int reservedId;
    @Basic
    @Column(name = "Customer_name")
    private String customerName;
    @Basic
    @Column(name = "roomNumbers")
    private String roomNumbers;
    @Basic
    @Column(name = "totalBill")
    private BigDecimal totalBill;
    @Basic
    @Column(name = "Paid_Status")
    private String paidStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity CustomerId;

    public int getReservedId() {
        return reservedId;
    }

    public void setReservedId(int reservedId) {
        this.reservedId = reservedId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return reservedId == that.reservedId && Objects.equals(customerName, that.customerName) && Objects.equals(roomNumbers, that.roomNumbers) && Objects.equals(totalBill, that.totalBill) && Objects.equals(paidStatus, that.paidStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservedId, customerName, roomNumbers, totalBill, paidStatus);
    }

    public UserEntity getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(UserEntity customerId) {
        CustomerId = customerId;
    }
}
