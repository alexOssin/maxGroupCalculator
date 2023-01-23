package com.alexOssin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {

    private final PrintStream stdOut = System.out;
    private final PrintStream stdErr = System.err;
    private final ByteArrayOutputStream stdOutCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream stdErrCaptor = new ByteArrayOutputStream();

    private final ClassLoader classLoader = getClass().getClassLoader();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(stdOutCaptor));
        System.setErr(new PrintStream(stdErrCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdOut);
        System.setErr(stdErr);
    }



    @Test
    public void processValidInput_twoGroups(){
        Main.main(new String[]{classLoader.getResource("validInputFromTheTask.txt").getPath()});
        assertTrue(stdOutCaptor.toString()
                .contains("maximum number of connected units  is : 4"));
    }

    @Test
    public void processEmptyFile(){
        Main.main(new String[]{classLoader.getResource("emptyFile.txt").getPath()});
        assertTrue(stdOutCaptor.toString()
                .contains("no connected groups were found"));
    }

    @Test
    public void processValidInput_loopDiagramOneGroup(){
        Main.main(new String[]{classLoader.getResource("validInputOneGroup.txt").getPath()});
        assertTrue(stdOutCaptor.toString()
                .contains("maximum number of connected units  is : 2"));
    }



    @Test
    public void processNotValidInput_noFirstFieldInARow(){
        Main.main(new String[]{classLoader.getResource("notValidNoFirstFieldInFirstRow.txt").getPath()});

        assertTrue(stdErrCaptor.toString()
                .contains("INPUT ERROR : line number 1 : EMPTY_FIRST_FIELD_IS_NOT_ALLOWED"));

    }
}
