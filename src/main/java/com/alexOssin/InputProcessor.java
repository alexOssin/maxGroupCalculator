package com.alexOssin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static com.alexOssin.InputLineValidator.*;

/*
   The class reads an input file,
   parses the input into InputLines
   and validates each one according to cases listed in InputLineValidator class
    */
public class InputProcessor {
    private FileReader fileReader;
    private String resourceName;
    private List<InputLine> inputLines = new ArrayList<>();


    public InputProcessor(final String resourceName){
        this.resourceName = resourceName;
        fileReader = new FileReader(resourceName);
    }


    public List<InputLine> getInputLines() {
        return inputLines;
    }

    public  void processInputFile() throws MaxGroupCalculatorException {
        fileReader.read();
        this.inputLines  = fileReader.getLineList();

        //validate input
        validate(inputLines);


    }
    private void validate(List<InputLine> inputLineList) throws MaxGroupCalculatorException {
        try{
            inputLineList.stream()
                    .forEach(line -> {
                        ValidationResult validationResult = validateLine(line);
                        if (validationResult != ValidationResult.VALID_LINE) {
                            throw new InputMismatchException("line number " + line.getLineNumber() + " : " + validationResult);
                        }
                    });
            //add more file data validation here if needed against data in DB  and other services , etc')
        }
        catch(Exception e){
            throw new MaxGroupCalculatorException("INPUT ERROR : "+e.getMessage());
        }
    }


    private ValidationResult validateLine (InputLine line){
        //add more line validation cases such as duplicated lines , num of lines restrictions here
        return isNonEmptyLine()
                .and(isNonEmptyFirstField())
                .and(isNodeNameLengthValid())
                .apply(line);

    }

}
