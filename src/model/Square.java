package model;

public class Square {
	private String letterPos; 
	private String numberPos; 
	private Piece piece; 
	private boolean isOccupied;
	
	public Square() {
		
	}
	
	public Square(String letterPos, String numberPos, Piece piece) {
		this.letterPos = letterPos; 
		this.numberPos = numberPos; 
		this.piece = piece;
	}

	public String getLetterPos() {
		return letterPos;
	}

	public void setLetterPos(String letterPos) {
		this.letterPos = letterPos;
	}

	public String getNumberPos() {
		return numberPos;
	}

	public void setNumberPos(String numberPos) {
		this.numberPos = numberPos;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
}
