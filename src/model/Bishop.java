package model;

public class Bishop extends Piece {

	public Bishop(char color, String role) {
		super(color, role);
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
