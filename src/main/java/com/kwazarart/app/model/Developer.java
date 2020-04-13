package com.kwazarart.app.model;

import java.util.List;

public class Developer {
    private int id;
    private String firstName;
    private String lastName;
    private Specialty specialty;
    private List<Skill> skills;
    private Status status;

    public Developer(int id, String firstName, String lastName, Specialty specialty, List<Skill> skills, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.skills = skills;
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "," + firstName  +
                "," + lastName +
                "," + specialty.getName() +
                "," + listSkillToString(skills)  + status;
    }

    public String listSkillToString(List<Skill> list) {
        StringBuffer line = new StringBuffer("");
        for (Skill skill : list) {
            line.append(skill.getName());
            line.append(",");
        }
        return line.toString();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
