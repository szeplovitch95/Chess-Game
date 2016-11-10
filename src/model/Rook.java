package model;
import chess.Chess;

public class Rook extends Piece{
	private boolean isMoved;
	
	public Rook(char color, String role, String location) {
		super(color, role, location);
		isMoved = false;
	}
	
	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		
		// check to see if the rook is trying to take a piece of its own color
		if(Board.board[destinationMoves[0]][destinationMoves[1]].getPiece() != null){
			if(Board.board[originMoves[0]][originMoves[1]].getPiece().getColor() ==
						Board.board[destinationMoves[0]][destinationMoves[1]].getPiece().getColor()){
				return false;
			}
		}
		
		// check to see if the rook is going in a straight line
		if(originMoves[0] != destinationMoves[0] && originMoves[1] != destinationMoves[1]){
			return false;
		}
		
		boolean ret = hasPiecesInBetween(origin, destination, Board);
		if(ret == true){
			setMoved(true);
		}
		return ret;
	}
	

	public boolean hasPiecesInBetween(String origin, String destination, ChessBoard Board){
		
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
	
}