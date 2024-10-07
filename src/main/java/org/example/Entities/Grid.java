package org.example.Entities;

import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.example.Exceptions.PercentageInputException;

import java.util.Random;

public class Grid {
    private final Cell[][] grid;

    public Grid(int rows, int columns) throws InvalidInputException {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidInputException("Rows and Columns must be greater than 0");
        }
        this.grid = new Cell[rows][columns];
    }

    public void seedRandomCells(int percentage) {
        if (percentage <= 0 || percentage > 100) {
            throw new PercentageInputException("Percentage must be between 1 and 100");
        }
        int aliveCellCount = (grid.length * grid[0].length) * percentage / 100;
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (random.nextInt(100) < percentage && aliveCellCount > 0) {
                    grid[i][j] = new Cell(CellStatus.ALIVE, i, j);
                    aliveCellCount--;
                } else {
                    grid[i][j] = new Cell(CellStatus.DEAD, i, j);
                }
            }
        }
    }

    public int countAliveCells() {
        int aliveCells = 0;
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.isAlive()) {
                    aliveCells++;
                }
            }
        }
        return aliveCells;
    }

    public void nextGeneration() {
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                newGrid[i][j] = grid[i][j].nextGenerationState(grid);
            }
        }
        System.arraycopy(newGrid, 0, grid, 0, grid.length);
    }

    public void printGrid() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                System.out.print(cell.isAlive() ? '*' : '-');
            }
            System.out.println();
        }
    }
}