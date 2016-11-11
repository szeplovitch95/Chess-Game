package model;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
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
	
	/**
	 * @return boolean This checks if the piece is equal to null. 
	 */
	public boolean isEmpty(){
		if(piece == null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @return Piece This is the getter method for the piece variable.
	 */
	public Piece getPiece() {
		return piece;
	}


	/**
	 * @param piece Piece. This sets the piece to the param value.
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	/**
	 * @return Piece. This is the getter method for the piece variable.
	 */
	public char getColor() {
		return color; 
	}
	
	/**
	 * @param color Color. This sets the color to the param value.
	 */
	public void setColor(char color) {
		this.color = color;
	}
	
	/**
	 * @return String. This is the getter method for the empty variable.
	 */
	public String getEmpty() {
		return empty;
	}
	
	/**
	 * @param empty String. This sets the empty variable to the param value.
	 */
	public void setEmpty(String empty) {
		this.empty = empty;
	}
}
