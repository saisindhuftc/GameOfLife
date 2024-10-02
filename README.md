### GameOfLife

Game of Life Explanation

The Game of Life is not your typical computer game. It is a cellular automaton, and was invented by Cambridge mathematician John Conway.

This game became widely known when it was mentioned in an article published by Scientific American in 1970. It consists of a grid of cells which, based on a few mathematical rules, can live, die or multiply. Depending 
on the initial conditions, the cells form various patterns throughout the course of the game.

##Rules

For a space that is populated:

Each cell with one or no neighbors dies, as if by solitude.


Each cell with four or more neighbors dies, as if by overpopulation.


Each cell with two or three neighbors survives.


For a space that is empty or unpopulated:

Each cell with three neighbors becomes populated.

Simulate Conway's Game Of Life on a m * n grid

Allow for a command line interface to start a new run by specifying m & n and a percentage value for seeding. Start the Game Of Life by seeding a random population of cells in the specified percentage on the grid .

On every tick of time, Present the Grid's state as output by modelling dead and alive cells as - & * s

COntinue until all cells are dead or user ends the simulation
