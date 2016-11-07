package model;

public class Knight extends Piece {
	
	public Knight(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard board) {
		return false;
	}
}
