package model;

public class Knight extends Piece {
	
	public Knight(char color, String role) {
		super(color, role);
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
