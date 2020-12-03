package com.ysh.talentshowintro.model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private Timestamp createDateTime;
    private String subject;
    private String message = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", createDateTime=" + createDateTime +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
