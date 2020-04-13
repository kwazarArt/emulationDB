package com.kwazarart.app.repository;

import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public interface Repository<E> {
    Logger log = Logger.getLogger(DeveloperRepository.class.getName());

    E add();

    E get(int x);

    List<E> getAll();

    void updater(int x);

    void delete();

    default void printByIndex(E e) {
        System.out.println(e);
    }

    default void printAll(List<E> list) {
        for (E e : list) {
            System.out.println(e);
        }
        System.out.println("\n");
    }

    default void notFound() {
        System.out.println("Object doesn't found");
    }

    default void logging(String str) {

        Date date = new Date();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            FileHandler fh = new FileHandler("C:\\Users\\user\\Desktop\\emulationdb\\src\\main\\java\\com\\kwazarart\\app\\resources\\log_test.txt");
            fh.setFormatter(formatter);
            log.addHandler(fh);
            log.info(date.toString() + " " + this.getClass().getName() + " called " + str + "() method");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
