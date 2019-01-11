package com.itic.minesweeper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Cell {
	private Point position;
	private State guess;
	
	public Cell(int x, int y) {
		position = new Point(x, y);
	}

	public State getGuess() {
		return guess;
	}

	protected void setGuess(State guess) {
		this.guess = guess;
	}

	public abstract void click(State state);

	@Override
	public String toString() {
		if (guess == State.BOMB)
			return "!";
		else if (guess == State.UNDEFINED)
			return "?";
		
		return "";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guess == null) ? 0 : guess.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (guess != other.guess)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	
	
}
