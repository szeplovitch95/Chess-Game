package model;

import chess.Chess;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public class King extends Piece { 
	private boolean isInCheck;
	
	public King(char color, String role, String location) {
		super(color, role, location);
	}
	/**
	 * @return boolean. Returns the isInCheck variable.
	 */
	public boolean isInCheck() {
		return isInCheck;
	}

	/**
	 * @param isInCheck boolean. Sets the isInCheck variable to the param value.
	 */
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
			
		// check for castling
		}else if(((destination.equals("c1") || destination.equals("g1")) && this.getColor() == 'w') 
				|| ((destination.equals("c8") || destination.equals("g8")) && this.getColor() == 'b')){
			if(destination.charAt(0) == 'c' 
				&& Board.board[Chess.intFromCharInt(destination.charAt(1))][0].getPiece() != null 
				&& Board.board[Chess.intFromCharInt(destination.charAt(1))][0].getPiece().getRole().equals("R")){
				
				Rook temp = (Rook)Board.board[Chess.intFromCharInt(destination.charAt(1))][0].getPiece();
				if(this.isMoved == false && temp.isMoved() == false){
					if(inBetween(coordOg, coordDt, Board) && !isInCheck){
						// set the flag
						Board.setCastled(true);
						return true;
					}
				}
			}else if(destination.charAt(0) == 'g' 
				&& Board.board[Chess.intFromCharInt(destination.charAt(1))][7].getPiece() != null 
				&& Board.board[Chess.intFromCharInt(destination.charAt(1))][7].getPiece().getRole().equals("R")){
				
				Rook temp = (Rook)Board.board[Chess.intFromCharInt(destination.charAt(1))][0].getPiece();
				if(this.isMoved == false && temp.isMoved() == false){
					if(inBetween(coordOg, coordDt, Board)){
						// set the flag
						Board.setCastled(true);
						return true;
					}
				}
			}				
		}
		return false;
	}
	
	/**
	 * 
	 * Method only used when castling. Determines if their are pieces
	 * blocking a king to castle
	 * @param originMoves int[]. Where the path starts
	 * @param destinationMoves int[]. Where the path ends
	 * @param Board ChessBoard. The current board being played on
	 * @return boolean. Whether or not there are pieces in the path
	 */
	public boolean inBetween(int[] originMoves, int[] destinationMoves, ChessBoard Board){
		// right
		if(originMoves[1] < destinationMoves[1]) {
			for(int i = originMoves[1] + 1; i < destinationMoves[1]; i++) {
				if(Board.board[originMoves[0]][i].getPiece() != null) {
					return false;
				}
			}
		}
		// left
		if(originMoves[1] > destinationMoves[1]) {
			for(int i = originMoves[1] - 1; i > destinationMoves[1]; i--) {
				if(Board.board[originMoves[0]][i].getPiece() != null) {
					return false;
				}
			}
		}
		return true;
	}
}