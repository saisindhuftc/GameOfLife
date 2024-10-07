package org.example.Entities;

import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;

import java.util.List;
import java.util.Objects;

public class Cell {

    private final int row;
    private final int column;
    private final CellStatus cellStatus;

    public Cell(CellStatus cellStatus, int row, int column) {
        if (row < 0 || column < 0) {
            throw new InvalidInputException("Rows and columns must be greater than 0");
        }
        this.row = row;
        this.column = column;
        this.cellStatus = cellStatus;
    }

    public Cell nextGenerationState(Cell[][] grid) {
        int aliveNeighbors = countAliveNeighbours(grid);
        if (cellStatus == CellStatus.ALIVE && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            return new Cell(CellStatus.ALIVE, this.row, this.column);
        } else if (cellStatus == CellStatus.DEAD && aliveNeighbors == 3) {
            return new Cell(CellStatus.ALIVE, this.row, this.column);
        } else {
            return new Cell(CellStatus.DEAD, this.row, this.column);
        }
    }

    public boolean isAlive() {
        return this.cellStatus == CellStatus.ALIVE;
    }

    public int countAliveNeighbours(Cell[][] grid) {
        List<int[]> neighboursCoords = new Neighbours(this.row, this.column)
                .validNeighboursCoordinates(grid.length, grid[0].length);
        int aliveCount = 0;
        for (int[] coords : neighboursCoords) {
            if (grid[coords[0]][coords[1]].isAlive()) {
                aliveCount++;
            }
        }
        return aliveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                column == cell.column &&
                cellStatus == cell.cellStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellStatus, row, column);
    }
}