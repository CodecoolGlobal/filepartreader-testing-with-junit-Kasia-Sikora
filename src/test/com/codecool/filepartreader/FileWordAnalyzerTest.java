package com.codecool.filepartreader;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
class FileWordAnalyzerTest {

    private FilePartReader filePartReader = new FilePartReader();
    private FileWordAnalyzer analyzer = new FileWordAnalyzer(filePartReader);


    @Test
    void testGetWordsOrderedAlphabetically(){
        filePartReader.setup("src/resources/short.txt", 1, 1);
        assertEquals(Arrays.asList("a", "a", "army.", "good", "great", "had", "He", "king.", "Robert", "was"), analyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testGetWordsOrderedAlphabetically_Fail(){
        filePartReader.setup("src/resources/short.txt", 1, 1);
        Assert.assertNotSame(Arrays.asList("a", "a", "king.", "Robert", "was"), analyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testGetWordsContainingSubstring(){
        filePartReader.setup("src/resources/test.txt", 2, 3);
        assertEquals(Arrays.asList("plotting", "plots"), analyzer.getWordsContainingSubstring("plo"));
    }

    @Test
    void testGetWordsContainingEmptySubstring(){
        filePartReader.setup("src/resources/short.txt", 1, 1);
        assertEquals(Arrays.asList("Robert", "was", "a", "good", "king.", "He", "had", "a", "great", "army."), analyzer.getWordsContainingSubstring(""));
    }

    @Test
    void testGetWordsContainingNullSubstring(){
        filePartReader.setup("src/resources/short.txt", 1, 1);
        Assertions.assertThrows(NullPointerException.class, () -> analyzer.getWordsContainingSubstring(null));
    }

    @Test
    void testGetWordsContainingWhitespace(){
        filePartReader.setup("src/resources/short.txt", 1, 1);
        assertEquals(new ArrayList(), analyzer.getWordsContainingSubstring(" "));
    }

    @Test
    void testGetStringsWhichPalindromes(){
        try {
            filePartReader.setup("src/resources/test.txt", 1, 2);
            System.out.println(filePartReader.read());
            assertEquals(Arrays.asList("a", "a"), analyzer.getStringsWhichPalindromes());
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    void testGetStringsWhichPalindromesRandomText(){
            assertEquals(Arrays.asList("did", "ala"), analyzer.getStringsWhichPalindromes("bla did ala dupa"));
    }
}