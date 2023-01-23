package com.alexOssin;

import org.junit.jupiter.api.Test;
import java.util.List;

import static com.alexOssin.NetworkUnitType.*;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    private final ClassLoader classLoader = getClass().getClassLoader();



    private static List<InputLine> expectedResult = List.of(
            new InputLine(1,"node1",List.of(new NetworkUnit("CCU1",CCU))),
            new InputLine(2,"CCU1",List.of(new NetworkUnit("node1",NODE),new NetworkUnit("node2",NODE))),
            new InputLine(3,"node2",List.of(new NetworkUnit("CCU1",CCU))),

            new InputLine(4,"node3",List.of(new NetworkUnit("CCU2",CCU))),
            new InputLine(5,"CCU2",List.of(new NetworkUnit("node3",NODE),new NetworkUnit("node4",NODE))),
            new InputLine(6,"node4",List.of(new NetworkUnit("CCU2",CCU),new NetworkUnit("node5",NODE))),
            new InputLine(7,"node5",List.of(new NetworkUnit("node4",NODE),new NetworkUnit("node6",NODE))),
            new InputLine(8,"node6",List.of(new NetworkUnit("node5",NODE))));



    @Test
    void read_validInputFile_verifyGeneratedOutput() throws Exception {
        FileReader fr = new FileReader(classLoader.getResource("validInputFromTheTask.txt").getPath());
        fr.read();

        List<InputLine> actualLineList = fr.getLineList();
        assertEquals(expectedResult,actualLineList);
    }


    @Test
    void read_emptyInputFile_verifyGeneratedOutput() throws Exception {

        FileReader fr = new FileReader(classLoader.getResource("emptyFile.txt").getPath());
        fr.read();

        List<InputLine> actualLineList = fr.getLineList();
        assertTrue(actualLineList.isEmpty(),"verify empty list");
    }

    @Test
    void read_fileDoesntExists_trowsException() throws Exception {

        FileReader fr = new FileReader("na.txt");

        MaxGroupCalculatorException thrown = assertThrows(MaxGroupCalculatorException.class,
                ()->fr.read(),"should throw an Exception");

        assertTrue(thrown.getMessage().contains("No such file or directory"));


    }


}