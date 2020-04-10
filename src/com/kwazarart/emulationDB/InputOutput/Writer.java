package com.kwazarart.emulationDB.InputOutput;

import java.io.*;

public class Writer {
    public static boolean write (String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void rewrite(String path, StringBuffer data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
