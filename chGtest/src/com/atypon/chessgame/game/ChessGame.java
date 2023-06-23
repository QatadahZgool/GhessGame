package com.atypon.chessgame.game;

import com.atypon.chessgame.Player;
import com.atypon.chessgame.board.Board;

import java.util.Scanner;

public class ChessGame {
    private static final int MAX_NUMBER_OF_MOVE = 25;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;

    public ChessGame(String WhitePlayer, String BlackPlayer) {
        this.whitePlayer = new Player(WhitePlayer, true);
        this.blackPlayer = new Player(BlackPlayer, false);
        this.board = Board.getInstance();
    }

    public void StartGame() {
        board.viewTheCurrentBoard();
        Scanner cs = new Scanner(System.in);
        int sourceRow, sourceColum, destRow, destColum;
        GameState gameState = GameState.DRAW;
        boolean notValidMove = true;
        for (int i = 1; i <= MAX_NUMBER_OF_MOVE; i++) {
            while (notValidMove) {
                System.out.println(this.whitePlayer.getName() + "  choice your move");
                sourceRow = cs.nextInt();
                System.out.println("Enter the source row value");
                sourceColum = cs.nextInt();
                System.out.println("Enter the source colum value");
                destRow = cs.nextInt();
                System.out.println("Enter the destination row value");
                destColum = cs.nextInt();
                System.out.println("Enter the destination colum value");
                if (sourceRow > 8 || sourceRow < 1 || sourceColum > 8 || sourceColum < 1 || destRow > 8 || destRow < 1 || destColum > 8 || destColum < 1)
                    System.out.println("please renter the values of source and destination because the last values was out of the range");
                else if (board.isEmpty(sourceRow, sourceColum)) {
                    System.out.println("there is no stone to move it because it is empty index");
                } else {
                    if (!board.getStoneAt(sourceRow, sourceColum).getIsWhite())
                        System.out.println("you can't move this stone because isn't yours");
                    else {
                        if (board.containsStone(sourceRow, sourceColum)) {
                            if (board.checkmateBeforeMove(board.getWhiteKing())) {
                                System.out.println();
                                System.out.println();
                                System.out.println(this.blackPlayer.getName() + " win ");
                                gameState = GameState.BLACK_IS_WIN;
                                notValidMove = false;
                            }
                            if (board.moveStone(sourceRow, sourceColum, destRow, destColum)) {
                               if (board.checkmateAfterMove(board.getWhiteKing())) {
                                   System.out.println();
                                   System.out.println();
                                   System.out.println();
                                   board.viewTheCurrentBoard();
                                   System.out.println();
                                   System.out.println();
                                    System.out.println(this.blackPlayer.getName() + " win ");
                                    gameState = GameState.BLACK_IS_WIN;
                                    notValidMove = false;
                                }
                                    notValidMove = false;

                            }
                        }
                    }
                }
            }
            if (gameState == GameState.BLACK_IS_WIN) break;
            System.out.println();
            System.out.println();
            System.out.println();
            board.viewTheCurrentBoard();

            while (!notValidMove) {
                System.out.println(this.blackPlayer.getName() + "  choice your move");
                sourceRow = cs.nextInt();
                System.out.println("Enter the source row value");
                sourceColum = cs.nextInt();
                System.out.println("Enter the source colum value");
                destRow = cs.nextInt();
                System.out.println("Enter the destination row value");
                destColum = cs.nextInt();
                System.out.println("Enter the destination colum value");
                if (sourceRow > 8 || sourceRow < 1 || sourceColum > 8 || sourceColum < 1 || destRow > 8 || destRow < 1 || destColum > 8 || destColum < 1)
                    System.out.println("please renter the values of source and destination because the last values was out of the range");
                else if (board.isEmpty(sourceRow, sourceColum)) {
                    System.out.println("there is no stone to move it because it is empty index");
                } else {
                    if (board.getStoneAt(sourceRow, sourceColum).getIsWhite())
                        System.out.println("you can't move this stone because isn't yours");
                    else {
                        if (board.containsStone(sourceRow, sourceColum)) {
                            if (board.checkmateBeforeMove(board.getBlackKing())) {
                                System.out.println();
                                System.out.println();
                                System.out.println(this.whitePlayer.getName() + " win");
                                gameState = GameState.WHITE_IS_WIN;
                                notValidMove = true;
                            }
                            if (board.moveStone(sourceRow, sourceColum, destRow, destColum)) {
                                if (board.checkmateAfterMove(board.getBlackKing())) {
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    board.viewTheCurrentBoard();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println(this.whitePlayer.getName() + " win");
                                    gameState = GameState.WHITE_IS_WIN;
                                    notValidMove = true;
                                }
                                notValidMove = true;
                            }
                        }
                    }
                }
            }
            if (gameState == GameState.WHITE_IS_WIN) break;
            System.out.println();
            System.out.println();
            System.out.println();
            board.viewTheCurrentBoard();
        }
        System.out.println();
        System.out.println();
        if (gameState == GameState.DRAW) System.out.println("Draw");
    }
}
