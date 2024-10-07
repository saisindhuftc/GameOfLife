package org.example.Entities;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        System.out.print("Enter seeding percentage (1-100): ");
        int percentage = scanner.nextInt();

        Grid grid = new Grid(rows, cols);
        grid.seedRandomCells(percentage);
        grid.printGrid();

        scanner.nextLine();
        while (true) {
            System.out.println("Press Enter for the next generation or type 'exit' to quit.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Simulation ended by the user.");
                break;
            }

            grid.nextGeneration();
            grid.printGrid();

            if (grid.countAliveCells() == 0) {
                System.out.println("All cells are dead. Simulation ended.");
                break;
            }
        }
        scanner.close();
    }
}