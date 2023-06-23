package com.atypon.chessgame.board;

import com.atypon.chessgame.stones.Stone;

public class Block {
    private final int columNumber;
    private final int rowNumber;
    private final boolean isWhite;
    private boolean isEmpty;
    private Stone stone;


    public Block(int rowNumber, int columNumber, boolean isWhite) {
        this.rowNumber = rowNumber;
        this.columNumber = columNumber;
        this.isWhite = isWhite;
        this.isEmpty = true;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Stone getStone() {
        return this.stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
        this.isEmpty = false;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }

    public void viewTheContentsOfTheBlock() {
        if (isEmpty) System.out.print(rowNumber + " " + columNumber + " " + isEmpty() + "    ");
        else System.out.print(rowNumber + " " + columNumber + "   " + stone.nameOfStone() + "    ");
    }
}
