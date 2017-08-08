package com.aharryhughes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ahhughes8 on 7/26/17.
 */
public class AI extends Player {
    private int difficulty;

    public AI( int difficulty ) {
        super("placeholder", false);
        this.setName(this.getBotName(difficulty));
        this.difficulty = difficulty;
    }

    public void turn(Board board) {

    }

    private String getBotName(int difficulty){
        String aiName;
        switch (difficulty){
            case(3):
                aiName = "Chess Bot 3000";
            case(5):
                aiName = "Chess Bot 6000";
            case(7):
                aiName = "Botty Fischer";
            default:
                aiName = "No name";
        }
        return aiName;
    }

//    public HashMap<Piece, ArrayList<Integer>> AIThink(Board board, int difficulty){
//        ArrayList<Piece> pieces = board.getPlayerPieces(this.isPlayer1());
//        analyzeOptions(board, pieces, difficulty);
//
//    }

    public ArrayList<Integer> analyzeOptions(Board board, ArrayList<Piece> pieces, int difficulty) {
        return null;
    }

    public void makeMove(Board board, int x, int y, Piece piece) {

    }
}
