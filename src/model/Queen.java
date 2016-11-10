package model;

import chess.Chess;

public class Queen extends Piece {

	public Queen(char color, String role, String location) {
		super(color, role, location);
	}

	@Override
	public boolean isMoveValid(String origin, String destination, ChessBoard board) {
		int[] originMoves = Chess.stringToCoordinants(origin);
		int[] destinationMoves = Chess.stringToCoordinants(destination);
		
		if(originMoves[1] == destinationMoves[1] || originMoves[0] == destinationMoves[0])
		return false;
	}
}
