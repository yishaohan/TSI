package com.ysh.talentshowintro.model;

public class Contestant {
    private int id;
    private String name;
    private int ballotCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBallotCount() {
        return ballotCount;
    }

    public void setBallotCount(int ballotCount) {
        this.ballotCount = ballotCount;
    }
}

