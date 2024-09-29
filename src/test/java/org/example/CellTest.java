package org.example;
import org.example.Cell;
import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void testDeadCellWillBecameAliveIfItHas3AliveNeighbours() {
        Cell deadCell = new Cell(CellStatus.DEAD, 3, 3);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 2, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 2));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 1));

        assertEquals(deadCell.nextGenerationState(neighbours), new Cell(CellStatus.ALIVE, 3, 3));
    }

    @Test
    public void testAliveCellWillRemainAliveIfItHas2AliveNeighbours() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 3, 3);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 0));

        assertEquals(aliveCell.nextGenerationState(neighbours), new Cell(CellStatus.ALIVE, 3, 3));
    }

    @Test
    public void testAliveCellHasLessThan2AliveNeighboursItWillBecameDead() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 2, 2);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));

        assertEquals(aliveCell.nextGenerationState(neighbours), new Cell(CellStatus.DEAD, 2, 2));
    }

    @Test
    public void testAliveCellWillBecameDeadIfItHas4LiveNeighbours() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 1, 1);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 0, 0));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 2));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 2));

        assertEquals(aliveCell.nextGenerationState(neighbours), new Cell(CellStatus.DEAD, 1, 1));
    }

    @Test
    void testNextGenerationState() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 0, 0);
        Cell deadCell = new Cell(CellStatus.DEAD, 0, 0);

        List<Cell> neighbours = Arrays.asList(
                new Cell(CellStatus.ALIVE, 0, 1),
                new Cell(CellStatus.ALIVE, 1, 0),
                new Cell(CellStatus.DEAD, 1, 1)
        );

        assertEquals(CellStatus.ALIVE, aliveCell.nextGenerationState(neighbours).getCellStatus());
        assertEquals(CellStatus.DEAD, deadCell.nextGenerationState(neighbours).getCellStatus());
    }
}