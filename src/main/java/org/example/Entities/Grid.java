package org.example.Entities;

import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.example.Exceptions.PercentageInputException;

import java.util.List;
import java.util.Random;

public class Grid {
    private Cell[][] grid;

    public Grid(int rows, int columns) throws InvalidInputException {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidInputException("Rows and Columns must be greater than 0");
        }
        this.grid = new Cell[rows][columns];
    }

    public void seedRandomCells(int percentage, int rows, int cols) {
        if (percentage <= 0 || percentage > 100) {
            throw new PercentageInputException("Percentage must be between 1 and 100");
        }
        int aliveCellCount = (rows * cols) * percentage / 100;
        Random random = new Random();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (random.nextInt(2) == 1 && aliveCellCount > 0) {
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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isAlive()) {
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
                List<int[]> neighboursCoords = new Neighbours(i, j).validNeighboursCoordinates(grid.length, grid[0].length);
                int aliveNeighbors = countAliveNeighbours(neighboursCoords);
                newGrid[i][j] = grid[i][j].nextGenerationState(aliveNeighbors);
            }
        }
        this.grid = newGrid;
    }

    private int countAliveNeighbours(List<int[]> neighboursCoords) {
        int aliveCount = 0;
        for (int[] coords : neighboursCoords) {
            if (grid[coords[0]][coords[1]].isAlive()) {
                aliveCount++;
            }
        }
        return aliveCount;
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isAlive()) {
                    System.out.print('*');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }
    }
}
