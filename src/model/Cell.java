package model;

public class Cell {
	private char color; 
	private Piece piece;	
	private String empty;
	
	public Cell(char color, String empty) {
		this.empty = empty;
		this.color = color;
	}
	
	public Cell(char color, Piece piece) {
		this.color = color; 
		this.piece = piece;
	}
	
	public boolean isEmpty(){
		if(piece == null){
			return true;
		}else{
			return false;
		}
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
	
	public String getEmpty() {
		return empty;
	}
	
	public void setEmpty(String empty) {
		this.empty = empty;
	}

}
