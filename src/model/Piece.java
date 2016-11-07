package model;

public abstract class Piece {
	private char color;
	private String role;
	private String location;

	public Piece(char color, String role, String location) {
		this.color = color; 
		this.role = role; 
		this.location = location;
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
	
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String loc){
		this.location = loc;
	}
	
	public abstract boolean isMoveValid(String origin, String destination, ChessBoard board);

}
