package com.alexOssin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class reads any provided input file and parses the lines of the file
 * into the List<InputLine> data structure .
 * NOTE!! no validation is made at this stage
 */
public class FileReader {

    private String resourceName;
    private InputLineParser inputLineParser;
    private Integer currentLineNumber = 0; //for logging only
    private List<InputLine> lineList;


    public FileReader(final String resourceName) {
        this.resourceName = resourceName;
        lineList = new ArrayList<>();
        inputLineParser = new InputLineParser();
    }

    public List<InputLine> getLineList() {
        return lineList;
    }

    public void read() throws MaxGroupCalculatorException {
        File file = new File(resourceName);

        try(BufferedReader bf = new BufferedReader(new java.io.FileReader(file))){
            lineList = bf.lines()
                    .map(line -> generateLine(line))
                    .collect(Collectors.toList());


            //uncomment for more details
            //System.out.println("\n"+lineList+"\n\n");

        }
        catch (IOException ioe){
            throw new MaxGroupCalculatorException("Error reading "+resourceName+" input file. "+ioe.getMessage());
        }
    }

    private InputLine generateLine(String lineStr){
        InputLine newLine = inputLineParser.parseInputLine(lineStr);
        newLine.setLineNumber(++currentLineNumber);
        return newLine;

    }

}
