package com.alexOssin;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * represents a line in an input file , where first filed is a unit name and
 * all other fields are collected in a list of connected units
 */
public class InputLine {
    private String firstField;
    private List<NetworkUnit> connectedUnits = new ArrayList<>();
    private Integer lineNumber = 0;


    public InputLine(final String firstField, final List<NetworkUnit> connectedUnits) {
        if(firstField == null || connectedUnits ==null)
            throw new IllegalArgumentException("InputLine parameters can not be null");
        this.firstField = firstField;
        this.connectedUnits = connectedUnits;
    }

    public InputLine(final Integer lineNumber, final String firstField, final List<NetworkUnit> connectedUnits) {
        if(lineNumber==null || firstField == null || connectedUnits ==null)
            throw new IllegalArgumentException("InputLine parameters can not be null");
        this.firstField = firstField;
        this.connectedUnits = connectedUnits;
        this.lineNumber = lineNumber;

    }

    @Override
    public String toString() {
        return "\n" +
                "lineNumber='" + lineNumber + '\'' +
                ", firstField='" + firstField + '\'' +
                ", connectedUnits=" + connectedUnits +
                ' ';
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getFirstField() {
        return firstField;
    }

    public List<NetworkUnit> getConnectedUnits() {
        return connectedUnits;
    }


    /**
     * compares Lines based on FirstField and connectedUnits only
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputLine inputLine = (InputLine) o;
        return firstField.equals(inputLine.firstField) && connectedUnits.equals(inputLine.connectedUnits);
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstField, connectedUnits);
    }
}
