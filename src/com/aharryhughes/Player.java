package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/26/17.
 */
public class Player {
    private String name;
    private boolean isPlayer1;
    private boolean inCheck = false;

    public Player(String name, boolean isPlayer1) {
        this.name = name;
        this.isPlayer1 = isPlayer1;
    }

    public void turn(Board board){
    }

    public Piece getKing(boolean isPlayer1, Board board){
        ArrayList<Piece> playerPieces;

        if(isPlayer1){
            playerPieces = board.getPlayer1pieces();
        }
        else{
            playerPieces = board.getPlayer2pieces();
        }

        for(Piece piece: playerPieces){
            if(piece.getName().equals("K1") || piece.getName().equals("k1")){
                return piece;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }
}
