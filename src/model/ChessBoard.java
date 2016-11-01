package model;

public class ChessBoard {
	private Cell[][] board;
	
	public ChessBoard() {
		board = new Cell[8][8];
		initBoard();
		printBoard();
	}
	
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
	
	//implement this method to find what type of piece is located in the origin.
	private Piece findPiece(String origin) {
		return null;
	}
	
	public void clearBoard() {
		
	}
	

	private void initBlackPieces() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i == 1) {
					board[i][j].setPiece(new Pawn('b', "p"));						
				}
				
				if((i == 0 && j == 0) || (i == 0 && j == 7) ) {
					board[i][j].setPiece(new Rook('b', "R"));
				}
				
				if((i == 0 && j == 1) || (i == 0 && j == 6) ) {
					board[i][j].setPiece(new Knight('b', "N"));
				}
				
				if((i == 0 && j == 2) || (i == 0 && j == 5) ) {
					board[i][j].setPiece(new Bishop('b', "B"));
				}
				
				if((i == 0 && j == 3)) {
					board[i][j].setPiece(new Queen('b', "Q"));
				}
				
				if((i == 0 && j == 4)) {
					board[i][j].setPiece(new King('b', "K"));
				}
			}
		}
	}
	
	private void initWhitePieces() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i == 6) {
					board[i][j].setPiece(new Pawn('w', "p"));						
				}
				
				if((i == 7 && j == 0) || (i == 7 && j == 7) ) {
					board[i][j].setPiece(new Rook('w', "R"));
				}
				
				if((i == 7 && j == 1) || (i == 7 && j == 6) ) {
					board[i][j].setPiece(new Knight('w', "N"));
				}
				
				if((i == 7 && j == 2) || (i == 7 && j == 5) ) {
					board[i][j].setPiece(new Bishop('w', "B"));
				}
				
				if((i == 7 && j == 3)) {
					board[i][j].setPiece(new King('w', "K"));
				}
				
				if((i == 7 && j == 4)) {
					board[i][j].setPiece(new Queen('w', "Q"));
				}
			}
		}
	}
	
	public boolean movePiece(String origin, String dest) {
		return false;
	}
	
	public boolean promotePawn(String destination) {
		return false;
	}
	
	
	public void printBoard() {
		// Add the initial blank like
		
		System.out.println();
		
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