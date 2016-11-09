package model;
import chess.Chess;

public class Bishop extends Piece implements moveHelper{

	public Bishop(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard Board) {
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		if(Board.board[destinationMoves[0]][destinationMoves[1]].getPiece() != null) {
			if(Board.board[originMoves[0]][originMoves[1]].getPiece().getColor() ==
					Board.board[destinationMoves[0]][destinationMoves[1]].getPiece().getColor())
			{
				return false;
			}			
		}

		return hasPiecesInbetween(origin, destination, Board.board);
	}
}
