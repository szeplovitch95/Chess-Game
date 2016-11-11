package model;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public class Player {
	private String[] playerMoves;
	private char playerColor; 
	
	public Player() {} 
	
	public Player(char playerColor) {
		this.playerColor = playerColor; 
	}

	/**
	 * @return String[]. This returns the playerMoves variable.
	 */
	public String[] getPlayerMoves() {
		return playerMoves;
	}

	/**
	 * @param playerMoves. Sets the playerMoves variable to the param value.
	 */
	public void setPlayerMoves(String[] playerMoves) {
		this.playerMoves = playerMoves;
	}

	/**
	 * @return char. This returns the playerColor variable.
	 */
	public char getPlayerColor() {
		return playerColor;
	}

	/**
	 * @param playerColor char. This sets the playerColor variable to the param value. 
	 */
	public void setPlayerColor(char playerColor) {
		this.playerColor = playerColor;
	}
}
