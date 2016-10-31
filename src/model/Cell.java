package model;



public class Cell {
	private char color; 
	private <T>  piece; 
	
	public Cell(char color, <T> piece) {
		this.color = color; 
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public char getColor() {
		return color; 
	}
	
	public void setColor(char color) {
		this.color = color;
	}
}
