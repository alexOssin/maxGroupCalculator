package com.alexOssin;

public class Main {

    public static void main(String args[]) {
        try {
            InputProcessor inputProcessor = new InputProcessor(args[0]);
            inputProcessor.processInputFile();

            GroupCalculator calculator = new GroupCalculator(inputProcessor.getInputLines());
            calculator.calculateGroupWithMaximumNumberOfUnits();

        }
        catch (Exception e) {
            System.err.println("MaxGroupCalculator APP error : " + e.getMessage());
        }
    }
}
