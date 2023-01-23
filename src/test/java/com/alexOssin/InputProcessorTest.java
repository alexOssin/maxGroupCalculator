package com.alexOssin;

import org.junit.jupiter.api.Test;

import static com.alexOssin.ValidationResult.*;
import static org.junit.jupiter.api.Assertions.*;

class InputProcessorTest {
    private final ClassLoader classLoader = getClass().getClassLoader();


    @Test
    void processInputFile_validInput_noExceptionThrown() {
        InputProcessor inputProcessor = new InputProcessor(classLoader.getResource("validInputFromTheTask.txt").getPath());
        assertDoesNotThrow(()->inputProcessor.processInputFile(),"should not throw any exception");

    }
    @Test
    void processInputFile_inputFileContainsEmptyLine_throwsException() {
        InputProcessor inputProcessor = new InputProcessor(classLoader.getResource("notValidInputWithEmptyLines.txt").getPath());
        MaxGroupCalculatorException exception = assertThrows(MaxGroupCalculatorException.class,
                () -> inputProcessor.processInputFile());

        assertTrue(exception.getMessage().contains(EMPTY_LINES_ARE_NOT_ALLOWED.toString()),"should contain "+EMPTY_LINES_ARE_NOT_ALLOWED+" message ");

    }
    @Test
    void processInputFile_inputFileContainsLinesWithEmptyFirstField_throwsException() {

        InputProcessor inputProcessor = new InputProcessor(classLoader.getResource("notValidNoFirstFieldInFirstRow.txt").getPath());
        MaxGroupCalculatorException exception = assertThrows(MaxGroupCalculatorException.class,
                () -> inputProcessor.processInputFile());

        assertTrue(exception.getMessage().contains(EMPTY_FIRST_FIELD_IS_NOT_ALLOWED.toString()),"should contain "+EMPTY_FIRST_FIELD_IS_NOT_ALLOWED+" message ");

    }
    @Test
    void processInputFile_inputFileContainsNodeNameThatExceedsMaxLength_throwsException() {

        InputProcessor inputProcessor = new InputProcessor(classLoader.getResource("notValidNodeNameExceedsMaxLength.txt").getPath());
        MaxGroupCalculatorException exception = assertThrows(MaxGroupCalculatorException.class,
                () -> inputProcessor.processInputFile());

        assertTrue(exception.getMessage().contains(NODE_NAME_EXCEEDS_MAX_LENGTH.toString()),"should contain "+NODE_NAME_EXCEEDS_MAX_LENGTH+" message ");

    }


}