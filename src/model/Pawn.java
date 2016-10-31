package model;

public class Pawn extends Piece {
	private boolean enPassing;
	
	public Pawn(char color, String role) {
		super(color, role);
	}
	
	public boolean isEnPassing() {
		return enPassing;
	}
	
	public void setEnPassing(boolean enPassing) {
		this.enPassing = enPassing;
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
