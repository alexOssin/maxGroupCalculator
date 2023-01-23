package com.alexOssin;

import java.util.Objects;

import static com.alexOssin.NetworkUnitType.*;


/**
 * represents a node or a Central Communication Unit (CCU)
 */
public class NetworkUnit {
    private String unitName;
    private Long id;
    private NetworkUnitType unitType;

    public NetworkUnit(String unitName) {
        if(unitName == null )
            throw new IllegalArgumentException("NetworkUnit parameters can not be null");
        this.unitName = unitName;
        if (isCCU(unitName))
            unitType = CCU;
        else
            unitType = NODE;

    }

    public NetworkUnit(String unitName, NetworkUnitType unitType) {
        if(unitName == null || unitType ==null)
            throw new IllegalArgumentException("NetworkUnit parameters can not be null");
        //CCU unit name should match CCU unit type
        if(isCCU(unitName) && unitType.equals(NODE)){
            throw new IllegalArgumentException("Unit name "+unitName+" doesn't match "+ unitType+" Unit Type ");
        }
        this.unitName = unitName;
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "\nunitName='" + unitName + '\'' +
                ", unitType=" + unitType;
    }

    public String getUnitName() {
        return unitName;
    }

    public Long getId() {
        return id;
    }

    public NetworkUnitType getUnitType() {
        return unitType;
    }


    /**
     * CCU designations always start with the string “CCU”, followed by a numeric
     * @param unitName
     * @return true if the string starts with “CCU”, followed by a numeric chars
     */
    public static Boolean isCCU(String unitName){

        String[] splitResult = unitName.trim().split(CCU.toString());

        return splitResult.length == 2
                && splitResult[0].equals("")
                && splitResult[1].matches("\\d+");

    }



    /**
    NOTE : equal and hashCode are based on the name and type field only .
           should be based on all the fields .
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkUnit unit = (NetworkUnit) o;
        return unitName.equals(unit.unitName) && unitType == unit.unitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitName, unitType);
    }
}