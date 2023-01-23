package com.alexOssin;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedGroupTest {

    @Test
    public void containsByUnitName_unitExists(){

        ConnectedGroup group = new ConnectedGroup();
        NetworkUnit networkUnit = new NetworkUnit("Alex");
        group.add(networkUnit);

        Boolean expectedResult = group.containsByUnitName(networkUnit.getUnitName());
        assertTrue(expectedResult,"verify unit exists");
    }

    @Test
    public void containsByUnitName_unitDoesntExist(){

        ConnectedGroup group = new ConnectedGroup();
        NetworkUnit networkUnitUnit = new NetworkUnit("Alex");
        group.add(networkUnitUnit);

        Boolean expectedResult = group.containsByUnitName("NA");
        assertFalse(expectedResult,"verify unit doesn't exists");
    }

    @Test
    public void containsByUnitName_emptyUnitList_unitDoesntExist(){

        ConnectedGroup group = new ConnectedGroup();
        Boolean expectedResult = group.containsByUnitName("NA");

        assertFalse(expectedResult,"verify unit doesn't exists");
    }

    @Test
    public void add_addCCU(){

        ConnectedGroup group = new ConnectedGroup();
        NetworkUnit networkUnitUnit = new NetworkUnit("CCU1");
        group.add(networkUnitUnit);

        assertAll(
                ()->assertEquals(1,group.getConnectedUnits().size()),
                ()->assertEquals(1,group.getNumberOfCCUs(),"assert num of CCUs"),
                ()->assertEquals(0,group.getNumberOfNodes())
        );
    }

    @Test
    public void add_addNode(){

        ConnectedGroup group = new ConnectedGroup();
        NetworkUnit networkUnit = new NetworkUnit("Alex");
        group.add(networkUnit);

        assertAll(
                ()->assertEquals(1,group.getConnectedUnits().size()),
                ()->assertEquals(1,group.getNumberOfNodes(),"assert num of Nodes"),
                ()->assertEquals(0,group.getNumberOfCCUs(),"assert num of CCUs")
        );
    }


    @Test
    public void addAll_addListOfUnits(){

        ConnectedGroup group = new ConnectedGroup();
        List<NetworkUnit> networkUnitUnitList = List.of(
                new NetworkUnit("NODEAlpha"),
                new NetworkUnit("CCU33"));

        group.addAll(networkUnitUnitList);

        assertAll(
                ()->assertEquals(group.getConnectedUnits().size(),2),
                ()->assertEquals(group.getNumberOfCCUs(),1,"assert num of CCUs"),
                ()->assertEquals(group.getNumberOfNodes(),1,"assert num of NODES")
        );
    }

}

