package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

import java.util.Objects;

public class King extends Stone {
    public King(boolean isWhite) {
        super(isWhite);
    }


    @Override
    public boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {

        if ((Math.abs(destRow - sourceRow) == 1 && Math.abs(destColum - sourceColum) == 1)
                || (Math.abs(destRow - sourceRow) == 1 && Math.abs(destColum - sourceColum) == 0)
                || (Math.abs(destRow - sourceRow) == 0 && Math.abs(destColum - sourceColum) == 1)) {
            if (containsEnemy(destRow, destColum, board)) {
                board.getStoneAt(destRow, destColum).setKilled(true);
                board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                if (board.getStoneAt(destRow, destColum).getIsWhite()) {
                    board.getWhiteKing().setRowNumber(destRow);
                    board.getWhiteKing().setColumNumber(destColum);
                } else {
                    board.getBlackKing().setRowNumber(destRow);
                    board.getBlackKing().setColumNumber(destColum);
                }
                return true;
            } else if (!board.isEmpty(destRow, destColum)) {
                System.out.println("It is illegal move because you have your stone at your destination ");
                return false;
            }
            board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
            if (board.getStoneAt(destRow, destColum).getIsWhite()) {
                board.getWhiteKing().setRowNumber(destRow);
                board.getWhiteKing().setColumNumber(destColum);
            } else {
                board.getBlackKing().setRowNumber(destRow);
                board.getBlackKing().setColumNumber(destColum);
            }
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
        return (Math.abs(sourceRowKing - sourceRow) == 1 && Math.abs(sourceColumKing - sourceColum) == 1)
                || (Math.abs(sourceRowKing - sourceRow) == 1 && Math.abs(sourceColumKing - sourceColum) == 0)
                || (Math.abs(sourceRowKing - sourceRow) == 0 && Math.abs(sourceColumKing - sourceColum) == 1);
    }

    @Override
    public String nameOfStone() {
        if (getIsWhite()) return "KW";
        return "KB";
    }
}
