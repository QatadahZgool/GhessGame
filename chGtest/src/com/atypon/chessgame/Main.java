package com.atypon.chessgame;

import com.atypon.chessgame.game.ChessGame;

public class Main {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame("Qatadah", "Fahed");
        chessGame.StartGame();
    }
}