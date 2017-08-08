package com.aharryhughes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ahhughes8 on 7/26/17.
 */
public class Human extends Player {

    public Human(String name, boolean isPlayer1) {
        super(name, isPlayer1);
    }

    public void turn(Board board) {
        System.out.println(this.getName()+" turn");
        Piece selectedPiece;
        ArrayList<Integer> move;
        Integer moveDeterminate;
        do{
            if(board.check(this.isPlayer1())){
                selectedPiece = this.getKing(this.isPlayer1(), board);
                //Output some text that the user is in check and needs to move the king
                System.out.println("You must move your king out of the way of destruction");
            }
            else{
                selectedPiece = this.selectPiece(board);
            }
            move = this.analyzeOptions(board, selectedPiece);
            moveDeterminate = move.get(0);
        }while(moveDeterminate == null);
        this.makeMove(board, move.get(0), move.get(1), selectedPiece);
    }

    private Piece selectPiece(Board board) {
        System.out.println(this.getName()+" select piece");
        //get player pieces
        ArrayList<Piece> pieces = board.getPlayerPieces(this.isPlayer1());
        //render piece options
        this.printPieces(pieces);
        //get userInput for piece choice
        System.out.println("");
        System.out.println("Type in the index of the piece to see it's possible moves");
        //return piece based off index chosen by user
        Scanner scanner = new Scanner(System.in);
        int pieceIndex = scanner.nextInt();
        return pieces.get(pieceIndex);
    }

    private ArrayList<Integer> analyzeOptions(Board board, Piece piece) {
        System.out.println(this.getName()+" analyze options");
        ArrayList<ArrayList<Integer>> possibleMoves = piece.getMoves();
        return this.makeDecision(possibleMoves);
    }

    private ArrayList<Integer> makeDecision(ArrayList<ArrayList<Integer>> possibleMoves){
        //render pieces move options to the player and take input
        System.out.println(this.getName()+" make decision");
        this.printMoves(possibleMoves);

        System.out.println("");
        System.out.println("Type in the index of the x and y to make that move");
        System.out.println("(If you wish to view the pieces again, type \"-1\")");

        Scanner scanner = new Scanner(System.in);
        int moveIndex = scanner.nextInt();

        ArrayList<Integer> move = new ArrayList<>();
        if(moveIndex == -1){
            move.add(null);
            move.add(null);
        }
        else{
            move.add(possibleMoves.get(moveIndex).get(0));
            move.add(possibleMoves.get(moveIndex).get(1));
        }

        return move;
    }

    private void makeMove(Board board, int x, int y, Piece piece) {
        System.out.println(this.getName()+" make move");
        board.movePiece( piece, x, y );
    }

    private void printPieces(ArrayList<Piece> pieces){
        System.out.print("| ");
        for (int i = 0; i<pieces.size(); i++ ){
            System.out.print(i+": "+pieces.get(i).getName()+" | ");
        }
    }

    private void printMoves(ArrayList<ArrayList<Integer>> moves){
        System.out.print("| ");
        for (int i = 0; i<moves.size(); i++ ){
            System.out.print(i+": ("+moves.get(i).get(1)+", "+moves.get(i).get(0)+") | ");
        }
    }
}
