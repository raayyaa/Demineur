package com.itic.minesweeper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {
	private Cell grid[][];
	private int size;

	public Grid(Difficulty diff) {
		size = diff.getValue();
		grid = new Cell[size][size];
		fill();
	}

	Grid(Cell cells[][]) {
		grid = cells;
		size = cells.length;
	}

	private void fill() {
		generateBombs();
		generateNumberBombs();
	}

	private void generateBombs() {
		Random generator = new Random();
		int x, y;
		for (int i = 0; i < size;) {
			x = generator.nextInt(size);
			y = generator.nextInt(size);
			Cell bomb = new BombCell(x, y);
			if (grid[x][y] == null) {
				grid[x][y] = bomb;
				i++;
			}
		}
	}

	boolean isBomb(int x, int y) {
		return grid[x][y] instanceof BombCell;

	}

	private void generateNumberBombs() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (grid[x][y] == null) {
					grid[x][y] = new NumberCell(x, y);
				}
			}
		}
		countBombs();
	}

	private void countBombs() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (grid[x][y] instanceof NumberCell) {
					NumberCell cell = (NumberCell) grid[x][y];
					cell.setNeighbors(getNeighbors(x, y));
				}
			}
		}
	}

	private Set<Cell> getNeighbors(int x, int y) {
		Set<Cell> cells = new HashSet<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				if (x + i < 0 || x + i >= size)
					continue;

				if (y + j < 0 || y + j >= size)
					continue;
				cells.add(grid[x + i][y + j]);
			}
		}
		return cells;
	}

	public boolean isWinner() {
		int validFlags = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Cell cell = grid[x][y];
				if (cell.getGuess() == State.BOMB && cell instanceof BombCell)
					validFlags++;
			}
		}
		return validFlags == size;
	}

	public void click(int x, int y, State state) {
		grid[x][y].click(state);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < size; i++) {
			row.append("---------------|");
		}
		row.insert(0, "\n|").append("\n");
		sb.append(row);
		for (int i = 0; i < size; i++) {
			sb.append("|");
			for (int j = 0; j < size; j++) {
				Cell cell = grid[i][j];

				sb.append("\t").append(cell).append("\t|");
			}
			sb.append(row);
		}
		return sb.toString();
	}

	public String printGameOver() {
		StringBuilder sb = new StringBuilder();
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < size; i++) {
			row.append("---------------|");
		}
		row.insert(0, "\n|").append("\n");
		sb.append(row);
		for (int i = 0; i < size; i++) {
			sb.append("|");
			for (int j = 0; j < size; j++) {
				Cell cell = grid[i][j];
				String s = cell instanceof BombCell ? "*" : cell.toString();
				sb.append("\t").append(s).append("\t|");
			}
			sb.append(row);
		}
		return sb.toString();
	}

}
