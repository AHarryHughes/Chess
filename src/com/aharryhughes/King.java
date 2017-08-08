package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public class King extends Piece implements iHazMoves, iValidate{
    private boolean inCheck;

    public King(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        super(isPlayer1Team, x, y, name, board);
        this.inCheck = false;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMoves() {
        System.out.println("King getMoves");
        ArrayList<ArrayList<Integer>> unValidatedMoves = new ArrayList<>();
        Integer x = this.getX();
        Integer y = this.getY();

        //get all the moves one move away from the piece
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y + j);
                    unValidatedMoves.add(temporaryMove);
                }
            }
        }

        //run validator and return moves
        return this.validateMoves(unValidatedMoves);
    }

    @Override
    public ArrayList<ArrayList<Integer>> validateMoves(ArrayList<ArrayList<Integer>> possibleMoves) {
        //remove moves that are off the board, onto team pieces, and into check
        System.out.println("King valMoves");
        for(int i = possibleMoves.size() - 1 ; i > -1; i-- ){
            ArrayList<Integer> move = possibleMoves.get(i);
            Integer x = move.get(0);
            Integer y = move.get(1);

            if(!(this.isInsideBoard(x, y) && !this.isInCheck(x, y) && (this.isEmpty(x, y, this.getBoard()) || this.isEnemy(x, y, this.getBoard())))){
                possibleMoves.remove(i);
            }

        }

        return possibleMoves;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCheckMoves() {
        System.out.println("King getCheckMoves");
        ArrayList<ArrayList<Integer>> unValidatedMoves = new ArrayList<>();
        Integer x = this.getX();
        Integer y = this.getY();


        //get all the moves one move away from the piece
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y + j);
                    unValidatedMoves.add(temporaryMove);
                }
            }
        }

        //run validator and return moves
        return this.validateCheckMoves(unValidatedMoves);
    }

    public ArrayList<ArrayList<Integer>> validateCheckMoves(ArrayList<ArrayList<Integer>> possibleMoves) {
        //remove moves that are off the board, onto team pieces, and into check
        System.out.println("King valCheckMoves");
        for(int i = possibleMoves.size() - 1 ; i > -1; i-- ){
            ArrayList<Integer> move = possibleMoves.get(i);
            Integer x = move.get(0);
            Integer y = move.get(1);

            if(!(this.isInsideBoard(x, y) && (this.isEmpty(x, y, this.getBoard()) || this.isEnemy(x, y, this.getBoard())))){
                possibleMoves.remove(i);
            }
        }

        return possibleMoves;
    }

    public boolean isInCheck(Integer x, Integer y) {
        //anylyze whether the king is in check
        System.out.println("King isInCheck");
            boolean inCheck = false;
        //  grab all the opponent pieces
            ArrayList<Piece> opponentsPieces = this.getBoardClass().getPlayerPieces(!this.isPlayer1Team());
            ArrayList<ArrayList<Integer>> opponentMoves = new ArrayList<>();
        //  evaluate all their moves
            for (int i = 0; i < opponentsPieces.size(); i++) {
                if(opponentsPieces.get(i).getName().equals("K1") || opponentsPieces.get(i).getName().equals("k1") ) {
                    opponentMoves.addAll(opponentsPieces.get(i).getCheckMoves());
                }
                else{
                    opponentMoves.addAll(opponentsPieces.get(i).getMoves());
                }
            }
        //  if their moves correlate with the x,y of king
            for(int j = 0; j < opponentMoves.size(); j++){
                //  set inCheck to true
                if(opponentMoves.get(j).get(0) != null && opponentMoves.get(j).get(0).equals(x) && opponentMoves.get(j).get(1).equals(y)){
                   inCheck = true;
                }
            }
            this.inCheck = inCheck;
        return inCheck;
    }

    public boolean isInCheckmate(){
        System.out.println("King isInCheckMate");
        boolean inCheckmate = false;

        ArrayList<ArrayList<Integer>> kingMoves = this.getMoves();

        if(this.getBoardClass().getPlayerPieces(this.isPlayer1Team()).size() != 1) {
            ArrayList<Integer> kingSpace = new ArrayList<>();
            kingSpace.add(this.getX());
            kingSpace.add(this.getY());
            kingMoves.add(kingSpace);
        }

        for(int i = kingMoves.size()-1; i > -1; i--){
            if( this.isInCheck( kingMoves.get(i).get(0), kingMoves.get(i).get(1) ) ){
                kingMoves.remove(i);
            }
        }

        return kingMoves.size() == 0;

    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }
}
