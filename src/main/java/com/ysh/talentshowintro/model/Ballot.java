package com.ysh.talentshowintro.model;

import java.sql.Timestamp;

public class Ballot {
    private int id;
    private String voter;
    private String candidate;
    private Timestamp createDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "id=" + id +
                ", voter='" + voter + '\'' +
                ", candidate='" + candidate + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
