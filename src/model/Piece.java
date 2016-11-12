package model;

import java.util.ArrayList;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public abstract class Piece {
	private char color;
	private String role;
	private String location;
	public boolean isMoved;

	public Piece(char color, String role, String location) {
		this.color = color; 
		this.role = role; 
		this.location = location;
		this.isMoved = false;
	}

	/**
	 * @return String. This returns the role variable.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role String. This sets the role variable to the param value.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return char. This returns the color variable.
	 */
	public char getColor() {
		return color;
	}
	
	/**
	 * @param color. This sets the color variable to the param value.
	 */
	public void setColor(char color) {
		this.color = color;
	}
	
	/**
	 * @return String. This returns the location variable. 
	 */
	public String getLocation(){
		return location;
	}

	/**
	 * @param loc. This sets the loc variable to the param value.
	 */
	public void setLocation(String loc){
		this.location = loc;
	}
	
	/**
	 * @return boolean. This returns the isMoved variable.
	 */
	public boolean isMoved() {
		return isMoved;
	}

	/**
	 * @param isMoved boolean. Sets the isMoved variable to the param value.
	 */
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}
	
	/**
	 * @param origin String. 
	 * @param destination
	 * @param board
	 * @return boolean. This is the abstract method of the Piece class which will return true if the 
	 * the move the user is tryin to make is valid and would return false otherwise.
	 */
	public abstract boolean isMoveValid(String origin, String destination, ChessBoard board);

}
