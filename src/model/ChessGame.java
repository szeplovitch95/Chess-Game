package model;
import chess.Chess;

import java.util.List;
import java.util.Scanner;

public class ChessGame {
	private ChessBoard board; 
	private Player whitePlayer; 
	private Player blackPlayer;
	private List<String> allMoves;
	private boolean draw;
	private boolean gameOver;
	private Player Winner; 
	
	public ChessGame() {}
	
	public ChessGame(ChessBoard board, Player whitePlayer, Player blackPlayer) {
		this.board = board; 
		this.whitePlayer = whitePlayer; 
		this.blackPlayer = blackPlayer; 
		this.draw = false;
		this.gameOver = false;
		
		startPlay();
	}
	
	public void startPlay(){
		boolean correctFormat;
		Player currentMove = whitePlayer;
		Scanner reader = new Scanner(System.in);
		
		// this is where the game execution will loop
		while(Winner == null && gameOver == false){
			
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
			
			processInput(move, currentMove);
			
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
	
	public void processInput(String input, Player current){
		
		String origin, dest;
		
		// check to see if a special type of move (resign, draw ect)
		if(input.equals("resign")){
			//set game over
			setGameOver();			
			return;
		}else if(input.equals("draw")){
			
			if(getDraw()){
				setGameOver();
				return;
			}
		}else if(input.length() == 11){
			// current player is asking for a draw
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(2, 4);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				System.out.println();
				return;
			}
			else{
				if(board.findPiece(origin).isMoveValid(origin, dest)){
					// make the move
					board.movePiece(origin, dest);
				}
			}
			
			setDraw();
			
		}else if (input.length() == 7){
			// player is looking for a promotion
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(2, 4);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				System.out.println();
				return;
			}
			else{
				if(board.findPiece(origin).isMoveValid(origin, dest)){
					// make the move
					board.movePiece(origin, dest);
				}
			}
			
			// Set pawn to promoted piece
			
		}else{
			// normal move
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(2, 4);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				System.out.println();
				return;
			}
			else{
				if(board.findPiece(origin).isMoveValid(origin, dest)){
					// make the move
					board.movePiece(origin, dest);
				}
			}
			
		}

		
		// HOW TO SEE IF OPPONENT IS IN CHECK AFTER THIS MOVE?
		// HOW TO SEE IF THIS MOVE IS PUTTING THE USER IN CHECK?
		
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
	public void setGameOver(){
		gameOver = true;
	}
	
	public boolean getGameOver(){
		return gameOver;
	}

	public Player getWinner() {
		return Winner;
	}

	public void setWinner(Player winner) {
		Winner = winner;
	}
	
	public void addMove(String move) {
		//implement this method to add a move to the List of all  moves
	}
	
	
}
