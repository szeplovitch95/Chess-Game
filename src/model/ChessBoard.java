package model;

public class ChessBoard {
	private Cell[][] cells;
	
	public ChessBoard() {
		initBoard();
		printBoard();
	}
	
	private void initBoard() {
		cells = new Cell[7][7];
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[j].length; j++) {
				
				if(i == 1 || i == 6) {
					cells[i][j].setPiece(new Pawn('w', "Pawn"));
				}
				
				if((i == 0 && j == 0) || (i == 0 && j == 7) ) {
					cells[i][j] = new Cell("w", new Rook('w', "Rook"));
					cells[i][j].setColor('w');
					cells[i][j].setPiece(new Rook('w', "Rook"));
				}
				
				if((i == 0 && j == 1) || (i == 0 && j == 6) ) {
					cells[i][j].setColor('b');
					cells[i][j].setPiece(new Knight('w', "Knight"));
				}
				
				if((i == 0 && j == 2) || (i == 0 && j == 5) ) {
					cells[i][j].setColor('w');
					cells[i][j].setPiece(new Bishop('w', "Bishop"));
				}
				
				if((i == 0 && j == 3)) {
					cells[i][j].setColor('b');
					cells[i][j].setPiece(new Queen('w', "Queen"));
				}
				
				if((i == 0 && j == 4)) {
					cells[i][j].setColor('w');
					cells[i][j].setPiece(new King('w', "King"));
				}
			}
			
		}
		
		
	}
	
	//implement this method to find what type of piece is located in the origin.
	private Piece findPiece(String origin) {
		return null;
	}
	
	public void clearBoard() {
		
	}
	
	public boolean movePiece(String origin, String dest) {
		return false;
	}
	
	public boolean promotePawn(String destination) {
		return false;
	}
	
	
	public void printBoard() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[j].length; j++) {
				System.out.print( " " + cells[i][j].getPiece().getRole());
			}
			System.out.println();
		}
	}
	
	/*
	 * Checks user's input ('e5 e6') to amke sure it is a valid input and located on the board. 
	 * For Example: 'z7 y4' is invalid and the program should print an error to the user 
	 * On success: method should call the findPiece method to find the type of the piece and then 
	 * call the appropriate piece's isMoveValid method to verify the move is valid. 
	 */
	
	public boolean validateMove(String origin, String destination) {
		return false;
	}
}