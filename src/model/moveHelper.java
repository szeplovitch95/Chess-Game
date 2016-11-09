package model;

import chess.Chess;

public interface moveHelper {
		default public boolean hasPiecesInbetween(String origin, String dest, Cell[][] board) {
			int[] originMoves = Chess.stringToCoordinants(origin);
			int[] destinationMoves = Chess.stringToCoordinants(dest);
			int spaces = Math.abs(((int)origin.charAt(1) - (int)dest.charAt(1)));
			
			//first check to see if the origin and dest pieces have the same color and return false if they are.
			//check destination piece is not the same players 
			// check nothing is on the way to destination
			
			
			// originMoves[7][0] = wR 
			//destinationMoves[1][0] = bp
			// Vertical move = originMoves[1] = destinationMoves[1] 
			//Horizontal Move = originMoves[0] = destinationMoves[0]
			//see if its vertical move
			
			if(originMoves[1] == destinationMoves[1] && originMoves[0] != destinationMoves[0]) {
				//upward movement
				if(originMoves[0] < destinationMoves[0]) {
					for(int i = originMoves[0] + 1; i < destinationMoves[0]; i++) {
						if(board[i][originMoves[1]].getPiece() != null) {
							return false;
						}
					}
				}
				
				//downward movement
				if(originMoves[0] > destinationMoves[0]) {
					for(int i = originMoves[0] - 1; i > destinationMoves[0]; i--) {
						if(board[i][originMoves[1]].getPiece() != null) {
							return false;
						}
					}
				}
			}
			
			if(originMoves[1] != destinationMoves[1] && originMoves[0] == destinationMoves[0]) {
				if(originMoves[1] < destinationMoves[1]) {
					for(int i = originMoves[1] + 2; i < destinationMoves[0]; i++) {
						if(board[i][originMoves[0]].getPiece() != null) {
							return false;
						}
					}
				}
				
				if(originMoves[1] > destinationMoves[1]) {
					for(int i = originMoves[1]; i > destinationMoves[0]; i--) {
						if(board[i][originMoves[0]].getPiece() != null) {
							return false;
						}
					}
				}
			}
			
			//inrank = originMoves[1]
			//finRank = destinationMoves[1]
			//initFile = originMoves[0]
			// finalFile = destinationMoves[1]
			
			int r;		
			int rankDif = originMoves[1] - destinationMoves[1];
			int fileDif = originMoves[0] - destinationMoves[0];
			if (Math.abs(rankDif) == Math.abs(fileDif)) {
				// up right if both quantities are negative
				
				if (fileDif < 0 && rankDif < 0) {
					r = originMoves[0] + 1;
					for (int i = originMoves[1] + 2; i < destinationMoves[0]; i++) {
						if (board[r++][i].getPiece() != null) {							
							return false;
						}
					}
				}

				if (fileDif > 0 && rankDif < 0) {
					r = originMoves[0] + 1;
					for (int i = originMoves[1]; i > destinationMoves[0]; i--) {
						if (board[r++][i].getPiece() != null) {
							return false;							
						}
					}
				}

				if (fileDif < 0 && rankDif > 0) {
					r = originMoves[0]  + 1;
					for (int i = originMoves[1] + 2; i < destinationMoves[0]; i++) {
						if (board[r++][i].getPiece() != null) {
							return false;
						}
						r--;
					}
				}

				if (fileDif > 0 && rankDif > 0) {
					r= originMoves[0]  + 1;
					for (int i = originMoves[1]; i > destinationMoves[0]; i--) {
						if (board[r++][i].getPiece() != null)
							return false;
						r--;
					}
				}
			}
			
			return true;
		}
}
