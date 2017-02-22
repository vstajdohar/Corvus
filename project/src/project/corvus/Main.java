package project.corvus;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private static boolean gameActive = true;
	private static int playerNo = 0;
	private static boolean isFirstMove = true;

	private static char[][] board; 




	public static void main(String[] args){

		TicTacToe game = new TicTacToe();

		while(gameActive){
			if(isFirstMove){
				game.askPlayer();
				game.placeMark( playerNo);
				isFirstMove = false;
			}else{
				while( !game.placeMark(playerNo)){
					if(playerNo == 0){
						game.askPlayer();
					}else{
						if(game.calculateDifficult() == 0){
							game.computerRandomMove();
						}else{
							if(!game.playSmart())
								game.computerRandomMove();
						}
					}
				}
			}

			game.changePlayer();

			if(playerNo == 1){
				System.out.println("Computer played!");
			}
			game.showBoard();

			if (game.checkForWin()) {

				if(playerNo == 0){
					System.out.println("PLAYER WINS! Congrats!");
					game.setStats(0);
				}else{
					System.out.println("COMPUTER WINS!");
					game.setStats(1);
				}
				game.showStats();
				if(game.askPlayerToContinue()){
					game.clearBoard();
					isFirstMove = true;
					playerNo = 1;
				}else{
					gameActive = false;
					System.exit(0);
				}
			}else if (game.isBoardFull()) {

				System.out.println("DRAW!");
				game.setStats(2);
				game.showStats();
				if(game.askPlayerToContinue()){
					game.clearBoard();
					isFirstMove = true;
					playerNo = 1;
				}else{
					gameActive = false;
					System.exit(0);
				}

			}

			if(playerNo == 0){
				playerNo = 1;
			}else{
				playerNo = 0;
			}
		}
	}
}