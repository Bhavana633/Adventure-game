package game;

import java.util.Scanner;

public class Main {

	public static void main(String[]args) {

		Scanner sc = new Scanner(System.in); // create scanner to read input
		game game = new game(sc); // pass scanner to Game
		game.start(); // start the game
		sc.close(); // close scanner at end
	}

}
