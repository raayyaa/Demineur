package com.itic.minesweeper;

import java.util.Scanner;

public class MineSweeper {
	private Grid grid;
	private Scanner scanner = new Scanner(System.in);

	public MineSweeper() {
		startGame();
	}

	private void startGame() {
		// choisir diffuclty
		System.out.println("Choisissez votre difficulté: 1:EASY, 2:MEDIUM, 3:HARD");
		int diff = scanner.nextInt();
		Difficulty difficulty = null;
		switch (diff) {
		case 1:
			difficulty = Difficulty.EASY;
			break;
		case 2:
			difficulty = Difficulty.MEDIUM;
			break;
		case 3:
			difficulty = Difficulty.HARD;
		default:
			return;
		}
		grid = new Grid(difficulty);
		play();
	}

	private void play() {
		System.out.println("Bienvenue dans le démineur, pour jouer taper les coordonnées (x,y) de la case.\n ");

		while (true) {
			System.out.println(grid);
			System.out.println("x: ");
			int x = scanner.nextInt();

			System.out.println("y: ");
			int y = scanner.nextInt();
			System.out.println("Guess: (1:creuser la case;2:! poser drapeau;3:? supposer)");
			int guess = scanner.nextInt();
			click(x, y, guess);

		}
	}

	public void click(int x, int y, int guess) {
		State s = null;
		switch (guess) {
		case 1:
			s = State.CLICK;
			break;
		case 2:
			s = State.BOMB;
			break;
		case 3:
			s = State.UNDEFINED;
		default:
			return;
		}
		try {
			grid.click(x, y, s);
			if(grid.isWinner()) {
				System.out.println("Winner");
				System.out.print(grid.printGameOver());

			}
		} catch (RuntimeException e) {
			System.out.println("GameOver");
			System.out.print(grid.printGameOver());
			System.exit(1);
		}

	}
}
