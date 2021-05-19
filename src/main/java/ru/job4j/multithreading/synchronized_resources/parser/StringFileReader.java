package ru.job4j.multithreading.synchronized_resources.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.function.IntPredicate;

public class StringFileReader {
    private final File file;

    public StringFileReader(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        return readString(symbol -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return readString(symbol -> symbol < 0x80);
    }

    private String readString(IntPredicate predicate) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            char[] buffer = new char[1024];
            StringBuilder builder = new StringBuilder();
            while (fileReader.read(buffer) > 0) {
                CharBuffer.wrap(buffer).chars()
                        .filter(predicate)
                        .forEach(builder::append);
            }
            return builder.toString();
        }
    }
}
