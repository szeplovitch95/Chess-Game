package chess;
import model.*;

public class Chess {
	public static ChessGame cg;
	
	public static void main(String[] args) {
		
		cg = new ChessGame(new ChessBoard(), new Player('w') , new Player('b'));
		
	}

	/**
	 * 
	 * Helper method for array to input conversion
	 * @param in
	 * @return int that represents the char  
	 */
	public static int intFromCharInt(char in){
		int temp = (int)(8-(in -48));
		return temp;
	}
	
	/**
	 * 
	 * Helper method for array to input conversion
	 * @param in
	 * @return int that represents the char 
	 */
	public static int intFromLetter(char in){
		int temp = (int)(in - 97);
		return temp;
	}
	
	/**
	 * 
	 * Helper method for array to input conversion
	 * @param in
	 * @return char that represents the int
	 */
	public static char letterFromInt(int in){
		char temp = (char)(in + 97);
		return temp;
	}
	
	/**
	 * 
	 * Helper method for array to input conversion
	 * @param row
	 * @param column
	 * @return String. user input friendly
	 */
	public static String coordinatesToString(int row, int column){
		char col = letterFromInt(column);
		String ret = "" + col + (8 - row);
		return ret;
	}
	/**
	 * 
	 * Helper method for array to input conversion
	 * @param move
	 * @return int[] with coordinates of move
	 */
	public static int[] stringToCoordinants(String move){
		int ret[] = new int[2];
		int i = move.charAt(1);
		i = 8- (i - 48);
		int j = intFromLetter(move.charAt(0));
		ret[0] = i;
		ret[1] = j;
		return ret;
	}
}
