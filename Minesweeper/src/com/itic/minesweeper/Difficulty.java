package com.itic.minesweeper;

import java.awt.event.KeyEvent;

public enum Difficulty {
	EASY(4), MEDIUM(6), HARD(9);
	private int value;

	private Difficulty(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

}
