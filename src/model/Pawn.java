package model;

import chess.Chess;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public class Pawn extends Piece {
	private boolean enPassant;
	private boolean moved;
	
	public Pawn(char color, String role, String location) {
		super(color, role, location);
		this.moved = false;
		this.enPassant = false;
	}
	
	/**
	 * @return boolean. This returns the enPassant variable.
	 */
	public boolean isEnPassant() {
		return enPassant;
	}
	
	/**
	 * @param in boolean. Sets the enPassant variable to the param value.
	 */
	public void setEnPassant(boolean in) {
		this.enPassant = in;
	}
	
	/**
	 * @return boolean. This returns the moved variable.
	 */
	public boolean hasMoved(){
		return moved;
	}
	
	/**
	 * Sets the moved variable to true.
	 */
	public void setMoved(){
		moved = true;
	}
	
	/**
	 * @param move String
	 */
	public void setPromotion(String move){
		
	}
	

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		
		Piece temp = Board.findPiece(origin);
		char color = temp.getColor();
		
		// make sure we are moving forward
		if(color == 'w'){
			if((int)origin.charAt(1) > (int)destination.charAt(1)){
				return false;
			}
		}else if(color == 'b'){
			if((int)origin.charAt(1) < (int)destination.charAt(1)){
				return false;
			}
		}
		if(origin.charAt(0) == destination.charAt(0)){
			// non attack move
			
			// check how many space we are moving
			// IS GETTING THE ASCII CODE MIGHT COME BACK TO BE A PROBLEM
			int spaces = Math.abs(((int)origin.charAt(1) - (int)destination.charAt(1)));
			if(spaces > 2){
				return false;
			}
			if(spaces == 1){
				// check to see if a piece is blocking
				if(Board.findPiece(destination) != null){
					return false;
				}
			}else if(spaces == 2){
				if(hasMoved()){
					return false;
				}else if(Board.findPiece(destination) != null){
					return false;
				}
				// need to check the space in between
				int[] orgnCd = Chess.stringToCoordinants(origin);
				int[] destCd = Chess.stringToCoordinants(destination);
				int space = (orgnCd[0] + destCd[0]) / 2;
				if(!Board.board[space][Chess.intFromLetter(origin.charAt(0))].isEmpty()){
					return false;
				}
				Board.setEnPassantEligible(this);
			}
		}else if((origin.charAt(0)+1 == destination.charAt(0) || origin.charAt(0)-1 == destination.charAt(0))
				&& (origin.charAt(1)+1 == destination.charAt(1) || origin.charAt(1)-1 == destination.charAt(1))){
			// Attack Move
			// check if there is an enemy piece at the destination or if there is one next to it en passant ready
			if(Board.findPiece(destination) == null){
				// must have a pawn next to it that is en passant ready
				
				if(Board.board[Chess.intFromCharInt(origin.charAt(1))][Chess.intFromLetter(destination.charAt(0))].getPiece() != null){
					// theres a piece in the right spot
					if(Board.board[Chess.intFromCharInt(origin.charAt(1))][Chess.intFromLetter(destination.charAt(0))].getPiece().getRole().equals("p")){
						Pawn tempPiece = (Pawn)Board.board[Chess.intFromCharInt(origin.charAt(1))][Chess.intFromLetter(destination.charAt(0))].getPiece();
						if(tempPiece.getLocation() == Board.getEnPassantEligible().getLocation()){
							//System.out.println(tempPiece.getLocation());
							Board.clearCell(tempPiece.getLocation());
							Board.clearEnPassant();
							return true;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else if(Board.findPiece(destination).getColor() == Board.findPiece(origin).getColor()){
				// pawn is trying to attack its own color piece
				return false;
				
			}else{
				// attack move
				return true;
			}
		}else{
			// user inputed something not valid
			return false;
		}
		if(isEnPassant() && hasMoved()){
			// no longer en passant ready
			setEnPassant(false);
		}
		if(!hasMoved()){
			setMoved();
		}
		return true;
	}
}
