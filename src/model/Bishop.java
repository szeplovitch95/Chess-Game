package model;

public class Bishop extends Piece {

	public Bishop(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
