package com.alexOssin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.alexOssin.NetworkUnit.isCCU;
import static org.junit.jupiter.api.Assertions.*;

class NetworkUnitTest {

    @Test
    void isCCU_validCCUUnitName() {
        String unitName = "CCU123";
        assertTrue(isCCU(unitName));

    }

    @Test
    void isCCU_lowerCaseCCU_notCCU() {
        String unitName = "ccu123";
        Assertions.assertFalse(isCCU(unitName));

    }

    @Test
    void isCCU_emptyCcuUnitName() {
        String unitName = "";
        Assertions.assertFalse(isCCU(unitName));

    }

    @Test
    void isCCU_startsWithCcuNotFollowedByNumeric_notCCU() {
        String unitName = "CCU /123";
        Assertions.assertFalse(isCCU(unitName));

    }
    @Test
    void newUnitType_ccuUnitNameDoesntMatchCcuUnitType_trowsException(){
        String unitName = "CCU123";
        NetworkUnitType unitType = NetworkUnitType.NODE;


        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()->new NetworkUnit(unitName,unitType),
                "expected IllegalArgumentException");
        assertTrue(thrown.getMessage().contains("Unit name "+unitName+" doesn't match "+unitType+" Unit Type"));


    }
}