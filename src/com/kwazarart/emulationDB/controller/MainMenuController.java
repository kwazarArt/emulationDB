package com.kwazarart.emulationDB.controller;

import com.kwazarart.emulationDB.viewer.MainMenu;

public class MainMenuController {
    Controller controllerSkill = new SkillController();
    Controller controllerSpecialty = new SpecialtyController();
    Controller controllerDeveloper = new DeveloperController();
    //сделать под фабрику (добавить шаблон)

    public void controlMainMenu() {
        while (true) {
            int select = MainMenu.viewMainMenu();
            switch (select) {
                case 1:
                    controllerDeveloper.controlMenu();
                    break;
                case 2:
                    controllerSkill.controlMenu();
                    break;
                case 3:
                    controllerSpecialty.controlMenu();
                    break;
                case 0:
                    return;
            }
        }
    }
}
