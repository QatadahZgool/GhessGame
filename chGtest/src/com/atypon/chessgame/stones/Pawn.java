package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

import java.util.Objects;

public class Pawn extends Stone {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {
        if (board.getStoneAt(sourceRow, sourceColum).getIsWhite()) {
            if (destColum - sourceColum == 1) {
                if (Math.abs(sourceRow - destRow) == 1) {
                    if (containsEnemy(destRow, destColum, board)) {
                        board.getStoneAt(destRow, destColum).setKilled(true);
                        board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                        return true;
                    }
                    else if(board.isEmpty(destRow,destColum)){System.out.println("It is illegal move because It is illegal destination ");
                        return false;}
                    System.out.println("It is illegal move because you have your stone at your destination ");
                    return false;
                } else if (sourceRow - destRow == 0 && board.isEmpty(destRow,destColum)) {
                    board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                    return true;
                }
            } else if (sourceColum == 2 && destColum == 4 && sourceRow - destRow == 0) {
                if (board.isEmpty(destRow, destColum)) {
                    board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                    return true;
                }
            }
            System.out.println("It is illegal move because It is illegal destination ");
            return false;
        } else {
            if (sourceColum - destColum == 1) {
                if (Math.abs(sourceRow - destRow) == 1) {
                    if (containsEnemy(destRow, destColum, board)) {
                        board.getStoneAt(destRow, destColum).setKilled(true);
                        board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                        return true;
                    }
                    System.out.println("It is illegal move because you have your stone at your destination ");
                    return false;
                } else if (sourceRow - destRow == 0) {
                    board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                    return true;
                }
            } else if (sourceColum == 7 && destColum == 5 && sourceRow - destRow == 0) {
                if (board.isEmpty(destRow, destColum)) {
                    board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                    return true;
                }
            }
            System.out.println("It is illegal move because It is illegal destination ");
            return false;
        }
    }

    private boolean containsEnemy(int destRow, int destColum, Board board) {
        Stone stone = board.getStoneAt(destRow, destColum);
        return (Objects.nonNull(stone) && stone.getIsWhite() != isWhite);
    }

    @Override
    public boolean checkMat(int sourceRow, int sourceColum, int sourceRowKing, int sourceColumKing, Board board) {
        if (board.getStoneAt(sourceRow, sourceColum).getIsWhite()) {
            if (sourceColumKing - sourceColum == 1) {
                return Math.abs(sourceRow - sourceRowKing) == 1;
            }
        } else {
            if (sourceColum - sourceColumKing == 1) {
                return Math.abs(sourceRow - sourceRowKing) == 1;
            }
        }
        return false;
    }

    @Override
    public String nameOfStone() {
        if (getIsWhite()) return "PW";
        return "PB";
    }
}