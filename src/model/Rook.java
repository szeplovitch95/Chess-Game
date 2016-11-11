package model;
import chess.Chess;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public class Rook extends Piece{
	private boolean isMoved;
	
	public Rook(char color, String role, String location) {
		super(color, role, location);
		isMoved = false;
	}
	
	/**
	 * @return boolean. This returns the isMoved variable.
	 */
	public boolean isMoved() {
		return isMoved;
	}

	/**
	 * @param isMoved boolean. Sets the isMoved variable to the param value.
	 */
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
	

	/**
	 * @param origin String
	 * @param destination String
	 * @param Board ChessBoard
	 * @return boolean. This checks the board to see if there are any pieces between the origin piece 
	 * and the destination piece. It will return true if there's no piece in between and will return false 
	 * otherwise.
	 */
	public boolean hasPiecesInBetween(String origin, String destination, ChessBoard Board){
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		// vertical movement
		if(originMoves[1] == destinationMoves[1] && originMoves[0] != destinationMoves[0]) {
			
			// down
			if(originMoves[0] < destinationMoves[0]) {
				for(int i = originMoves[0] + 1; i < destinationMoves[0]; i++) {
					if(Board.board[i][originMoves[1]].getPiece() != null) {
						return false;
					}
				}
			}
			// up
			if(originMoves[0] > destinationMoves[0]) {
				for(int i = originMoves[0] - 1; i > destinationMoves[0]; i--) {
					if(Board.board[i][originMoves[1]].getPiece() != null) {
						return false;
					}
				}
			}
		}
		// horizontal movement
		if(originMoves[1] != destinationMoves[1] && originMoves[0] == destinationMoves[0]) {
			
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
		}
		
		return true;  
	}
}