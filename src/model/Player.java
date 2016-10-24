package model;

public class Player {
	private String[] playerMoves;
	private char playerColor; 
	
	public Player() {} 
	
	public Player(char playerColor) {
		this.playerColor = playerColor; 
	}

	public String[] getPlayerMoves() {
		return playerMoves;
	}

	public void setPlayerMoves(String[] playerMoves) {
		this.playerMoves = playerMoves;
	}

	public char getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(char playerColor) {
		this.playerColor = playerColor;
	}
}
