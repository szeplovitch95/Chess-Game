package chess;
import model.*;

public class Chess {

	public static ChessGame cg;
	
	public static void main(String[] args) {
		
		cg = new ChessGame(new ChessBoard(), new Player('w') , new Player('b'));
		
	}

	public static int intFromLetter(char in){
		return (int)in - 97;
	}
	
	public static char letterFromInt(int in){
		return (char)(in + 97);
	}
	
	public static String coordinatesToString(int row, int column){
		char col = letterFromInt(column);
		String ret = "" + col + (8 - row);
		return ret;
	}
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
