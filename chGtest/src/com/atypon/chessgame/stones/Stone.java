package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

public abstract class Stone {
    protected final boolean isWhite;
    protected boolean isKilled = false;
    protected int columNumber;
    protected int rowNumber;
    public Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }
    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean isKilled() {
        return isKilled;
    }
    public void setKilled(boolean killed) {
        isKilled = killed;
    }
    public int getColumNumber() {
        return columNumber;
    }
    public void setColumNumber(int columNumber) {
        this.columNumber = columNumber;
    }
    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    public abstract boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board);
    public abstract String nameOfStone();
    public abstract boolean checkMat(int sourceRow, int sourceColum, int sourceRowKing, int sourceColumKing, Board board);
}
