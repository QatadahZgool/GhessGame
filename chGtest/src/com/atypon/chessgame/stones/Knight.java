package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

import java.util.Objects;

public class Knight extends Stone {
    public Knight(boolean isWhite) {
        super(isWhite);
    }


    @Override
    public boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {
        if (Math.abs(sourceRow - destRow) == 2 && Math.abs(sourceColum - destColum) == 1 || Math.abs(sourceRow - destRow) == 1 && Math.abs(sourceColum - destColum) == 2) {
            if (!board.isEmpty(destRow, destColum)) {
                if (containsEnemy(destRow, destColum, board)) {
                    board.getStoneAt(destRow, destColum).setKilled(true);
                    board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                     return true;
                }
                System.out.println("It is illegal move because you have one of your stone at your destination ");
                return false;
            }

            board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
            return true;
        }
        System.out.println("It is illegal move because It is illegal destination ");
        return false;
    }

    private boolean containsEnemy(int destRow, int destColum, Board board) {
        Stone stone = board.getStoneAt(destRow, destColum);
        return (Objects.nonNull(stone) && stone.getIsWhite() != isWhite);
    }

    @Override
    public boolean checkMat(int sourceRow, int sourceColum, int sourceRowKing, int sourceColumKing, Board board) {
        return Math.abs(sourceRow - sourceRowKing) == 2 && Math.abs(sourceColum - sourceColumKing) == 1 || Math.abs(sourceRow - sourceRowKing) == 1 && Math.abs(sourceColum - sourceColumKing) == 2;
    }


    @Override
    public String nameOfStone() {
        if (getIsWhite()) return "NW";
        return "NB";
    }
}