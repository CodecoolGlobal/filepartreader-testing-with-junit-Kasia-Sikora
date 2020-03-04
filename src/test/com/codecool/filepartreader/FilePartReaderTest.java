package com.codecool.filepartreader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


@RunWith(Parameterized.class)
class FilePartReaderTest {

    private FilePartReader reader = new FilePartReader();


    @Test
    void testSetup_IfFromLineIsBiggerThanToLine_ExceptionThrown() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> reader.setup("src/resources/test.txt", 5, 3));
    }


    @Test
    void testSetup_IfFromLineIsLowerThanToLine_TestPass() {

        Assertions.assertDoesNotThrow( () -> reader.setup("src/resources/test.txt", 3, 5));
    }


    @Test
    void testRead_Assertion_short_ThrowIOException(){
        reader.setup("", 1, 1);
        Assertions.assertThrows(IOException.class, () -> reader.read());
    }

    @Test
    void testRead_Assertion_short(){
        try {
            reader.setup("src/resources/short.txt", 1, 1);
            assertEquals("Robert was a good king. He had a great army.", reader.read());
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @Test
    void testReadLines(){
        reader.setup("src/resources/test.txt", 3, 4);
        assertEquals("friends of the king. Then they started killing those trusted friends. " +
                "Eventually, they succeeded in their plan of killing the king. Did they make a good " +
                "move? Can they find a new king without dispute? After the death ", reader.readLines());
    }

    @Test
    void testReadLinesTestFail(){
        reader.setup("src/resources/test.txt", 3, 4);
        assertNotSame("friends of the king. Then they started killing those trusted friends. " +
                "Eventually, they move? Can they find a new king without dispute? After the death ", reader.readLines());
    }

    @Test
    void testReadLines_Iliad(){
        reader.setup("src/resources/Iliad.txt", 16, 16);
        assertEquals("pestilence upon the host to plague the people, because the son of ", reader.readLines());
    }

}