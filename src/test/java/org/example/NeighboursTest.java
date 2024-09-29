package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.example.Neighbours;
import org.example.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class NeighboursTest {

    @Test
    void testNeighboursInitializationWithNegitiveRowValue() {
        assertThrows(InvalidInputException.class, () -> new Neighbours(-1, 3));
        assertDoesNotThrow(() -> new Neighbours(0, 0));
    }

    @Test
    void testNeighboursInitializationWithNegitiveColumnValue() {
        assertThrows(InvalidInputException.class, () -> new Neighbours(5, -3));
    }

    @Test
    void testNeighboursInitializationWithZeroRowAndColumnValues() {
        assertDoesNotThrow(() -> new Neighbours(0, 0));
    }

    @Test
    void testIntializingNeighboursWithValidRowAndColumnValues() {
        assertDoesNotThrow(() -> new Neighbours(2, 0));
    }

    @Test
    void testValidNeighboursCoordinatesForCellAt0_0() {
        Neighbours neighbours = new Neighbours(0, 0);
        List<int[]> validCoords = neighbours.validNeighboursCoordinates(3, 3);

        assertEquals(3, validCoords.size());
        assertArrayEquals(new int[]{0, 1}, validCoords.get(0));
        assertArrayEquals(new int[]{1, 0}, validCoords.get(1));
        assertArrayEquals(new int[]{1, 1}, validCoords.get(2));
    }

    @Test
    void testValidNeighboursCoordinatesForCellAt1_1() {
        Neighbours neighbours = new Neighbours(1, 1);
        List<int[]> validCoords = neighbours.validNeighboursCoordinates(3, 3);

        assertEquals(8, validCoords.size());
        assertArrayEquals(new int[]{1, 0}, validCoords.get(0));
        assertArrayEquals(new int[]{0, 1}, validCoords.get(1));
        assertArrayEquals(new int[]{1, 2}, validCoords.get(2));
        assertArrayEquals(new int[]{2, 1}, validCoords.get(3));
        assertArrayEquals(new int[]{0, 0}, validCoords.get(4));
        assertArrayEquals(new int[]{2, 2}, validCoords.get(5));
        assertArrayEquals(new int[]{2, 0}, validCoords.get(6));
        assertArrayEquals(new int[]{0, 2}, validCoords.get(7));
    }

    @Test
    void testValidNeighboursCoordinatesForCellAt2_2() {
        Neighbours neighbours = new Neighbours(2, 2);
        List<int[]> validCoords = neighbours.validNeighboursCoordinates(3, 3);

        assertEquals(3, validCoords.size());
        assertArrayEquals(new int[]{2, 1}, validCoords.get(0));
        assertArrayEquals(new int[]{1, 2}, validCoords.get(1));
        assertArrayEquals(new int[]{1, 1}, validCoords.get(2));
    }
}