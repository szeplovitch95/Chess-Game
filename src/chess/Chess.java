package chess;
import model.*;

public class Chess {

	public static void main(String[] args) {
		Player black = new Player('b');
		Player white = new Player('w');
		ChessBoard board = new ChessBoard();
		ChessGame cg = new ChessGame(board, white, black);
		
	}
	
	public void getPlayerInput(){
		
	}

}
