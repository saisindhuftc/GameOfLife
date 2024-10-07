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
        if(row<0 || column<0){
            throw new InvalidInputException("Can not create Cell with negative rows and cols");
        }
        this.row = row;
        this.column=column;
        this.cellStatus = cellStatus;
    }

    private int getAliveNeighbours(List<Cell> neighbours) {
        int count = 0;
        for(Cell neighbour : neighbours) {
            if(neighbour.cellStatus == CellStatus.ALIVE)
                count++;
        }
        return count;
    }
    public Cell nextGenerationState(List<Cell> neighbours) {
        int aliveNeighbors = getAliveNeighbours(neighbours);
        if(cellStatus.equals(CellStatus.ALIVE) && aliveNeighbors==2 || aliveNeighbors==3){
            return new Cell(CellStatus.ALIVE,this.row,this.column);
        }
        return new Cell(CellStatus.DEAD,this.row,this.column);
    }

    public CellStatus getCellStatus() {
        return this.cellStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return row == cell.row && column == cell.column && cellStatus == cell.cellStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, cellStatus);
    }
}