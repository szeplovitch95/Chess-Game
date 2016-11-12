package model;

import chess.Chess;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public class Queen extends Piece {

	public Queen(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard board) {
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		// check to see if the rook is trying to take a piece of its own color
		if(board.board[destinationMoves[0]][destinationMoves[1]].getPiece() != null){
			if(board.board[originMoves[0]][originMoves[1]].getPiece().getColor() ==
						board.board[destinationMoves[0]][destinationMoves[1]].getPiece().getColor()){
				return false;
			}
		}
		
		if((originMoves[0] == destinationMoves[0] && originMoves[1] != destinationMoves[1])
				|| (originMoves[0] != destinationMoves[0] && originMoves[1] == destinationMoves[1])){
			// Movement is straight (like rook)
			return hasPiecesInBetweenStraight(origin, destination, board);
			
		}else if(Math.abs(originMoves[0] - destinationMoves[0]) == Math.abs(originMoves[1] - destinationMoves[1])){
			// movement is diagonal (like bishop)
			return hasPiecesInBetweenDiagonal(origin, destination, board);
		}else{
			return false;
		}
	}
	
	
	/**
	 * 
	 * Method to determine if there are piece obstructing the move.
	 * Straight lines only
	 * 
	 * @param origin
	 * @param destination
	 * @param Board
	 * @return boolean whether there any pieces in between origin and destination
	 */
	public boolean hasPiecesInBetweenStraight(String origin, String destination, ChessBoard Board){
		
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		if(originMoves[1] == destinationMoves[1] && originMoves[0] != destinationMoves[0]) {
			//upward movement
			if(originMoves[0] < destinationMoves[0]) {
				for(int i = originMoves[0] + 1; i < destinationMoves[0]; i++) {
					if(Board.board[i][originMoves[1]].getPiece() != null) {
						return false;
					}
				}
			}
			
			//downward movement
			if(originMoves[0] > destinationMoves[0]) {
				for(int i = originMoves[0] - 1; i > destinationMoves[0]; i--) {
					if(Board.board[i][originMoves[1]].getPiece() != null) {
						return false;
					}
				}
			}
		}
		
		if(originMoves[1] != destinationMoves[1] && originMoves[0] == destinationMoves[0]) {
			if(originMoves[1] < destinationMoves[1]) {
				for(int i = originMoves[1] + 2; i < destinationMoves[0]; i++) {
					if(Board.board[i][originMoves[0]].getPiece() != null) {
						return false;
					}
				}
			}
			
			if(originMoves[1] > destinationMoves[1]) {
				for(int i = originMoves[1]; i > destinationMoves[0]; i--) {
					if(Board.board[i][originMoves[0]].getPiece() != null) {
						return false;
					}
				}
			}
		}
		return true;  
		
	}
	
	/**
	 * 
	 * Method to determine if there are piece obstructing the move.
	 * Diagonal lines only
	 * 
	 * @param origin
	 * @param destination
	 * @param Board
	 * @return boolean whether there any pieces in between origin and destination
	 */
	public boolean hasPiecesInBetweenDiagonal(String origin, String destination, ChessBoard Board){
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		int r;		
		int rankDif = originMoves[1] - destinationMoves[1];
		int fileDif = originMoves[0] - destinationMoves[0];
		if (Math.abs(rankDif) == Math.abs(fileDif)) {
			
			// down and right
			if (fileDif < 0 && rankDif < 0) {
				r = originMoves[0] + 1;
				for (int i = originMoves[1] + 1; i < destinationMoves[1]; i++) {
					if (Board.board[r][i].getPiece() != null) {							
						return false;
					}
					r++;
				}
			}
			
			// up right
			if (fileDif > 0 && rankDif < 0) {
				r = originMoves[0] - 1;
				for (int i = originMoves[1] + 1; i < destinationMoves[1]; i++) {
					if (Board.board[r][i].getPiece() != null) {
						return false;							
					}
					r--;
				}
			}
			
			// down left
			if (fileDif < 0 && rankDif > 0) {
				r = originMoves[0]  + 1;
				for (int i = originMoves[1] - 1; i > destinationMoves[1]; i--) {
					if (Board.board[r][i].getPiece() != null) {
						return false;
					}
					r++;
				}
			}

			// up and left
			if (fileDif > 0 && rankDif > 0) {
				r= originMoves[0] - 1;
				for (int i = originMoves[1] - 1; i > destinationMoves[1]; i--) {
					if (Board.board[r][i].getPiece() != null){
						return false;
					}
					r--;
				}
			}
		}
		
		return true;
	}
}
