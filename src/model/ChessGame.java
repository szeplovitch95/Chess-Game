package model;
import chess.Chess;

import java.util.List;
import java.util.Scanner;

public class ChessGame {
	private ChessBoard 		board; 
	private Player 			whitePlayer; 
	private Player 			blackPlayer;
	private List<String> 	allMoves;
	private boolean 		draw;
	private boolean 		gameOver;
	private boolean			inCheck;
	private Player 			currentMove;
	private Player 			Winner;

	
	public ChessGame() {}
	
	public ChessGame(ChessBoard board, Player whitePlayer, Player blackPlayer) {
		this.board		 = board; 
		this.whitePlayer = whitePlayer; 
		this.blackPlayer = blackPlayer; 
		this.draw		 = false;
		this.gameOver	 = false;
		this.inCheck	 = false;
		
		startPlay();

	}
	
	/**
	 * 
	 * Method that keeps the game going with user inputs
	 * 
	 */
	public void startPlay(){
		boolean correctFormat, validMove;
		currentMove = whitePlayer;
		Scanner reader = new Scanner(System.in);
		
		// this is where the game execution will loop
		while(Winner == null && gameOver == false){
			
			//Print the board
			board.printBoard();
			System.out.println();
			
			if(inCheck){
				System.out.println("Check");
			}
			
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
			
			if(currentMove.getPlayerColor() == 'w' && board.putInCheck(board.blackKingLocation, 'b')){
				King temp = (King)board.findPiece(Chess.coordinatesToString(board.blackKingLocation[0], board.blackKingLocation[1]));
				temp.setInCheck(true);
				inCheck = true;
			}else if(board.putInCheck(board.whiteKingLocation, 'w')){
				King temp = (King)board.findPiece(Chess.coordinatesToString(board.whiteKingLocation[0], board.whiteKingLocation[1]));
				temp.setInCheck(true);
				inCheck = true;
			}else{
				if(currentMove.getPlayerColor() == 'w'){
					King temp = (King)board.findPiece(Chess.coordinatesToString(board.blackKingLocation[0], board.blackKingLocation[1]));
					temp.setInCheck(false);
				}else{
					King temp = (King)board.findPiece(Chess.coordinatesToString(board.whiteKingLocation[0], board.whiteKingLocation[1]));
					temp.setInCheck(false);
				}
				
				inCheck = false;
			}
			
			if(getDraw() && (!move.equals("draw") && move.length() != 11)){
				setDraw(false);
			}
			
			System.out.println();
			switchPlayer();
			
		}
		
		reader.close();
		
	}
	
	/**
	 * 
	 * Method that takes in input from the user and checks the formatting of the string.
	 * 
	 * @param move String of raw input data from the user
	 * @return boolean whether or not the formatting of the input was valid or not
	 */
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
	
	
	/**
	 * 
	 * Method that takes in the user's input and makes sure the move
	 * is possible given the current condition of the game 
	 * 
	 * @param input String. Raw input data from the user
	 * @param current Player. The player whose turn it is
	 * @return Boolean deciding whether or not the move is valid or not
	 */
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
			}else if( currentMove.getPlayerColor() == 'w' && board.beforeMoveCheck(origin, dest, board.whiteKingLocation, 'w')){
				// move puts the current player in check
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(currentMove.getPlayerColor() == 'b' && board.beforeMoveCheck(origin, dest, board.blackKingLocation, 'b')){
				// move puts the current player in check
				System.out.println("Illegal move, try again.");
				return false;
				
			}else{
				// valid move
				//see if previous move was a castling
				if(board.getCastled()){
					board.castleKing(origin, dest);
				}else{
					board.movePiece(origin, dest);
				}
				board.setCastled(false);
			}
			
			setDraw(true);
			
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
				
			}else if( currentMove.getPlayerColor() == 'w' && board.beforeMoveCheck(origin, dest, board.whiteKingLocation, 'w')){
				// move puts the current player in check
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(currentMove.getPlayerColor() == 'b' && board.beforeMoveCheck(origin, dest, board.blackKingLocation, 'b')){
				// move puts the current player in check
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
				
			}else if( currentMove.getPlayerColor() == 'w' && board.beforeMoveCheck(origin, dest, board.whiteKingLocation, 'w')){
				// move puts the current player in check
				System.out.println("Illegal move, try again.");
				return false;
				
			}else if(currentMove.getPlayerColor() == 'b' && board.beforeMoveCheck(origin, dest, board.blackKingLocation, 'b')){
				// move puts the current player in check
				System.out.println("Illegal move, try again.");
				return false;
				
			}else{
				// valid move
				//see if previous move was a castling
				if(board.getCastled()){
					board.castleKing(origin, dest);
				}else{
					board.movePiece(origin, dest);
				}
				board.setCastled(false);
			}
			
		}

		
		// HOW TO SEE IF OPPONENT IS IN CHECK AFTER THIS MOVE?
		// HOW TO SEE IF THIS MOVE IS PUTTING THE USER IN CHECK?
		return true;
	}


	/**
	 * @return ChessBoard. The board that the current game is being played on
	 */
	public ChessBoard getBoard() {
		return board;
	}

	/**
	 * 
	 * sets the current ChessBoard to the param
	 * @param board
	 */
	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	/**
	 * @return Player. the white player
	 */
	public Player getWhitePlayer() {
		return whitePlayer;
	}

	/**
	 * @param whitePlayer. Sets the whiteplayer to the param
	 */
	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	/**
	 * @return Player. the black player
	 */
	public Player getBlackPlayer() {
		return blackPlayer;
	}

	/**
	 * @param blackPlayer. Sets the blackplayer to the param
	 */
	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	/**
	 * @param bool. Sets the whether or not a player is looking to draw
	 */
	public void setDraw(boolean bool){
		draw = bool;
	}
	
	/**
	 * @return the current status of draw
	 */
	public boolean getDraw(){
		return draw;
	}
	
	/**
	 * sets whether the game should be ended
	 */
	public void setGameOver(){
		gameOver = true;
	}
	
	/**
	 * @return whether or not the game should be over
	 */
	public boolean getGameOver(){
		return gameOver;
	}

	/**
	 * @return the player that wins
	 */
	public Player getWinner() {
		return Winner;
	}

	/**
	 * @param winner. sets the winning player
	 */
	public void setWinner(Player winner) {
		Winner = winner;
		
	}
	
	/**
	 * switched the player after ecery valid move
	 */
	public void switchPlayer(){
		if(currentMove == whitePlayer){
			currentMove = blackPlayer;
		}else{
			currentMove = whitePlayer;
		}
	}
	
	
}
