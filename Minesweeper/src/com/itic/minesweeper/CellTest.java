package com.itic.minesweeper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;

public class CellTest {
	private NumberCell NumberCell;

	@Before
	public void setUp() {
		int size = 4;
		Cell cells[][] = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; i < size; i++) {
				cells[i][j] = new NumberCell(i, j);
			}
		}

		cells[0][2] = new BombCell(0, 2);
		cells[2][1] = new BombCell(2, 1);
		cells[3][1] = new BombCell(3, 1);
		cells[3][3] = new BombCell(3, 3);

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (cells[x][y] instanceof NumberCell) {
					NumberCell cell = (NumberCell) cells[x][y];
					cell.setNeighbors(getNeighbors(cells, x, y));
				}
			}
		}
		Grid grid = new Grid(cells);

	}

	private Set<Cell> getNeighbors(Cell[][] grid, int x, int y) {
		Set<Cell> cells = new HashSet<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				if (x + i < 0 || x + i > grid.length)
					continue;

				if (y + j < 0 || y + j > grid.length)
					continue;
				cells.add(grid[x + i][y + j]);
			}
		}
		return cells;
	}
	
	

}
