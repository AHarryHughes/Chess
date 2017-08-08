package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public class Queen extends Piece implements iHazMoves{

    public Queen(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        super(isPlayer1Team, x, y, name, board);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMoves() {
        //init arraylist and add horizontal, vertical, and diagonal to it
        ArrayList<ArrayList<Integer>> validatedMoves = horizontalVerticalMoves(this.getX(), this.getY(), this.getBoard());
        validatedMoves.addAll(diagonalMoves(this.getX(), this.getY(), this.getBoard()));

        //return list
        return validatedMoves;
    }
}
