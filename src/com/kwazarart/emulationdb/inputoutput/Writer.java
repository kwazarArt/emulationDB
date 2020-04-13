package com.kwazarart.emulationdb.inputoutput;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Writer{
    private static ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public static boolean write (String path, String data) {
        Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return true;
    }

    public static void rewrite(String path, StringBuffer data) {
        Lock writeLock = rwLock.writeLock();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
