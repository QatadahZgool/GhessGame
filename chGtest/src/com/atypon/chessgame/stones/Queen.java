package com.atypon.chessgame.stones;

import com.atypon.chessgame.board.Board;

import java.util.Objects;

public class Queen extends Stone {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean getIsWhite() {
        return isWhite;

    }


    @Override
    public boolean isValidMove(int sourceRow, int sourceColum, int destRow, int destColum, Board board) {
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
        } else {
            if (sourceRow - destRow == 0 && Math.abs(sourceColum - destColum) != 0) {
                x = Math.abs(destColum - sourceColum);
                if (sourceColum < destColum) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow, sourceColum + i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                } else {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow, sourceColum - i, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                }
            } else if (destColum - sourceColum == 0 && Math.abs(destRow - sourceRow) != 0) {
                x = Math.abs(destRow - sourceRow);
                if (sourceRow < destRow) {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow + i, sourceColum, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                } else {
                    for (int i = 1; i < x; i++) {
                        if (!isLegalMove(sourceRow, sourceColum, sourceRow - i, sourceColum, board)) return false;
                    }
                    return (theLastStepOfMovement(sourceRow, sourceColum, destRow, destColum, board));
                }
            }
        }
        System.out.println("It is illegal move because It is illegal destination aaab ");
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
        } else if (Math.abs(sourceRow - sourceRowKing) == 1 && Math.abs(sourceColum - sourceColumKing) == 1)
            return true;
        else if (sourceRow - sourceRowKing == 0 && Math.abs(sourceColum - sourceColumKing) == 1) return true;
        else if (sourceColumKing - sourceColum == 0 && Math.abs(sourceRowKing - sourceRow) > 1) {
            x = Math.abs(sourceRowKing - sourceRow);
            if (sourceRow < sourceColumKing) {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow + i, sourceColum, board)) return false;
                }
                return true;
            } else {
                for (int i = 1; i < x; i++) {
                    if (!checkMatInWay(sourceRow - i, sourceColum, board)) return false;
                }
            }
            return true;
        } else return sourceColumKing - sourceColum == 0 && Math.abs(sourceRowKing - sourceRow) == 1;
        return false;
    }

    private boolean checkMatInWay(int nextDestRow, int nextDestColum, Board board) {
        return board.isEmpty(nextDestRow, nextDestColum);
    }

    @Override
    public String nameOfStone() {
        if (getIsWhite()) return "QW";
        return "QB";
    }
}
