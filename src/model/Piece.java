package model;

public abstract class Piece {
	private String position;
	private char color;
	private String role;
	
	
	public Piece(String position, char color, String role) {
		this.position = position; 
		this.color = color; 
		this.role = role; 
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
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
	
	public abstract boolean isMoveValid(String move);
}
