package model;
import chess.Chess;

public class Rook extends Piece implements moveHelper{
	private boolean isMoved;
	
	public Rook(char color, String role, String location) {
		super(color, role, location);
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
		
		
		if(Board.board[originMoves[0]][originMoves[1]].getPiece().getColor() ==
						Board.board[destinationMoves[0]][destinationMoves[1]].getPiece().getColor())
		{
			return false;
		}
		
		
		
		return hasPiecesInbetween(origin, destination, Board.board);
	}
}