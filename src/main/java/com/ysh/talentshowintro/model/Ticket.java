package com.ysh.talentshowintro.model;

import java.sql.Timestamp;

public class Ticket {
    private int id;
    private String owner;
    private String OrderID;
    private String number;
    private Timestamp createDateTime;
    private boolean show;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", OrderID='" + OrderID + '\'' +
                ", number='" + number + '\'' +
                ", createDateTime=" + createDateTime +
                ", show=" + show +
                '}';
    }
}

