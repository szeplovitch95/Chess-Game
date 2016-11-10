package model;

import chess.Chess;

public class King extends Piece {
	private boolean isMoved; 
	private boolean isInCheck;
	
	public King(char color, String role, String location) {
		super(color, role, location);
		isMoved = false;
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
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		int[] coordOg = Chess.stringToCoordinants(origin);
		int[] coordDt = Chess.stringToCoordinants(destination);
		
		// check to see if theres a piece of the same color at the destination
		if(Board.board[coordDt[0]][coordDt[1]].getPiece() != null){
			if(Board.board[coordOg[0]][coordOg[1]].getPiece().getColor() ==
						Board.board[coordDt[0]][coordDt[1]].getPiece().getColor()){
				return false;
			}
		}
		
		// check to see if only one move
		if((coordOg[0] == coordDt[0] && Math.abs(coordOg[1] - coordDt[1]) == 1)
				|| (Math.abs(coordOg[0] - coordDt[0]) == 1 && coordOg[1] == coordDt[1])
				|| (Math.abs(coordOg[0] - coordDt[0]) == 1 && Math.abs(coordOg[1] - coordDt[1]) == 1)){
			
			setMoved(true);
			return true;
		}else if(false){
			// check for castling***
		}
		return false;
	}
}