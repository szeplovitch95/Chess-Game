package model;

import java.util.List;

public class ChessGame {
	private ChessBoard board; 
	private Player whitePlayer; 
	private Player blackPlayer;
	private List<String> allMoves;
	private String Winner; 
	
	public ChessGame() {}
	
	public ChessGame(ChessBoard board, Player whitePlayer, Player blackPlayer) {
		this.board = board; 
		this.whitePlayer = whitePlayer; 
		this.blackPlayer = blackPlayer; 
	}

	public ChessBoard getBoard() {
		return board;
	}

	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public List<String> getAllMoves() {
		return allMoves;
	}

	public void setAllMoves(List<String> allMoves) {
		this.allMoves = allMoves;
	}

	public String getWinner() {
		return Winner;
	}

	public void setWinner(String winner) {
		Winner = winner;
	}
	
	public void addMove(String move) {
		//implement this method to add a move to the List of all  moves
	}
}
