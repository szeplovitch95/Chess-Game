package chess;
import model.*;

public class Chess {


	
	public static void main(String[] args) {

		ChessBoard board = new ChessBoard();
		Player white = new Player('w');
		Player black = new Player('b');
		
		ChessGame cg = new ChessGame(board, white , black);
		
	}


}
