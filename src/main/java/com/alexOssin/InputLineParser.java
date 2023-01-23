package com.alexOssin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputLineParser {

    public InputLine parseInputLine(final String line){
        if(line==null)
            throw new IllegalArgumentException("InputLineParser parameter can not be null");
        String firstField = parseFirstField(line);
        List<NetworkUnit> connectedUnits = parseConnectedUnits(line);

        return new InputLine(firstField,connectedUnits);
    }
    private String parseFirstField(final String line) {
        String firstField = Arrays.stream(line.split(","))
                .findFirst().orElse("");
        return firstField.trim();
    }

    /*
    parses by "," delimiter
    skips first field from the line to form a list of connected units
    filters out all empty strings in between commas and trims whitespaces .
    example : given " ccu1, node1 , ,,,CCU2 , ," returns: node1,CCU2 as network units
     */
    private List<NetworkUnit> parseConnectedUnits(final String line) {
        return Arrays.stream(line.split(","))
                .skip(1l)//skip first field in each line
                .filter(unitName-> !unitName.trim().isEmpty()) //filter out all empty strings between ","
                .map(unitName -> new NetworkUnit(unitName.trim()))
                .collect(Collectors.toList());

    }

}
