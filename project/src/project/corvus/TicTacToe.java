package project.corvus;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board; 

    private char currentPlayerMark;
    
    private int playerWin = 0;
    private int computerWin = 0;
    private int draw = 0;
    
	public static int row;
	public static int column;

	private static int difficult = 0;

    Main main = new Main();
    public TicTacToe() {

        board = new char[3][3];

        currentPlayerMark = 'x';

        populateBoard();

    }

    public void populateBoard() {

        // Rows
        for (int r = 0; r < 3; r++) {
            // Columns
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public void showBoard() {

        System.out.println("-------------");

        for (int r = 0; r < 3; r++) {

            System.out.print("| ");

            for (int c = 0; c < 3; c++) {

                System.out.print(board[r][c] + " | ");

            }

            System.out.println();

            System.out.println("-------------");

        }
    }

    public boolean isBoardFull() {

        boolean isFull = true;

        for (int r = 0; r < 3; r++) {

            for (int c = 0; c < 3; c++) {

                if (board[r][c] == ' ') {

                    isFull = false;

                }
            }
        }
        return isFull;
    }

    public boolean checkForWin() {

        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());

    }
    
    public boolean playSmart(){
    	if(checkRowsForPlayWin()){
    		return true;
    	}else if(checkColForPlayWin()){
    		return true;
    	}else if(checkDiagonalsForPlayWin()){
    		return true;
    	}else if(checkRowsForPlayNoLoose()){
    		return true;
    	}else if(checkColForPlayNoLoose()){
    		return true;
    	}else if(checkDiagonalsForPlayNoLoose()){
    		return true;
    	}else{
    		return false;
    	}
//    	return (checkRowsForPlay() || checkColForPlay() || checkDiagonalsForPlay());
    }
    
    private boolean checkRowsForPlayWin() {

        for (int r = 0; r < 3; r++) {

            if (checkRowForPlay(r, board[r][0], board[r][1], board[r][2], 'o') == true) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkColForPlayWin() {

        for (int c = 0; c < 3; c++) {

            if (checkColForPlay(c, board[0][c], board[1][c], board[2][c], 'o') == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForPlayWin() {

        return ((checkDiagonalPositiveForPlay(board[0][0], board[1][1], board[2][2], 'o') == true) || (checkDiagonalNegativeForPlay(board[0][2], board[1][1], board[2][0], 'o') == true));

    }
    
    private boolean checkRowsForPlayNoLoose() {

        for (int r = 0; r < 3; r++) {

            if (checkRowForPlay(r, board[r][0], board[r][1], board[r][2], 'x') == true) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkColForPlayNoLoose() {

        for (int c = 0; c < 3; c++) {

            if (checkColForPlay(c, board[0][c], board[1][c], board[2][c], 'x') == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForPlayNoLoose() {

        return ((checkDiagonalPositiveForPlay(board[0][0], board[1][1], board[2][2], 'x') == true) || (checkDiagonalNegativeForPlay(board[0][2], board[1][1], board[2][0], 'x') == true));

    }

    private boolean checkRowsForWin() {

        for (int r = 0; r < 3; r++) {

            if (checkRowCol(board[r][0], board[r][1], board[r][2]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {

        for (int c = 0; c < 3; c++) {

            if (checkRowCol(board[0][c], board[1][c], board[2][c]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {

        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));

    }
    
    private boolean checkRowCol(char c1, char c2, char c3) {

        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));

    }
    
    private boolean checkRowForPlay(int row, char c1, char c2, char c3, char mark) {

        if (((c1 != ' ') && mark == c1) && ((c1 == c2) &&  (c3 == ' '))){
        	setPlace(row, 2);
        	return true;
        }
        if (((c1 != ' ') && mark == c1) && ((c1 == c3) &&  (c2 == ' '))){
        	setPlace(row, 1);
        	return true;
        }
        if ((c1 == ' ') && (c2!= ' ' && mark == c2 && (c2 == c3))){
        	setPlace(row, 0);
        	return true;
        }
        
        return false;

    }
    
    private boolean checkColForPlay(int row, char c1, char c2, char c3, char mark) {

        if (((c1 != ' ') && mark == c1) && ((c1 == c2) &&  (c3 == ' '))){
        	setPlace(2, row);
        	return true;
        }
        if (((c1 != ' ') && mark == c1) && ((c1 == c3) &&  (c2 == ' '))){
        	setPlace(1, row);
        	return true;
        }
        if ((c1 == ' ') && (c2!= ' ' && mark == c2 && (c2 == c3))){
        	setPlace(0, row);
        	return true;
        }
        
        return false;

    }
    
    private boolean checkDiagonalPositiveForPlay(char c1, char c2, char c3, char mark) {

        if (((c1 != ' ') && mark == c1) && ((c1 == c2) &&  (c3 == ' '))){
        	setPlace(2, 2);
        	return true;
        }
        if (((c1 != ' ') && mark == c1) && ((c1 == c3) &&  (c2 == ' '))){
        	setPlace(1, 1);
        	return true;
        }
        if ((c1 == ' ') && (c2!= ' ' && mark == c2 && (c2 == c3))){
        	setPlace(0, 0);
        	return true;
        }
        
        return false;

    }
    
    private boolean checkDiagonalNegativeForPlay(char c1, char c2, char c3, char mark) {

        if (((c1 != ' ') && mark == c1) && ((c1 == c2) &&  (c3 == ' '))){
        	setPlace(2, 0);
        	return true;
        }
        if (((c1 != ' ') && mark == c1) && ((c1 == c3) &&  (c2 == ' '))){
        	setPlace(1, 1);
        	return true;
        }
        if ((c1 == ' ') && (c2!= ' ' && mark == c2 && (c2 == c3))){
        	setPlace(0, 2);
        	return true;
        }
        
        return false;

    }
    

    public void changePlayer() {

        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        }else {
            currentPlayerMark = 'x';
        }
    }

    public boolean placeMark(int player) {

        if ((row >= 0) && (row < 3)) {

            if ((column >= 0) && (column < 3)) {

                if (board[row][column] == ' ') {

                    board[row][column] = currentPlayerMark;

                    return true;
                }else{
                    if(player==0)
                        System.out.println("Place already taken");
                }
            }
        }
        return false;
    }
    
    public void setStats(int player){
    	
    	if(player == 0){
    		playerWin ++;
    	}else if (player == 1){
    		computerWin ++;
    	}else{
    		draw ++;
    	}
    }
    
    public void showStats(){
    	
    	System.out.println("STATS!!! Computer win: " + computerWin + "; Player win: " + playerWin + "; Draw: " + draw);
    }
    
    public void clearBoard(){
    	 
    	board = new char[3][3];

         currentPlayerMark = 'x';

         populateBoard();
    }
    
	public static void askPlayer() {
		Scanner sc = new Scanner(System.in);
		int one = 0, two = 0;
		boolean okOne = false;
		boolean okTwo = false;

		while(!okOne){
			System.out.print("Enter first coordiante : ");
			try{
				one=sc.nextInt();
				if(one>2)
					System.out.println("Wrong input");
				else
					okOne=true;
			}catch(InputMismatchException e){
				System.out.println("Wrong input");
			}
		}

		while(!okTwo){
			System.out.print("Enter second coordiante : ");
			try{
				two=sc.nextInt();
				if(two>2)
					System.out.println("Wrong input");
				else
					okTwo = true;
			}catch(InputMismatchException e){
				System.out.println("Wrong input");
			}
		}

		setPlace(one, two);
	}

	public static void computerRandomMove() {
		Scanner sc = new Scanner(System.in);
		int one = 0, two = 0;
		try{
			one=getRandom(3);
			if(one>2)
				System.out.println("Wrong input");
		}catch(InputMismatchException e){
			System.out.println("Wrong input");
		}
		try{
			two=getRandom(3);
			if(two>2)
				System.out.println("Wrong input");
		}catch(InputMismatchException e){
			System.out.println("Wrong input");
		}
		setPlace(one, two);
	}

	private static int getRandom ( int to){
		return( new Random().nextInt(Math.abs(to)));
	}

	public static boolean askPlayerToContinue(){
		Scanner sc = new Scanner(System.in);
		boolean shallContinue = false;
		boolean isAnswerOK = false;

		System.out.print("Play another game Y/N: ");
		
		while(!isAnswerOK){
			String play = sc.next();
			if("Y".equals(play)){
				shallContinue = true;
				isAnswerOK = true;
			}else if ("N".equals(play)){
				System.out.print("Tkank you for playing. Have a good day.");
				shallContinue = false;
				isAnswerOK = true;
			}else{
				System.out.print("Wrong value. Please insert Y or N!");
			}
		}
		return shallContinue;
	}
	
	public static void setPlace(int x, int y){
		row = x;
		column = y;
	}
	
	public int calculateDifficult(){
		
		int percentage = 0;
		
		if(computerWin > playerWin || computerWin == playerWin){
			difficult = 0;
		}else{
			int totalGames = computerWin+playerWin+draw;
			percentage = playerWin*100/totalGames;
			
			if(percentage < 30){
				difficult = 0;
			}else if (percentage > 90){
				difficult = 1;
			}else{
				difficult = getRandom(2);
			}
		}
		return difficult;
	}
}
