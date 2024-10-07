package org.example.Entities;
import org.example.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeighboursTest {

    @Test
    void testAbleToCreateNeighbourObject() {
        assertDoesNotThrow(()-> new Neighbours(2, 0));
    }

    @Test
    void testNeighboursInitializationWithZeroRowAndColumnValues() {
        assertDoesNotThrow(() -> new Neighbours(0, 0));
    }

    @Test
    void testNotToHaveANeighbourWithNegativeRowValue() {
        assertThrows(InvalidInputException.class, ()-> new Neighbours(-1, 0));
    }

    @Test
    void testNeighboursInitializationWithNegitiveColumnValue() {
        assertThrows(InvalidInputException.class, () -> new Neighbours(5, -3));
    }

    @Test
    void testIntializingNeighboursWithValidRowAndColumnValues() {
        assertDoesNotThrow(() -> new Neighbours(2, 2));
    }

    @Test
    void testAbleToGetValidNeighboursCoordinatesForCellAt0_0() {
        Neighbours cellNeighboursList = new Neighbours(0,0);
        List<int[]> expectedList = new ArrayList<>();
        expectedList.add(new int[]{0,1});
        expectedList.add(new int[]{1,0});
        expectedList.add(new int[]{1,1});

        List<int[]> neighboursCoordinates = cellNeighboursList.validNeighboursCoordinates(3,3);

        for(int i=0; i<neighboursCoordinates.size(); i++){
            assertEquals(expectedList.get(i)[0], neighboursCoordinates.get(i)[0]);
            assertEquals(expectedList.get(i)[1], neighboursCoordinates.get(i)[1]);
        }
    }

    @Test
    void TestAbleToGetValidNeighboursCoordinatesForCellAt1_1() {
        Neighbours cellNeighboursList = new Neighbours(1,1);
        List<int[]> expectedlist = new ArrayList<>();
        expectedlist.add(new int[]{1,0});
        expectedlist.add(new int[]{0,1});
        expectedlist.add(new int[]{1,2});
        expectedlist.add(new int[]{2,1});
        expectedlist.add(new int[]{0,0});
        expectedlist.add(new int[]{2,2});
        expectedlist.add(new int[]{2,0});
        expectedlist.add(new int[]{0,2});

        List<int[]> neighboursCoordinates = cellNeighboursList.validNeighboursCoordinates(3,3);

        for(int i=0; i<neighboursCoordinates.size(); i++){
            assertEquals(expectedlist.get(i)[0], neighboursCoordinates.get(i)[0]);
            assertEquals(expectedlist.get(i)[1], neighboursCoordinates.get(i)[1]);
        }
    }

}