package com.kwazarart.emulationDB.controller;

import com.kwazarart.emulationDB.viewer.InnerMenu;

public interface Controller {
    default void controlMenu() {
        while (true) {
            int variant = InnerMenu.viewInnerMenu();
            switch (variant) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    readAll();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 0:
                    return;
            }
        }
    }

    void create();

    void read();

    void readAll();

    void update();

    void delete();
}
