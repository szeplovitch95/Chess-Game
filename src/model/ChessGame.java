package model;

import java.util.List;
import java.util.Scanner;

public class ChessGame {
	private ChessBoard board; 
	private Player whitePlayer; 
	private Player blackPlayer;
	private List<String> allMoves;
	private boolean draw;
	private String Winner; 
	
	public ChessGame() {}
	
	public ChessGame(ChessBoard board, Player whitePlayer, Player blackPlayer) {
		this.board = board; 
		this.whitePlayer = whitePlayer; 
		this.blackPlayer = blackPlayer; 
		draw = false;
		
		startPlay();
	}
	
	public void startPlay(){
		boolean correctFormat;
		Player currentMove = whitePlayer;
		Scanner reader = new Scanner(System.in);
		
		// this is where the game execution will loop
		while(Winner == null){
			
			//Print the board
			board.printBoard();
			System.out.println();
			
			// Print the prompt
			if(currentMove.getPlayerColor() == 'w'){
				System.out.print("White's move: ");
			}else{
				System.out.print("Black's move: ");
			}
			String move = reader.nextLine();
			//System.out.print(move);
			correctFormat = checkFormat(move);
			//System.out.println(correctFormat);
			
			while(correctFormat == false){
				System.out.println();
				System.out.print("Invalid input, try again: ");
				move = reader.nextLine();
				correctFormat = checkFormat(move);
			}
			
			processInput(move);
		}
		
		reader.close();
		
	}
	
	public boolean checkFormat(String move){
		
		boolean retVal = true;
		
		if(move == null || move.equals("")){
			retVal = false;
		}
		else if(move.equals("resign")){
			retVal = true;
		}
		else if(getDraw() && move.equals("draw")){
			retVal = true;
		}
		else if(move.length() > 11 || (move.length() > 7 && move.length() < 11 )){
			retVal = false;
		}
		else{
			try{
				
				if((move.charAt(0) < 'a' || move.charAt(0) > 'h')
						|| (move.charAt(1) < '1' || move.charAt(1) > '8')
						|| move.charAt(2) != ' '
						|| (move.charAt(3) < 'a' || move.charAt(3) > 'h')
						|| (move.charAt(4) < '1' || move.charAt(4) > '8')){
					
					retVal = false;
				}
				
				// have to check to promotions and draws
				if(move.length() > 5){
					if(move.charAt(5) != ' ' ||
							(move.charAt(6) != 'R'
							&& move.charAt(6) != 'N'
							&& move.charAt(6) != 'B'
							&& move.charAt(6) != 'Q'
							&& (move.charAt(6) != 'd'
									|| move.charAt(7) != 'r'
									|| move.charAt(8) != 'a'
									|| move.charAt(9) != 'w'
									|| move.charAt(10) != '?'))){
						
						retVal = false;
					}
				}
				
			}catch(Exception e){
				//System.out.println("exception");
				retVal = false;
			}

		}
		return retVal;
	}
	
	public void processInput(String input){
		
	}

	public ChessBoard getBoard() {
		return board;
	}

	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public List<String> getAllMoves() {
		return allMoves;
	}

	public void setAllMoves(List<String> allMoves) {
		this.allMoves = allMoves;
	}
	
	public void setDraw(){
		draw = true;
	}
	
	public boolean getDraw(){
		return draw;
	}

	public String getWinner() {
		return Winner;
	}

	public void setWinner(String winner) {
		Winner = winner;
	}
	
	public void addMove(String move) {
		//implement this method to add a move to the List of all  moves
	}
	
	
}
