package model;

public class King extends Piece {

	
	public King(String position, char color, String role) {
		super(position, color, role);
	}

	//Implement Kings move method
	
	@Override
	public boolean isMoveValid(String move) {
		return false;
	}

}
