package com.kwazarart.emulationdb.inputoutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader {
    private static ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public static List<String> read(String path) {
        Lock readLock = rwLock.readLock();
        readLock.lock();
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i++ == 0) {
                    continue;
                }
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return list;
    }
}
