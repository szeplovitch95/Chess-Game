package model;

public class Queen extends Piece {

	public Queen(char color, String role) {
		super(color, role);
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
