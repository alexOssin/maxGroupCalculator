package com.alexOssin;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputLineValidatorTest {

    @Test
    void isNodeNameLengthValid() {
        InputLine il = new InputLine("NODE_NAME_EXCEEDS_MAXIMUM_LENGTH", List.of(new NetworkUnit("node",NetworkUnitType.NODE)));
        ValidationResult res = InputLineValidator.isNodeNameLengthValid().apply(il);
        assertEquals(ValidationResult.NODE_NAME_EXCEEDS_MAX_LENGTH,res);
    }

    @Test
    void isNonEmptyFirstField() {
        InputLine il = new InputLine("", List.of(new NetworkUnit("node",NetworkUnitType.NODE)));
        ValidationResult res = InputLineValidator.isNonEmptyFirstField().apply(il);
        assertEquals(ValidationResult.EMPTY_FIRST_FIELD_IS_NOT_ALLOWED,res);
    }

    @Test
    void isNonEmptyLine() {
        InputLine il = new InputLine("", Collections.emptyList());
        ValidationResult res = InputLineValidator.isNonEmptyLine().apply(il);
        assertEquals(ValidationResult.EMPTY_LINES_ARE_NOT_ALLOWED,res);
    }
}