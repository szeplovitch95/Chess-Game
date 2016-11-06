package model;

public class Queen extends Piece {

	public Queen(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
