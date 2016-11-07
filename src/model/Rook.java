package model;

public class Rook extends Piece {
	private boolean isMoved;
	
	public Rook(char color, String role, String location) {
		super(color, role, location);
	}
	
	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard board) {
		return false;
	}
}