package model;

public class ChessBoard {
	private Cell[][] cells;
	
	public ChessBoard() {
		cells = new Cell[8][8];
		initBoard();
		printBoard();
	}
	
	private void initBoard() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				if(i == 1 || i == 3 || i == 5 || i == 7) {
					if(j % 2 == 0) {
						cells[i][j] = new Cell('b', "##");						
					} else {
						cells[i][j] = new Cell('w', "##");
					}
				} else {
					if(j % 2 == 0) {
						cells[i][j] = new Cell('w', "##");						
					} else {
						cells[i][j] = new Cell('b', "##");
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
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				if(i == 1) {
						cells[i][j].setPiece(new Pawn('b', "P"));						
				}
				
				if((i == 0 && j == 0) || (i == 0 && j == 7) ) {
					cells[i][j].setPiece(new Rook('b', "R"));
				}
				
				if((i == 0 && j == 1) || (i == 0 && j == 6) ) {
					cells[i][j].setPiece(new Knight('b', "N"));
				}
				
				if((i == 0 && j == 2) || (i == 0 && j == 5) ) {
					cells[i][j].setPiece(new Bishop('b', "B"));
				}
				
				if((i == 0 && j == 3)) {
					cells[i][j].setPiece(new Queen('b', "Q"));
				}
				
				if((i == 0 && j == 4)) {
					cells[i][j].setPiece(new King('b', "K"));
				}
			}
		}
	}
	
	private void initWhitePieces() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				if(i == 6) {
						cells[i][j].setPiece(new Pawn('w', "P"));						
				}
				
				if((i == 7 && j == 0) || (i == 7 && j == 7) ) {
					cells[i][j].setPiece(new Rook('w', "R"));
				}
				
				if((i == 7 && j == 1) || (i == 7 && j == 6) ) {
					cells[i][j].setPiece(new Knight('w', "N"));
				}
				
				if((i == 7 && j == 2) || (i == 7 && j == 5) ) {
					cells[i][j].setPiece(new Bishop('w', "B"));
				}
				
				if((i == 7 && j == 3)) {
					cells[i][j].setPiece(new King('w', "K"));
				}
				
				if((i == 7 && j == 4)) {
					cells[i][j].setPiece(new Queen('w', "Q"));
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
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				if(cells[i][j].getPiece() == null && cells[i][j].getColor() == 'b') {
					System.out.print(" " + cells[i][j].getEmpty());
				} else if(cells[i][j].getPiece() != null){
					System.out.print( " " + cells[i][j].getColor() + cells[i][j].getPiece().getRole());
				} else {
					System.out.print("   ");
				}
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