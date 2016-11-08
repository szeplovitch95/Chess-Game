package model;

import chess.Chess;

public class Knight extends Piece {
	
	public Knight(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		
		int[] coordOg = Chess.stringToCoordinants(origin);
		int[] coordDt = Chess.stringToCoordinants(destination);
		
		// make sure it is a valid L shape
		if((Math.abs(coordOg[0] - coordDt[0]) == 2 && Math.abs(coordOg[1] - coordDt[1]) == 1)
				|| (Math.abs(coordOg[0] - coordDt[0]) == 1 && Math.abs(coordOg[1] - coordDt[1]) == 2)){
			
			// Now have to check if there is a piece of the same color
			if(Board.findPiece(destination) == null){
				return true;
			}else if(Board.findPiece(destination) != null && Board.findPiece(destination).getColor() != this.getColor()){
				return true;
			}
		}
		
		return false;
	}
}
