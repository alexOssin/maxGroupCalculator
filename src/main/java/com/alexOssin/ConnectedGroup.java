package com.alexOssin;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

import static com.alexOssin.NetworkUnitType.*;

public class ConnectedGroup {
    private Long id;
    private HashSet<NetworkUnit> connectedUnits;
    private Integer numberOfNodes = 0;
    private Integer numberOfCCUs = 0;


    public Integer getNumberOfNodes() {
        return numberOfNodes;
    }

    public Integer getNumberOfCCUs() {
        return numberOfCCUs;
    }

    public HashSet<NetworkUnit> getConnectedUnits() {
        return connectedUnits;
    }

    public ConnectedGroup() {
        connectedUnits = new HashSet<>();
    }

    public boolean containsByUnitName(String unitName){
        return connectedUnits.stream().filter(unit->unit.getUnitName().equals(unitName)).findAny().isPresent();
    }

    public void addAll(List<NetworkUnit> connectedUnitList){
        connectedUnitList.stream().forEach(connectedUnit->{
            if(!connectedUnits.contains(connectedUnit)){
                if(!add(connectedUnit)){
                    throw new InputMismatchException("failed to add unit [unitName="
                            +connectedUnit.getUnitName()+" unitType="+connectedUnit.getUnitType()+"]");
                }
            }
        });
    }

    public boolean add(NetworkUnit unit){
        if(unit.getUnitType().equals(CCU))
            numberOfCCUs +=1;
        else if (unit.getUnitType().equals(NODE))
            numberOfNodes +=1;

        return connectedUnits.add(unit);
    }

    @Override
    public String toString() {
        return "\nConnectedGroup : " +
                "number Of Nodes = " + numberOfNodes +
                ", number Of CCUs = " + numberOfCCUs +
                connectedUnits +
                "\n";
    }
}
