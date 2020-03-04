package com.codecool.filepartreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FilePartReader {

    private String filePath;
    private int fromLine;
    private int toLine;

    FilePartReader() {
        this.fromLine = -1;
        this.toLine = 100;
        this.filePath = "";
    }

    void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException();
        } else {
            this.fromLine = fromLine;
            this.toLine = toLine;
            this.filePath = filePath;
        }
    }


    public String read() throws IOException {
        return Files.readString(Path.of(filePath));
    }


    String readLines() {

        try {
            String fileText = read();
            List<String> lineOfText = Arrays.asList(fileText.split("\\n"));

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < lineOfText.size(); i++) {
                if ((i >= fromLine - 1) && (i <= toLine - 1)) {
                    result.append(lineOfText.get(i)).append(" ");
                }
            }
            return result.toString();

        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
