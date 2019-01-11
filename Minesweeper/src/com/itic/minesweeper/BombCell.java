package com.itic.minesweeper;

public class BombCell extends Cell {

	public BombCell(int x, int y) {
		super(x, y);

	}

	@Override
	public String toString() {
		String str = super.toString();
		if (str.equals(""))
			str = getGuess() == State.CLICK ? "*" : "";
		return str;
	}

	@Override
	public void click(State state) {
		if (state == State.CLICK)
			throw new RuntimeException("GameOver");
		setGuess(state);
	}

}
