package model;

public interface moveHelper {
		default public boolean hasPiecesInbetween(String origin, String dest, Cell[][] board) {
			return true;
		}
}
