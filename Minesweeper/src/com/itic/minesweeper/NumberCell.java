package com.itic.minesweeper;

import java.util.HashSet;
import java.util.Set;

public class NumberCell extends Cell {

	private int value;
	private Set<Cell> neighbors;

	@Override
	public String toString() {
		String str = super.toString();

		if (str.equals(""))
			str = getGuess() == State.CLICK ? String.valueOf(value) : " ";

		return str;
	}

	public NumberCell(int x, int y) {
		super(x, y);
	}

	public void setNeighbors(Set<Cell> neighbors) {
		this.neighbors = neighbors;
		countBombsArround();
	}

	private void countBombsArround() {
		for (Cell cell : neighbors)
			if (cell instanceof BombCell)
				value++;

	}

	@Override
	public void click(State state) {
		if (state == State.CLICK) {
			for (Cell cell : neighbors)
				if (cell instanceof NumberCell) {
					NumberCell numberCell = (NumberCell) cell;
					if (numberCell.value == 0)
						cell.click(state);
				}

		}
		setGuess(state);

		/*
		 * neighbors.stream() .filter(cell -> cell instanceof NumberCell) .map(cell ->
		 * (NumberCell) cell) .filter(numberCell -> numberCell.value == 0) .forEach(cell
		 * -> cell.click());
		 */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumberCell other = (NumberCell) obj;

	
		if (value != other.value)
			return false;
		return true;
	}

}
