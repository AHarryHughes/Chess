package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public class Bishop extends Piece implements iHazMoves{

    public Bishop(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        super(isPlayer1Team, x, y, name, board);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMoves() {
        //init arraylist and add diagonal to it
        ArrayList<ArrayList<Integer>> validatedMoves = diagonalMoves(this.getX(), this.getY(), this.getBoard());
        //return list
        return validatedMoves;
    }
}
