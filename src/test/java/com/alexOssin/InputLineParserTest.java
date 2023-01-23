package com.alexOssin;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static com.alexOssin.NetworkUnitType.*;
import static org.junit.jupiter.api.Assertions.*;

class InputLineParserTest {

    @Test
    public void parseInputLine_fullLine(){

        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(1,"unit1",
                List.of(new NetworkUnit("unit2",NODE),new NetworkUnit("unit3",NODE)));

        InputLine actualResult = parser.parseInputLine("unit1,unit2,unit3");

        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

    @Test
    public void parseInputLine_parseEmptyLine(){
        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(1,"", Collections.emptyList());
        InputLine actualResult = parser.parseInputLine("");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }


    @Test
    public void parseInputLine_noFirstField(){
        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(0,"",List.of(new NetworkUnit("CCU1",CCU)));
        InputLine actualResult = parser.parseInputLine(",CCU1");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

    @Test
    public void parseInputLine_multConnectedUnits(){
        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(1,"",List.of(new NetworkUnit("CCU1",CCU),new NetworkUnit("unit1",NODE)));
        InputLine actualResult = parser.parseInputLine(",CCU1,unit1");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

    @Test
    public void parseInputLine_CcuUnit_parsedAsCcuType(){
        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(1,"",List.of(new NetworkUnit("CCU123",CCU)));
        InputLine actualResult = parser.parseInputLine(",CCU123");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

    @Test
    public void parseInputLine_nodeUnit_parsedAsNodeType(){
        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(0,"someUnitStr",Collections.emptyList());
        InputLine actualResult = parser.parseInputLine("someUnitStr");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

    @Test
    public void parseInputLine_nullStringToParse_throwsException(){

        InputLineParser parser = new InputLineParser();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.parseInputLine(null);
        });
        assertEquals("InputLineParser parameter can not be null", exception.getMessage());
    }

    @Test
    public void parseInputLine_filtersOutEmptyStringsInBetweenCommas(){

        InputLineParser parser = new InputLineParser();
        InputLine expectedResult = new InputLine(1,"",List.of(new NetworkUnit("CCU123",CCU)));
        InputLine actualResult = parser.parseInputLine(",CCU123, ,  ,,  ,");
        assertEquals(expectedResult,actualResult,"assert parsed line");
    }

}