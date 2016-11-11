package model;

/**
 * @author Shachar Zeplovitch
 * @author Christopher McDonough
 */
public interface moveHelper {
		default public boolean hasPiecesInbetween(String origin, String dest, Cell[][] board) {
			return true;
		}
}
