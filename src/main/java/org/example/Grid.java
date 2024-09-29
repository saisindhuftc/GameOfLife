package org.example;

import org.example.Enums.CellStatus;
import org.example.Exceptions.InvalidInputException;
import org.example.Exceptions.PercentageInputException;

import java.util.ArrayList;
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
                if (grid[i][j].getCellStatus() == CellStatus.ALIVE) {
                    aliveCells++;
                }
            }
        }
        return aliveCells;
    }

    public void nextGeneration() {
        Cell[][] newgrid = new Cell[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                List<int[]> neighboursCoords = new Neighbours(i, j).validNeighboursCoordinates(grid.length, grid[0].length);
                List<Cell> neighbours = new ArrayList<>();
                for (int[] coords : neighboursCoords) {
                    neighbours.add(grid[coords[0]][coords[1]]);
                }
                newgrid[i][j] = grid[i][j].nextGenerationState(neighbours);
            }
        }
        this.grid = newgrid;
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getCellStatus() == CellStatus.ALIVE) {
                    System.out.print('*');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }
    }
}