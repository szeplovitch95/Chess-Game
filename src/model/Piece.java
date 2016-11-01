package model;

public abstract class Piece {
	private char color;
	private String role;
	private String symbol;

	public Piece(char color, String role) {
		this.color = color; 
		this.role = role; 
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public char getColor() {
		return color;
	}
	
	public void setColor(char color) {
		this.color = color;
	}
	
	public abstract boolean isMoveValid(String origin, String destination);
}
