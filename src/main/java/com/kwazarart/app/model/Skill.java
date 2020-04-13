package com.kwazarart.app.model;

import java.util.Objects;


public class Skill {
    private int id;
    private String name;
    private Status status;

    public Skill(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return id +"," + name + "," + status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id &&
                Objects.equals(name, skill.name) &&
                status == skill.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}
