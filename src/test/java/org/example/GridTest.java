package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Exceptions.InvalidInputException;
import org.example.Exceptions.PercentageInputException;
import org.junit.jupiter.api.Test;

class GridTest {

    @Test
    void testGridInitializationWith0Rows() {
        assertThrows(InvalidInputException.class, () -> new Grid(0, 5));
    }

    @Test
    void testGridInitializationWith0Columns() {
        assertThrows(InvalidInputException.class, () -> new Grid(5, 0));
    }

    @Test
    public void testInitializeGridWith3RowsAnd3Cols() throws InvalidInputException {
        int rows = 3;
        int cols = 3;

        Grid grid = new Grid(rows, cols);

        assertNotNull(grid);
    }

    @Test
    public void testInitializeGridWithNegativeRowsThrowError() {
        int rows = -1;
        int cols = 2;

        assertThrows(InvalidInputException.class, () -> new Grid(rows, cols));
    }

    @Test
    public void testInitializeGridWithNegativeRowsAndNegativeColsShouldThrowError() {
        int rows = -1;
        int cols = -2;

        assertThrows(InvalidInputException.class, () -> new Grid(rows, cols));
    }


    @Test
    public void testInitializeGridWith10Rows10ColsAndSeedingPercentage0ShouldThrowError() {
        int rows = 10;
        int cols = 10;
        int seedingPercentage = 0;

        assertThrows(PercentageInputException.class, () -> {
            Grid grid = new Grid(rows, cols);
            grid.seedRandomCells(seedingPercentage, rows, cols);
        });
    }

    @Test
    public void testFor2x2GridWithOneAliveCellNextGenerationWillBeAllDead() throws InvalidInputException {
        Grid grid = new Grid(2, 2);
        grid.seedRandomCells(25, 2, 2);

        assertEquals(1, grid.countAliveCells());

        grid.nextGeneration();

        assertEquals(0, grid.countAliveCells());
    }

    @Test
    public void testFor3x3GridWithOneAliveCellNextGenerationWillBeAllDead() throws InvalidInputException {
        Grid grid = new Grid(3, 3);
        grid.seedRandomCells(15, 3, 3);

        assertEquals(1, grid.countAliveCells());

        grid.nextGeneration();

        assertEquals(0, grid.countAliveCells());
    }

    @Test
    public void testFor4x4GridWithOneAliveCellNextGenerationWillBeAllDead() throws InvalidInputException {
        Grid grid = new Grid(4, 4);
        grid.seedRandomCells(10, 4, 4);

        assertEquals(1, grid.countAliveCells());

        grid.nextGeneration();

        assertEquals(0, grid.countAliveCells());
    }

    @Test
    void testSeedRandomCells() throws InvalidInputException {
        Grid grid = new Grid(5, 5);

        assertThrows(PercentageInputException.class, () -> grid.seedRandomCells(0, 5, 5));
        assertThrows(PercentageInputException.class, () -> grid.seedRandomCells(101, 5, 5));
        assertDoesNotThrow(() -> grid.seedRandomCells(50, 5, 5));
    }

    @Test
    void testNextGeneration() throws InvalidInputException {
        Grid grid = new Grid(5, 5);
        grid.seedRandomCells(50, 5, 5);
        int initialAliveCells = grid.countAliveCells();
        grid.nextGeneration();

        assertNotEquals(initialAliveCells, grid.countAliveCells());
    }
}