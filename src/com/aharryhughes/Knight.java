package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public class Knight extends Piece implements iHazMoves, iValidate{

    public Knight(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        super(isPlayer1Team, x, y, name, board);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMoves() {
        //get all the moves two moves one way and one move the other way from the piece
        ArrayList<ArrayList<Integer>> unVaidatedMoves = new ArrayList<>();
        Integer x = this.getX();
        Integer y = this.getY();

        //create array of all possible coordinate functions, iterate through that array, validating through both methods

        if(x - 1 >= 0 && y - 2 >= 0 && x - 1 <= 7 && y - 2 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x - 1);
            move.add(y - 2);
            unVaidatedMoves.add(move);
        }
        if(x - 2 >= 0 && y - 1 >= 0 && x - 2 <= 7 && y - 1 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x - 2);
            move.add(y - 1);
            unVaidatedMoves.add(move);
        }
        if(x + 1 >= 0 && y - 2 >= 0 && x + 1 <= 7 && y - 2 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x + 1);
            move.add(y - 2);
            unVaidatedMoves.add(move);
        }
        if(x + 2 >= 0 && y - 1 >= 0 && x + 2 <= 7 && y - 1 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x + 2);
            move.add(y - 1);
            unVaidatedMoves.add(move);
        }
        if(x + 1 >= 0 && y + 2 >= 0 && x + 1 <= 7 && y + 2 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x + 1);
            move.add(y + 2);
            unVaidatedMoves.add(move);
        }
        if(x + 2 >= 0 && y + 1 >= 0 && x + 2 <= 7 && y + 1 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x + 2);
            move.add(y + 1);
            unVaidatedMoves.add(move);
        }
        if(x - 1 >= 0 && y + 2 >= 0 && x - 1 <= 7 && y + 2 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x - 1);
            move.add(y + 2);
            unVaidatedMoves.add(move);
        }
        if(x - 2 >= 0 && y + 1 >= 0 && x - 2 <= 7 && y + 1 <= 7) {
            ArrayList<Integer> move = new ArrayList<>();
            move.add(x - 2);
            move.add(y + 1);
            unVaidatedMoves.add(move);
        }

        ////run validator and return moves
        return this.validateMoves(unVaidatedMoves);
    }

    @Override
    public ArrayList<ArrayList<Integer>> validateMoves(ArrayList<ArrayList<Integer>> possibleMoves) {
        //check to see if in board and not a team piece
        for (int i = possibleMoves.size() - 1; i > -1; i--) {

            ArrayList<Integer> move = possibleMoves.get(i);

            Integer x = move.get(0);
            Integer y = move.get(1);

            if(!(this.isInsideBoard(x, y) && ( this.isEmpty(x, y, this.getBoard()) || this.isEnemy(x, y, this.getBoard()) )) ){
                possibleMoves.remove(i);
            }
        }

        return possibleMoves;
    }
}
