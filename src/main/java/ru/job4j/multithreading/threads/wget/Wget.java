package ru.job4j.multithreading.threads.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Wget implements Runnable {
    private final String url;
    private final int speedKbs;
    private final String fileName;

    public Wget(String url, int speedKbs, String fileName) {
        this.url = url;
        this.speedKbs = speedKbs;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            int bufferSize = 1024 * 1024;
            byte[] dataBuffer = new byte[bufferSize];
            int bytesRead;
            Date startBlockTime = new Date();
            Date finishBlockTime;
            while ((bytesRead = in.read(dataBuffer, 0, bufferSize)) != -1) {
                finishBlockTime = new Date();
                long delay = calculateDelay(finishBlockTime.getTime() - startBlockTime.getTime(), bytesRead);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(delay);
                startBlockTime = new Date();
            }
            /* Скачать файл*/
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private long calculateDelay(long time, long bytesRead) {
        long expectedTimeMillis = (long) (bytesRead / (speedKbs / 1000D));
        long result = expectedTimeMillis;
        System.out.println("diff is " + time + "millis");
        System.out.println("expectedTime is " + expectedTimeMillis + "millis");

        if (expectedTimeMillis > time) {
            result = expectedTimeMillis - time;
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}
