package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public class Pawn extends Piece implements iHazMoves, iValidate{

    public Pawn(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        super(isPlayer1Team, x, y, name, board);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMoves() {
        //get all the moves one forward away from the piece

        //  get the direction based on player
        int direction = this.direction();

        ArrayList<Integer> diagonalLeft = new ArrayList<>();
        diagonalLeft.add(getX()-1);
        diagonalLeft.add(getY()+direction);

        ArrayList<Integer> Forward = new ArrayList<>();
        Forward.add(getX());
        Forward.add(getY()+direction);

        ArrayList<Integer> diagonalRight = new ArrayList<>();
        diagonalRight.add(getX()+1);
        diagonalRight.add(getY()+direction);

        ArrayList<ArrayList<Integer>> unValidatedPossibleMoves = new ArrayList<>();
        unValidatedPossibleMoves.add(diagonalLeft);
        unValidatedPossibleMoves.add(Forward);
        unValidatedPossibleMoves.add(diagonalRight);

        //run validator and return moves
        return this.validateMoves(unValidatedPossibleMoves);
    }

    private int direction(){
        if(this.isPlayer1Team()){
            return -1;
        }
        return 1;
    }

    @Override
    public ArrayList<ArrayList<Integer>> validateMoves(ArrayList<ArrayList<Integer>> possibleMoves) {
        //if forward make sure it is empty, if diagonal make sure it is an enemy piece
        Integer x = possibleMoves.get(2).get(0);
        Integer y = possibleMoves.get(2).get(1);
        if( !( this.isInsideBoard( x, y ) && ( !this.isEmpty( x, y, this.getBoard() ) && this.isEnemy( x, y, this.getBoard() ) ) ) ){
            possibleMoves.remove(2);
        }

        x = possibleMoves.get(1).get(0);
        y = possibleMoves.get(1).get(1);
        if( !( this.isInsideBoard( x, y ) && ( this.isEmpty( x, y, this.getBoard() ) && !this.isEnemy( x, y, this.getBoard() ) ) ) ){
            possibleMoves.remove(1);
        }

        x = possibleMoves.get(0).get(0);
        y = possibleMoves.get(0).get(1);
        if( !( this.isInsideBoard( x, y ) && ( !this.isEmpty( x, y, this.getBoard() ) && this.isEnemy( x, y, this.getBoard() ) ) ) ){
            possibleMoves.remove(0);
        }

        return possibleMoves;
    }
}
