package model;

public class King extends Piece {
	private boolean isMoved; 
	private boolean isInCheck;
	
	public King(char color, String role) {
		super(color, role);
	}

	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	public boolean isInCheck() {
		return isInCheck;
	}

	public void setInCheck(boolean isInCheck) {
		this.isInCheck = isInCheck;
	}
	
	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}