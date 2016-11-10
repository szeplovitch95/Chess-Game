package model;
import chess.Chess;

public class Bishop extends Piece {

	public Bishop(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		// check to see if bishop is trying to attack piece of its own color
		if(Board.board[destinationMoves[0]][destinationMoves[1]].getPiece() != null) {
			if(Board.board[originMoves[0]][originMoves[1]].getPiece().getColor() ==
					Board.board[destinationMoves[0]][destinationMoves[1]].getPiece().getColor())
			{
				return false;
			}			
		}
		
		// check to see if bishop is going in a diagonal
		if(Math.abs(originMoves[0] - destinationMoves[0]) != Math.abs(originMoves[1] - destinationMoves[1])){
			return false;
		}

		return hasPiecesInBetween(origin, destination, Board);
	}
	
	public boolean hasPiecesInBetween(String origin, String destination, ChessBoard Board){
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
				for (int i = originMoves[1]; i > destinationMoves[1]; i++) {
					if (Board.board[r][i].getPiece() != null) {
						return false;							
					}
					r--;
				}
			}
			
			// down left
			if (fileDif < 0 && rankDif > 0) {
				r = originMoves[0]  + 1;
				for (int i = originMoves[1] - 1; i < destinationMoves[1]; i--) {
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
