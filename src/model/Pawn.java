package model;

public class Pawn extends Piece {
	private boolean enPassing;
	
	public Pawn(char color, String role, String location) {
		super(color, role, location);
	}
	
	public boolean isEnPassing() {
		return enPassing;
	}
	
	public void setEnPassing(boolean enPassing) {
		this.enPassing = enPassing;
	}
	
	public void setPromotion(String move){
		
	}

	@Override
	public boolean isMoveValid(String origin, String destination) {
		return false;
	}
}
