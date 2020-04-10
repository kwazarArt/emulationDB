package com.kwazarart.emulationDB.model;

public class Specialty {

    private int id;
    private String name;
    private Status status;

    public Specialty(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id +"," + name + "," + status;
    }
}
