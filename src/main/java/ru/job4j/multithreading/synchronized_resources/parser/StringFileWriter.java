package ru.job4j.multithreading.synchronized_resources.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringFileWriter {
    private final File file;

    public StringFileWriter(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
