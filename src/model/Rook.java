package model;

public class Rook extends Piece {
	private boolean isMoved;
	
	public Rook(char color, String role) {
		super(color, role);
	}
	
	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}