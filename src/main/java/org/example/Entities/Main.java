package org.example.Entities;

import java.util.Scanner;

public class  Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        System.out.print("Enter the seeding percentage (1-100): ");
        int seedingPercentage = scanner.nextInt();

        Grid grid = new Grid(rows, cols);

        grid.seedRandomCells(rows, cols, seedingPercentage);

        System.out.println("Initial Generation:");
        grid.printGrid();

        boolean gameRunning = true;

        int genration = 1;
        try {
            while (gameRunning) {
                grid.nextGeneration();
                System.out.println("Generation " + genration++ + ":");
                grid.printGrid();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (grid.countAliveCells() == 0) {
                    System.out.println("All cells are dead. Game Over!");
                    gameRunning = false;
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        scanner.close();
    }
}