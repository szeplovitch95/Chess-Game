package model;
import chess.Chess;

public class ChessBoard {
	public Cell[][] 		board;
	private Piece 			EnPassantEligible;
	private boolean 		castled;
	
	public ChessBoard() {
		board = new Cell[8][8];
		castled = false;
		initBoard();
	}
	
	
	/**
	 * Initializes the board
	 */
	private void initBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i == 1 || i == 3 || i == 5 || i == 7) {
					if(j % 2 == 0) {
						board[i][j] = new Cell('b', "##");						
					} else {
						board[i][j] = new Cell('w', "  ");
					}
				} else {
					if(j % 2 == 0) {
						board[i][j] = new Cell('w', "  ");						
					} else {
						board[i][j] = new Cell('b', "##");
					}
				}
				
					
			}
		}
		
		initBlackPieces();
		initWhitePieces();	
	}
	
	/**
	 * @param location. String of the coordinates on the board
	 * @return Cell. the cell at the given coordinates
	 */
	public Cell findCell(String location){
		int coord[] = Chess.stringToCoordinants(location);
		return board[coord[0]][coord[1]];
	}
	
	/**
	 * @param origin String of the location of piece were looking for
	 * @return the piece at that location. null if there is none
	 */
	public Piece findPiece(String origin) {
		int coord[] = Chess.stringToCoordinants(origin);
		return board[coord[0]][coord[1]].getPiece();
	}
	
	/**
	 * 
	 * method that clears a cell when a piece gets taken
	 * @param location. String of the location we want to clear
	 */
	public void clearCell(String location) {
		int coord[] = Chess.stringToCoordinants(location);
		board[coord[0]][coord[1]].setPiece(null);	
	}
	
	/**
	 * 
	 * method that clears a cell when a piece gets taken
	 * @param coord. int[][] coordinates of the location
	 */
	public void clearCell(int[] coord){
		board[coord[0]][coord[1]].setPiece(null);
	}
	

	/**
	 * initializes all the black piece at the start of the game
	 */
	private void initBlackPieces() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i == 1) {
					board[i][j].setPiece(new Pawn('b', "p", Chess.coordinatesToString(i, j)));	
				}
				
				if((i == 0 && j == 0) || (i == 0 && j == 7) ) {
					board[i][j].setPiece(new Rook('b', "R", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 0 && j == 1) || (i == 0 && j == 6) ) {
					board[i][j].setPiece(new Knight('b', "N", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 0 && j == 2) || (i == 0 && j == 5) ) {
					board[i][j].setPiece(new Bishop('b', "B", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 0 && j == 3)) {
					board[i][j].setPiece(new Queen('b', "Q", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 0 && j == 4)) {
					board[i][j].setPiece(new King('b', "K", Chess.coordinatesToString(i, j)));					
				}

			}
		}
	}
	
	/**
	 * initializes all the white pieces at the start of the game
	 */
	private void initWhitePieces() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i == 6) {
					board[i][j].setPiece(new Pawn('w', "p", Chess.coordinatesToString(i, j)));						
				}
				
				if((i == 7 && j == 0) || (i == 7 && j == 7) ) {
					board[i][j].setPiece(new Rook('w', "R", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 7 && j == 1) || (i == 7 && j == 6) ) {
					board[i][j].setPiece(new Knight('w', "N", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 7 && j == 2) || (i == 7 && j == 5) ) {
					board[i][j].setPiece(new Bishop('w', "B", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 7 && j == 4)) {
					board[i][j].setPiece(new King('w', "K", Chess.coordinatesToString(i, j)));
				}
				
				if((i == 7 && j == 3)) {
					board[i][j].setPiece(new Queen('w', "Q", Chess.coordinatesToString(i, j)));
				}
			}
		}
	}
	
	/**
	 * 
	 * Method that moves a piece from origin to dest
	 * @param origin String of the initial location of the piece to be moved
	 * @param dest String of the place that piece is going to
	 * @return boolean 
	 */
	public boolean movePiece(String origin, String dest) {
		int[] orgCoord = Chess.stringToCoordinants(origin);
		int[] destCoord = Chess.stringToCoordinants(dest);
		Piece temp = board[orgCoord[0]][orgCoord[1]].getPiece();
		board[destCoord[0]][destCoord[1]].setPiece(temp);
		board[destCoord[0]][destCoord[1]].getPiece().setLocation(dest);
		board[orgCoord[0]][orgCoord[1]].setPiece(null);
		return true;
	
	}
	
	public boolean movePiece(int[] orgCoord,int[] destCoord) {
		Piece temp = board[orgCoord[0]][orgCoord[1]].getPiece();
		board[destCoord[0]][destCoord[1]].setPiece(temp);
		board[destCoord[0]][destCoord[1]].getPiece().setLocation(Chess.coordinatesToString(destCoord[0], destCoord[1]));
		board[orgCoord[0]][orgCoord[1]].setPiece(null);
		return true;
	
	}
	
	/**
	 * Method that prints the board to the console after every valid move
	 */
	public void printBoard() {
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].getPiece() == null) {
					
					// we dont want leading spaces
					if(j == 0){
						System.out.print(board[i][j].getEmpty());
					}else{
						System.out.print(" " + board[i][j].getEmpty());
					}
					
				} else if(board[i][j].getPiece() != null){
					// we dont want leading spaces
					if(j == 0){
						System.out.print(board[i][j].getPiece().getColor() + board[i][j].getPiece().getRole());
					}else{
						System.out.print( " " + board[i][j].getPiece().getColor() + board[i][j].getPiece().getRole());
					}
				// Print the numbers at the end of the board
				} if(j == 7 ){
					System.out.print(" " + (8 - i));
				}
			}
			// add the letters at the bottom
			if(i == 7 ){
				System.out.println();
				System.out.print(" a  b  c  d  e  f  g  h");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * Method that promotes a pawn
	 * @param destination String of the location of the pawn
	 * @param newRole char the new role that the pawn will be promoted to
	 */
	public void promotePawn(String destination, char newRole) {
		if(newRole == 'R'){
			char color = findCell(destination).getPiece().getColor();
			findCell(destination).setPiece(new Rook(color, "R", destination));
		}else if(newRole == 'N'){
			char color = findCell(destination).getPiece().getColor();
			findCell(destination).setPiece(new Knight(color, "N", destination));
		}else if(newRole == 'B'){
			char color = findCell(destination).getPiece().getColor();
			findCell(destination).setPiece(new Bishop(color, "B", destination));
		}else if(newRole == 'Q'){
			char color = findCell(destination).getPiece().getColor();
			findCell(destination).setPiece(new Queen(color, "Q", destination));
		}
	}
	
	/**
	 * @return Piece. Returns which piece on the board is en passant eligible
	 */
	public Piece getEnPassantEligible(){
		return EnPassantEligible;
	}
	
	/**
	 * @param pawn Piece that will be en passant eligible
	 */
	public void setEnPassantEligible(Piece pawn){
		EnPassantEligible = pawn;
	}
	
	/**
	 * after the next move, all pieces become ineligible to en passant
	 */
	public void clearEnPassant(){
		EnPassantEligible = null;
	}
	
	public void setCastled(boolean in){
		castled = in;
	}
	
	public boolean getCastled(){
		return castled;
	}
	
	/*
	 * Checks user's input ('e5 e6') to make sure it is a valid input and located on the board. 
	 * For Example: 'z7 y4' is invalid and the program should print an error to the user 
	 * On success: method should call the findPiece method to find the type of the piece and then 
	 * call the appropriate piece's isMoveValid method to verify the move is valid. 
	 */
	
	public boolean validateMove(String origin, String destination) {
		return false;
	}
}