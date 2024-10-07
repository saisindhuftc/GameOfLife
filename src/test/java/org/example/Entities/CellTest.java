package org.example.Entities;

import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testDeadCellBecomesAliveWithExactlyThreeAliveNeighbours() {
        Cell deadCell = new Cell(CellStatus.DEAD, 3, 3);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 2, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 2));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 1));

        Cell nextGenCell = deadCell.nextGenerationState(neighbours);

        assertEquals(CellStatus.ALIVE, nextGenCell.getCellStatus());
    }

    @Test
    void testAliveCellRemainsAliveWithTwoAliveNeighbours() {

        Cell aliveCell = new Cell(CellStatus.ALIVE, 3, 3);
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 0));

        Cell nextGenCellWithTwoNeighbours = aliveCell.nextGenerationState(neighbours);

        assertEquals(CellStatus.ALIVE, nextGenCellWithTwoNeighbours.getCellStatus());
    }

    @Test
    void testAliveCellRemainsAliveWithThreeAliveNeighbours() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 3, 3);

        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 0));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 0));

        Cell nextGenCellWithThreeNeighbours = aliveCell.nextGenerationState(neighbours);

        assertEquals(CellStatus.ALIVE, nextGenCellWithThreeNeighbours.getCellStatus());

    }

    @Test
    void testAliveCellDiesWithLessThanTwoOrMoreThanThreeAliveNeighbours() {
        Cell aliveCell = new Cell(CellStatus.ALIVE, 2, 2);
        List<Cell> oneNeighbour = Arrays.asList(new Cell(CellStatus.ALIVE, 0, 1));
        Cell nextGenCellWithOneNeighbour = aliveCell.nextGenerationState(oneNeighbour);

        assertEquals(CellStatus.DEAD, nextGenCellWithOneNeighbour.getCellStatus());

        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 0, 0));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 2));
        neighbours.add(new Cell(CellStatus.ALIVE, 1, 0));
        neighbours.add(new Cell(CellStatus.ALIVE, 2, 1));

        Cell nextGenCellWithFiveNeighbours = aliveCell.nextGenerationState(neighbours);

        assertEquals(CellStatus.DEAD, nextGenCellWithFiveNeighbours.getCellStatus());
    }

    @Test
    void testDeadCellRemainsDeadWithFewerThanThreeAliveNeighbours() {
        Cell deadCell = new Cell(CellStatus.DEAD, 1, 1);
        List<Cell> neighbours = new ArrayList<>();

        neighbours.add(new Cell(CellStatus.ALIVE, 0, 0));
        neighbours.add(new Cell(CellStatus.ALIVE, 0, 1));

        Cell nextGenCell = deadCell.nextGenerationState(neighbours);

        assertEquals(CellStatus.DEAD, nextGenCell.getCellStatus());
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

}
