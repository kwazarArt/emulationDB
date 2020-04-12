package com.kwazarart.emulationdb.inputoutput;

import com.kwazarart.emulationdb.model.Developer;
import com.kwazarart.emulationdb.model.Skill;
import com.kwazarart.emulationdb.model.Specialty;
import com.kwazarart.emulationdb.repository.DeveloperRepository;
import com.kwazarart.emulationdb.repository.SkillRepository;
import com.kwazarart.emulationdb.repository.SpecialtyRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {
    public static boolean isDigit (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Skill> returnListSkill(List<String> listString) {
        List<Skill> listSkill = new ArrayList<>();
        SkillRepository skillRepository = new SkillRepository();
        List<Skill> currentListSkill = skillRepository.getList();
        for(Skill skill : currentListSkill) {
            if (listString.contains(skill.getName())) {
                listSkill.add(skill);
            }
        }
        return listSkill;
    }

    public static Specialty returnSpecialty(String element) {
        SpecialtyRepository specialtyRepository = new SpecialtyRepository();
        List<Specialty> list = specialtyRepository.getListSpecialty();
        for (Specialty specialty : list) {
            if (specialty.getName().equals(element)) {
                return specialty;
            }
        }
        return null;
    }

    public static boolean containsSpecialty(Specialty specialty) {
        SpecialtyRepository specialtyRepository = new SpecialtyRepository();
        List<Specialty> list = specialtyRepository.getListSpecialty();
        for (Specialty specialtyCurrent : list) {
            if (specialtyCurrent.getName().equalsIgnoreCase(specialty.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSkill(Skill skill) {
        SkillRepository skillRepository = new SkillRepository();
        List<Skill> list = skillRepository.getList();
        for (Skill skillCurrent : list) {
            if (skillCurrent.getName().equalsIgnoreCase(skill.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDeveloper(Developer developer) {
        DeveloperRepository developerRepository = new DeveloperRepository();
        List<Developer> list = developerRepository.getList();
        for (Developer developerCurrent : list) {
            if (developerCurrent.getFirstName().equalsIgnoreCase(developer.getFirstName()) &&
            developerCurrent.getLastName().equalsIgnoreCase(developer.getLastName()) &&
            developerCurrent.getSpecialty().equals(developer.getSpecialty()) &&
            developerCurrent.getSkills().equals(developer.getSkills())) {
                return true;
            }
        }
        return false;
    }


}
