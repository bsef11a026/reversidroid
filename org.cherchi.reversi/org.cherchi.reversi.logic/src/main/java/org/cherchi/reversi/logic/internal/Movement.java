package org.cherchi.reversi.logic.internal;

public class Movement {
	
	private int column;
	private int row;
	private int player;
	
	public Movement(int column, int row, int player) {
		this.column = column;
		this.row = row;
		this.player = player;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
}
