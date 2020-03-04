package com.codecool.filepartreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FileWordAnalyzer {

    private FilePartReader filePartReader;

    FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }


    List getWordsOrderedAlphabetically(){

        List<String> wordsOrderedList = Arrays.asList(filePartReader.readLines().split(" "));
        wordsOrderedList.sort(String.CASE_INSENSITIVE_ORDER);

        return wordsOrderedList;
    }

    List getWordsContainingSubstring(String subString) {

        String text = filePartReader.readLines();
        String[] textList = text.split(" ");

        List<String> result = new ArrayList<>();
        for (String word : textList) {
            if (word != null) {
                if (word.contains(subString)) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    List getStringsWhichPalindromes() {

        String text = filePartReader.readLines();
        String[] textList = text.split("\\s+");
        List<String> result = new ArrayList<>();

        for (String word : textList) {
            if (word.contentEquals(new StringBuilder(word).reverse())) {
                result.add(word);
            }
        }
        return result;
    }


    List getStringsWhichPalindromes(String randomText) {

        String[] textList = randomText.split("\\s+");
        List<String> result = new ArrayList<>();

        for (String word : textList) {
            if (word.contentEquals(new StringBuilder(word).reverse())) {
                result.add(word);
            }
        }
        return result;
    }
}
