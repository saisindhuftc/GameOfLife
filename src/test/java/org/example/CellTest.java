package org.example;

import org.example.Entities.Cell;
import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testCanNotCreateNewCellWithNegativeRows() {
        assertThrows(InvalidInputException.class, () -> new Cell(CellStatus.ALIVE, -1, 5));
    }

    @Test
    void testCanNotCreateNewCellWithNegativeCols() {
        assertThrows(InvalidInputException.class, () -> new Cell(CellStatus.ALIVE, 5, -1));
    }

    @Test
    void testDeadCellBecomesAliveWith3AliveNeighbors() {
        // Create a 3x3 grid
        Cell[][] grid = new Cell[3][3];

        // Set up the grid with 3 alive neighbors around the dead cell at (1, 1)
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.ALIVE, 0, 1);
        grid[0][2] = new Cell(CellStatus.DEAD, 0, 2);
        grid[1][0] = new Cell(CellStatus.ALIVE, 1, 0);
        grid[1][1] = new Cell(CellStatus.DEAD, 1, 1); // Dead cell to be tested
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.DEAD, 2, 0);
        grid[2][1] = new Cell(CellStatus.DEAD, 2, 1);
        grid[2][2] = new Cell(CellStatus.DEAD, 2, 2);

        // Get the dead cell's next generation state
        Cell deadCell = grid[1][1];
        Cell nextState = deadCell.nextGenerationState(grid);

        // Assert that the dead cell becomes alive
        assertEquals(new Cell(CellStatus.ALIVE, 1, 1), nextState);
    }

    @Test
    void testAliveCellRemainsAliveWith2AliveNeighbors() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.ALIVE, 0, 1);
        grid[0][2] = new Cell(CellStatus.DEAD, 0, 2);
        grid[1][0] = new Cell(CellStatus.DEAD, 1, 0);
        grid[1][1] = new Cell(CellStatus.ALIVE, 1, 1);
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.ALIVE, 2, 0);
        grid[2][1] = new Cell(CellStatus.DEAD, 2, 1);
        grid[2][2] = new Cell(CellStatus.DEAD, 2, 2);

        Cell aliveCell = grid[1][1];
        Cell nextState = aliveCell.nextGenerationState(grid);
        assertEquals(new Cell(CellStatus.ALIVE, 1, 1), nextState);
    }

    @Test
    void testAliveCellDiesWithLessThan2AliveNeighbors() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.DEAD, 0, 1);
        grid[0][2] = new Cell(CellStatus.DEAD, 0, 2);
        grid[1][0] = new Cell(CellStatus.DEAD, 1, 0);
        grid[1][1] = new Cell(CellStatus.ALIVE, 1, 1);
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.DEAD, 2, 0);
        grid[2][1] = new Cell(CellStatus.DEAD, 2, 1);
        grid[2][2] = new Cell(CellStatus.DEAD, 2, 2);

        Cell aliveCell = grid[1][1];
        Cell nextState = aliveCell.nextGenerationState(grid);
        assertEquals(new Cell(CellStatus.DEAD, 1, 1), nextState);
    }

    @Test
    void testAliveCellDiesWithMoreThan3AliveNeighbors() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.ALIVE, 0, 1);
        grid[0][2] = new Cell(CellStatus.ALIVE, 0, 2);
        grid[1][0] = new Cell(CellStatus.ALIVE, 1, 0);
        grid[1][1] = new Cell(CellStatus.ALIVE, 1, 1);
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.ALIVE, 2, 0);
        grid[2][1] = new Cell(CellStatus.ALIVE, 2, 1);
        grid[2][2] = new Cell(CellStatus.ALIVE, 2, 2);

        Cell aliveCell = grid[1][1];
        Cell nextState = aliveCell.nextGenerationState(grid);
        assertEquals(new Cell(CellStatus.DEAD, 1, 1), nextState);
    }

    @Test
    void testNextGenerationStateForMixedNeighbors() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.DEAD, 0, 1);
        grid[0][2] = new Cell(CellStatus.ALIVE, 0, 2);
        grid[1][0] = new Cell(CellStatus.DEAD, 1, 0);
        grid[1][1] = new Cell(CellStatus.ALIVE, 1, 1);
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.ALIVE, 2, 0);
        grid[2][1] = new Cell(CellStatus.DEAD, 2, 1);
        grid[2][2] = new Cell(CellStatus.ALIVE, 2, 2);

        Cell aliveCell = grid[1][1];
        Cell deadCell = grid[1][0];

        assertTrue(deadCell.nextGenerationState(grid).isAlive());
        assertFalse(aliveCell.nextGenerationState(grid).isAlive());
    }

    @Test
    public void testCountAliveNeighbours() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = new Cell(CellStatus.ALIVE, 0, 0);
        grid[0][1] = new Cell(CellStatus.DEAD, 0, 1);
        grid[0][2] = new Cell(CellStatus.ALIVE, 0, 2);
        grid[1][0] = new Cell(CellStatus.DEAD, 1, 0);
        grid[1][1] = new Cell(CellStatus.ALIVE, 1, 1);
        grid[1][2] = new Cell(CellStatus.DEAD, 1, 2);
        grid[2][0] = new Cell(CellStatus.ALIVE, 2, 0);
        grid[2][1] = new Cell(CellStatus.DEAD, 2, 1);
        grid[2][2] = new Cell(CellStatus.ALIVE, 2, 2);

        Cell cell = grid[1][1];
        int aliveNeighbours = cell.countAliveNeighbours(grid);
        assertEquals(4, aliveNeighbours);
    }
}
