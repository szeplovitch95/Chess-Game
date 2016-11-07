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
	private Player currentMove;
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
		boolean correctFormat, validMove;
		currentMove = whitePlayer;
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
			
			validMove = processInput(move, currentMove);
			while(!validMove){
				if(currentMove.getPlayerColor() == 'w'){
					System.out.print("White's move: ");
				}else{
					System.out.print("Black's move: ");
				}
				move = reader.nextLine();
				validMove = processInput(move, currentMove);
			}
			
			// Update piece outlooks (en passant, check...)
			if(board.getEnPassantEligible() != null){
				Pawn EP = (Pawn)board.getEnPassantEligible();
				if(EP.getColor() != currentMove.getPlayerColor()){
					// not eligible anymore
					board.clearEnPassant();
				}
			}
			
			System.out.println();
			switchPlayer();
			
			
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
	
	public boolean processInput(String input, Player current){
		
		String origin = "";
		String dest = "";
		
		// check to see if a special type of move (resign, draw ect)
		if(input.equals("resign")){
			//set game over
			setGameOver();			
			return true;
		}else if(input.equals("draw")){
			
			if(getDraw()){
				setGameOver();
				return true;
			}
		}else if(input.substring(0, 2).equals(input.substring(3, 5))){
			// not moving anything
			System.out.println("Illegal move, try again.");
			return false;
			
		}else if(input.length() == 11){
			// current player is asking for a draw
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(3, 5);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(board.findPiece(origin).getColor() != currentMove.getPlayerColor()){
				// player trying to move a piece that is not theirs
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(!board.findPiece(origin).isMoveValid(origin, dest, board)){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
			}else{
				// valid move
				board.movePiece(origin, dest);
			}
			
			setDraw();
			
		}else if (input.length() == 7){
			// player is looking for a promotion
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(3, 5);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(!board.findPiece(origin).getRole().equals("p")){
				// can only promote pawns
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(board.findPiece(origin).getColor() != currentMove.getPlayerColor()){
				// player trying to move a piece that is not theirs
				System.out.println("Illegal move, try again.");
				return false;
			
			}else if(!board.findPiece(origin).isMoveValid(origin, dest, board)){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
				
			}else{
				// make sure pawn is in promotable position
				if(dest.charAt(1) == '1' || dest.charAt(1) == '8'){
					// make the move
					board.movePiece(origin, dest);
					
					// set pawn to the promoted piece
					board.promotePawn(dest, input.charAt(6));
					
				}else{
					// pawn is not in the position to be promoted
					System.out.println("Illegal move, try again.");
					return false;
				}
			}
			
		}else{
			// normal move
			// break up the input into origin and destination
			origin = input.substring(0, 2);
			dest = input.substring(3, 5);
			
			// check to see if there is a valid piece at the origin
			if(board.findPiece(origin) == null){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(board.findPiece(origin).getColor() != currentMove.getPlayerColor()){
				// player trying to move a piece that is not theirs
				System.out.println("Illegal move, try again.");
				return false;
			
			}else if(!board.findPiece(origin).isMoveValid(origin, dest, board)){
				// not a valid move
				System.out.println("Illegal move, try again.");
				return false;
				
			}else{
				// valid move
				board.movePiece(origin, dest);
			}
			
		}

		
		// HOW TO SEE IF OPPONENT IS IN CHECK AFTER THIS MOVE?
		// HOW TO SEE IF THIS MOVE IS PUTTING THE USER IN CHECK?
		return true;
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
	
	public void switchPlayer(){
		if(currentMove == whitePlayer){
			currentMove = blackPlayer;
		}else{
			currentMove = whitePlayer;
		}
	}
	
	public void addMove(String move) {
		//implement this method to add a move to the List of all  moves
	}
	
	
}
