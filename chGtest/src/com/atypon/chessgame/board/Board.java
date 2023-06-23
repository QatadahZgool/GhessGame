package com.atypon.chessgame.board;

import com.atypon.chessgame.stones.*;

public class Board {
    private static final Board board = new Board();
    private final Block[][] blocks = new Block[9][9];
    private Stone whiteKing;
    private Stone blackKing;


    private Board() {
        InitialBoard();
    }

    public static Board getInstance() {
        return board;
    }

    private void InitialBoard() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (Math.pow(-1, i + j) > 0)
                    blocks[i][j] = new Block(i, j, false);
                else
                    blocks[i][j] = new Block(i, j, true);
            }
        }


        for (int i = 1; i <= 8; i++) {
            blocks[i][2].setStone(new Pawn(true));
            blocks[i][7].setStone(new Pawn(false));
        }
        blocks[1][1].setStone(new Rook(true));
        blocks[8][1].setStone(new Rook(true));
        blocks[1][8].setStone(new Rook(false));
        blocks[8][8].setStone(new Rook(false));

        blocks[2][1].setStone(new Knight(true));
        blocks[7][1].setStone(new Knight(true));
        blocks[7][8].setStone(new Knight(false));
        blocks[2][8].setStone(new Knight(false));

        blocks[3][1].setStone(new Bishop(true));
        blocks[6][1].setStone(new Bishop(true));
        blocks[3][8].setStone(new Bishop(false));
        blocks[6][8].setStone(new Bishop(false));

        blocks[4][1].setStone(new Queen(true));
        blocks[4][8].setStone(new Queen(false));

        blocks[5][1].setStone(new King(true));
        blocks[5][8].setStone(new King(false));
        whiteKing = blocks[5][1].getStone();
        blackKing = blocks[5][8].getStone();
        whiteKing.setRowNumber(5);
        whiteKing.setColumNumber(1);
        blackKing.setColumNumber(8);
        blackKing.setRowNumber(5);
    }

    public Stone getWhiteKing() {
        return whiteKing;
    }

    public Stone getBlackKing() {
        return blackKing;
    }

    public boolean checkmateAfterMove(Stone stone) {
        int x = 0;
        if (stone == getWhiteKing()) {
            for (int i = 1; i <= 8; i++) {
                for (int g = 1; g <= 8; g++) {
                    if (!blocks[i][g].isEmpty())
                        if (!blocks[i][g].getStone().getIsWhite())
                            if (blocks[i][g].getStone().checkMat(i, g, getWhiteKing().getRowNumber(), getWhiteKing().getColumNumber(), board))
                                x = 1;
                }
            }
            return x == 1;
        } else if (stone == getBlackKing()) {
            for (int i = 1; i <= 8; i++) {
                for (int g = 1; g <= 8; g++) {
                    if (!blocks[i][g].isEmpty())
                        if (blocks[i][g].getStone().getIsWhite())
                            if (blocks[i][g].getStone().checkMat(i, g, getBlackKing().getRowNumber(), getBlackKing().getColumNumber(), board))
                                x = 1;
                }
            }
            return x == 1;
        }
        return false;
    }

    public boolean checkmateBeforeMove(Stone stone) {
        if (!(stone instanceof King)) return false;
        int x = 0;
        if (stone.getRowNumber() == 1) {
            if (!checkmateForNextIndex(1, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(1, stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(2, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(2, stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(2, stone.getColumNumber(), stone)) x += 1;
            return x == 0;
        }
        if (stone.getRowNumber() == 8) {
            if (!checkmateForNextIndex(8, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(8, stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(7, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(7, stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(7, stone.getColumNumber(), stone)) x += 1;
            return x == 0;
        }
        if (stone.getColumNumber() == 1) {
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, 2, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, 2, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber(), 2, stone)) x += 1;
            return x == 0;
        }
        if (stone.getColumNumber() == 8) {
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, 8, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, 8, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, 7, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, 7, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber(), 7, stone)) x += 1;
            return x == 0;
        } else {
            if (!checkmateForNextIndex(stone.getRowNumber(), stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber(), stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, stone.getColumNumber(), stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() + 1, stone.getColumNumber() - 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, stone.getColumNumber(), stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, stone.getColumNumber() + 1, stone)) x += 1;
            if (!checkmateForNextIndex(stone.getRowNumber() - 1, stone.getColumNumber() - 1, stone)) x += 1;
            return x == 0;
        }

    }

    public boolean checkmateForNextIndex(int sourceRowKing, int sourceColumKing, Stone stone) {
        int x = 0;
        if (stone == getWhiteKing()) {
            if (getWhiteKing().getRowNumber() == 1)
                for (int i = 1; i <= 8; i++) {
                    for (int g = 1; g <= 8; g++) {
                        if (!blocks[i][g].isEmpty())
                            if (!blocks[i][g].getStone().getIsWhite())
                                if (blocks[i][g].getStone().checkMat(i, g, sourceRowKing, sourceColumKing, board))
                                    x = 1;
                    }
                }
            return x == 1;
        } else if (stone == getBlackKing()) {
            for (int i = 1; i <= 8; i++) {
                for (int g = 1; g <= 8; g++) {
                    if (!blocks[i][g].isEmpty())
                        if (blocks[i][g].getStone().getIsWhite())
                            if (blocks[i][g].getStone().checkMat(i, g, sourceRowKing, sourceColumKing, board)) x = 1;
                }
            }
            return x == 1;
        }
        return false;
    }

    public void viewTheCurrentBoard() {
        for (int i = 1; i <= 8; i++) {
            for (int g = 1; g <= 8; g++) {
                blocks[i][g].viewTheContentsOfTheBlock();
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public void moveOneBlock(int sourceRow, int sourceColum, int destRow, int destColum) {
        blocks[destRow][destColum].setStone(blocks[sourceRow][sourceColum].getStone());
        blocks[sourceRow][sourceColum].setEmpty(true);
        blocks[destRow][destColum].setEmpty(false);
    }

    public boolean moveStone(int sourceRow, int sourceColum, int destRow, int destColum) {
        return blocks[sourceRow][sourceColum].getStone()
                .isValidMove(sourceRow, sourceColum, destRow, destColum, this);
    }

    public boolean isEmpty(int currentRow, int currentColum) {
        return blocks[currentRow][currentColum].isEmpty();
    }

    public Stone getStoneAt(int row, int colum) {
        return blocks[row][colum].getStone();
    }

    public boolean containsStone(int row, int colum) {
        return !blocks[row][colum].isEmpty();
    }
}