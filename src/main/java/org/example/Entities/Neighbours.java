package org.example.Entities;

import org.example.Exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class Neighbours {

    private final int[][] NEIGHBOUR_OFFSETS;

    public Neighbours(int row, int column) {
        if (row < 0 || column < 0) {
            throw new InvalidInputException("Rows and Columns must be greater than 0");
        }
        this.NEIGHBOUR_OFFSETS = new int[][]{
                {row, column-1},    // left
                {row-1, column},    // top
                {row, column+1},    // right
                {row+1, column},    // bottom
                {row-1, column-1},  // top left
                {row+1, column+1},  // bottom right
                {row+1, column-1},  // bottom left
                {row-1, column+1}   // top right
        };
    }

    public List<int[]> validNeighboursCoordinates(int totalRows, int totalColumns) {
        List<int[]> validNeighboursCoordinates = new ArrayList<>();
        for (int[] neighbourPosition : NEIGHBOUR_OFFSETS) {
            int row = neighbourPosition[0];
            int column = neighbourPosition[1];
            if (row >= 0 && row < totalRows && column >= 0 && column < totalColumns) {
                validNeighboursCoordinates.add(new int[]{row, column});
            }
        }
        return validNeighboursCoordinates;
    }
}
