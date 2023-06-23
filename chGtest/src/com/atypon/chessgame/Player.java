package com.atypon.chessgame;

public class Player {
    private final String name;
    private final boolean isWhite;

    public Player(String WhitePlayer, boolean isWhite) {
        this.name = WhitePlayer;
        this.isWhite = isWhite;
    }

    public String getName() {
        return name;
    }


    public boolean isWhite() {
        return isWhite;
    }
}
