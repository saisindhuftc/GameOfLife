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
        Cell deadCell = new Cell(CellStatus.DEAD, 3, 3);
        int aliveNeighbors = 3;

        Cell nextState = deadCell.nextGenerationState(aliveNeighbors);
        assertEquals(new Cell(CellStatus.ALIVE, 3, 3), nextState);
    }

    @Test
    void testAliveCellRemainsAliveWith2AliveNeighbors() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 3, 3);
        int aliveNeighbors = 2;

        Cell nextState = aliveCell.nextGenerationState(aliveNeighbors);
        assertEquals(new Cell(CellStatus.ALIVE, 3, 3), nextState);
    }

    @Test
    void testAliveCellDiesWithLessThan2AliveNeighbors() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 2, 2);
        int aliveNeighbors = 1;

        Cell nextState = aliveCell.nextGenerationState(aliveNeighbors);
        assertEquals(new Cell(CellStatus.DEAD, 2, 2), nextState);
    }

    @Test
    void testAliveCellDiesWithMoreThan3AliveNeighbors() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 1, 1);
        int aliveNeighbors = 4;

        Cell nextState = aliveCell.nextGenerationState(aliveNeighbors);
        assertEquals(new Cell(CellStatus.DEAD, 1, 1), nextState);
    }

    @Test
    void testNextGenerationStateForMixedNeighbors() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 1, 2);
        Cell deadCell = new Cell(CellStatus.DEAD, 2, 0);

        int aliveNeighbors = 2;

        assertTrue(aliveCell.nextGenerationState(aliveNeighbors).isAlive());
        assertFalse(deadCell.nextGenerationState(aliveNeighbors).isAlive());
    }

}
