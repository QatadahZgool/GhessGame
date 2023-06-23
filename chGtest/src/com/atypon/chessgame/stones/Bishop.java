package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

import java.util.Objects;

public class Bishop extends Stone {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {
        System.out.println("hello there to com.atypon.chessgame.stones.Bishop check");
        System.out.println();
        System.out.println();
        System.out.println();
        int x = Math.abs(destColum - sourceColum);
        int c = Math.abs(sourceRow - destRow);
        if (x == c) {
            if (x > 1) {
                if (sourceRow < destRow && sourceColum < destColum) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow + i, sourceColum + i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                } else if (sourceRow > destRow && sourceColum < destColum) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow - i, sourceColum + i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                } else if (sourceRow < destRow && sourceColum > destColum) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow + i, sourceColum - i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                } else if (sourceRow > destRow && sourceColum > destColum) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow - i, sourceColum - i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                }
            } else if (x == 1) {
                return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
            }
        }
        System.out.println("It is illegal move because It is illegal destination ");
        return false;
    }

    private boolean isLegalMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {

        if (board.getStoneAt(sourceRow, sourceColum).getIsWhite()) {
            if (board.isEmpty(destRow, destColum)) return true;
            else {
                if (board.getStoneAt(destRow, destColum).getIsWhite()) {
                    System.out.println("It is illegal move because you have one of your stone in your way on index :" + destRow + " " + destColum);
                    return false;
                } else {
                    System.out.println("It is illegal move because you have a Black stone in your way at "+ destRow + " " + destColum+" so you have to change your destination ");
                    return false;
                }
            }
        } else {
            if (board.isEmpty(destRow, destColum)) return true;
            else {
                if (!board.getStoneAt(destRow, destColum).getIsWhite()) {
                    System.out.println("It is illegal move because you have one of your stone in your way on index :" + destRow + " " + destColum);
                    return false;
                } else {
                    System.out.println("It is illegal move because you have a White stone in your way at "+ destRow + " " + destColum+" so you have to change your destination ");
                    return false;
                }
            }
        }
    }

    private boolean theLastStepOfMovement(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {
        if (!board.isEmpty(destRow, destColum)) {
            if (containsEnemy(destRow, destColum, board)) {
                board.getStoneAt(destRow, destColum).setKilled(true);
                board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
                return true;
            }
            System.out.println("It is illegal move because you have your stone at your destination ");
            return false;
        }

        board.moveOneBlock(sourceRow, sourceColum, destRow, destColum);
        return true;

    }

    private boolean containsEnemy(int destRow, int destColum, Board board) {
        Stone stone = board.getStoneAt(destRow, destColum);
        return (Objects.nonNull(stone) && stone.getIsWhite() != isWhite);
    }

    @Override
    public boolean checkMat(int sourceRow, int sourceColum, int sourceRowKing, int sourceColumKing, Board board) {
        int x;
        if ((Math.abs(sourceRow - sourceRowKing) == Math.abs(sourceColum - sourceColumKing)) && Math.abs(sourceRow - sourceRowKing) > 1) {
            x = Math.abs(sourceRow - sourceRowKing);
            if (sourceRow < sourceRowKing && sourceColum < sourceColumKing) {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow + i, sourceColum + i, board)) return false;
                }
                return true;
            } else if (sourceRow > sourceColumKing && sourceColum < sourceColumKing) {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow - i, sourceColum + i, board)) return false;
                }
                return true;
            } else if (sourceRow < sourceRowKing && sourceColum > sourceColumKing) {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow + i, sourceColum - i, board)) return false;
                }
                return true;
            } else if (sourceRow > sourceColumKing && sourceColum > sourceColumKing) {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow - i, sourceColum - i, board)) return false;
                }
                return true;
            }
        } else return Math.abs(sourceRow - sourceRowKing) == 1 && Math.abs(sourceColum - sourceColumKing) == 1;
        return false;

    }

    private boolean checkMatInWay(int nextDestRow, int nextDestColum, Board board) {
        return board.isEmpty(nextDestRow, nextDestColum);
    }

    @Override
    public String nameOfStone() {
        if (getIsWhite()) return "BW";
        return "BB";
    }
}
