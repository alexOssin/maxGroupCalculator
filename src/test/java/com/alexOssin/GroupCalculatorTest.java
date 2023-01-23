package com.alexOssin;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.alexOssin.NetworkUnitType.*;
import static com.alexOssin.NetworkUnitType.NODE;
import static org.junit.jupiter.api.Assertions.*;

class GroupCalculatorTest {
    private static List<InputLine> expectedResult = List.of(
            new InputLine(1,"node1",List.of(new NetworkUnit("CCU1",CCU))),
            new InputLine(2,"CCU1",List.of(new NetworkUnit("node1",NODE),new NetworkUnit("node2",NODE))),
            new InputLine(3,"node2",List.of(new NetworkUnit("CCU1",CCU))),
            new InputLine(4,"node3",List.of(new NetworkUnit("CCU2",CCU))),
            new InputLine(5,"CCU2",List.of(new NetworkUnit("node3",NODE),new NetworkUnit("node4",NODE))),
            new InputLine(6,"node4",List.of(new NetworkUnit("CCU2",CCU),new NetworkUnit("node5",NODE))),
            new InputLine(7,"node5",List.of(new NetworkUnit("node4",NODE),new NetworkUnit("node6",NODE))),
            new InputLine(8,"node6",List.of(new NetworkUnit("node5",NODE))));

    private static List<InputLine> validInputOneGroup = List.of(
            new InputLine(1,"node1",List.of(new NetworkUnit("CCU1",CCU))),
            new InputLine(2,"CCU1",List.of(new NetworkUnit("node1",NODE),new NetworkUnit("node2",NODE))),
            new InputLine(3,"node2",List.of(new NetworkUnit("CCU1",CCU)))
    );


    @Test
    void calculateGroupWithMaximumNumberOfUnits_twoGroups_outputTheMaxOne() {
        GroupCalculator calculator = new GroupCalculator(expectedResult);
        Integer actualNumOfConnectedUnits = calculator.calculateGroupWithMaximumNumberOfUnits();
        assertEquals(4,actualNumOfConnectedUnits);
    }
    @Test
    void calculateGroupWithMaximumNumberOfUnits_emptyInput_noConnectedGroupFound() {
        GroupCalculator calculator = new GroupCalculator(Collections.emptyList());
        Integer actualNumOfConnectedUnits = calculator.calculateGroupWithMaximumNumberOfUnits();
        assertEquals(-1,actualNumOfConnectedUnits);
    }
    @Test
    void calculateGroupWithMaximumNumberOfUnits_oneGroup_outputsMax() {
        GroupCalculator calculator = new GroupCalculator(validInputOneGroup);
        Integer actualNumOfConnectedUnits = calculator.calculateGroupWithMaximumNumberOfUnits();
        assertEquals(2,actualNumOfConnectedUnits);
    }



}