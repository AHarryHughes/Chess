package com.aharryhughes;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/26/17.
 */
public class Board {

    private Rook R1 = new Rook(true, 0, 7, "R1", this);
    private Knight N1 = new Knight(true, 1, 7, "N1", this);
    private Bishop B1 = new Bishop(true, 2, 7, "B1", this);
    private King K1 = new King(true, 3, 7, "K1", this);
    private Queen Q1 = new Queen(true, 4, 7, "Q1", this);
    private Bishop B2 = new Bishop(true, 5, 7, "B2", this);
    private Knight N2 = new Knight(true, 6, 7, "N2", this);
    private Rook R2 = new Rook(true, 7, 7, "R2", this);

    private Pawn P1 = new Pawn(true, 0, 6, "P1", this);
    private Pawn P2 = new Pawn(true, 1, 6, "P2", this);
    private Pawn P3 = new Pawn(true, 2, 6, "P3", this);
    private Pawn P4 = new Pawn(true, 3, 6, "P4", this);
    private Pawn P5 = new Pawn(true, 4, 6, "P5", this);
    private Pawn P6 = new Pawn(true, 5, 6, "P6", this);
    private Pawn P7 = new Pawn( true, 6, 6,"P7", this);
    private Pawn P8 = new Pawn(true, 7, 6, "P8", this);

    //Player1 ^^^

    private Pawn p1 = new Pawn( false, 0, 1,"p1", this);
    private Pawn p2 = new Pawn(false, 1, 1, "p2", this);
    private Pawn p3 = new Pawn(false, 2, 1, "p3", this);
    private Pawn p4 = new Pawn(false, 3, 1, "p4", this);
    private Pawn p5 = new Pawn(false, 4, 1, "p5", this);
    private Pawn p6 = new Pawn(false, 5, 1, "p6", this);
    private Pawn p7 = new Pawn(false, 6, 1, "p7", this);
    private Pawn p8 = new Pawn(false, 7, 1, "p8", this);

    private Rook r1 = new Rook(false, 0, 0, "r1", this);
    private Knight n1 = new Knight(false, 1, 0, "n1", this);
    private Bishop b1 = new Bishop(false, 2, 0, "b1", this);
    private Queen q1 = new Queen(false, 3, 0, "q1", this);
    private King k1 = new King(false, 4, 0, "k1", this);
    private Bishop b2 = new Bishop(false, 5, 0, "b2", this);
    private Knight n2 = new Knight(false, 6, 0, "n2", this);
    private Rook r2 = new Rook(false, 7, 0, "r2", this);

    private Piece[][] board = new Piece[8][8];
    private ArrayList<Piece> player2pieces = new ArrayList<>(); // = {p1, p2, p3, p4, p5, p6, p7, p8, r1, n1, b1, q1, k1, b2, n2, r2};
    private ArrayList<Piece> player1pieces = new ArrayList<>(); // = {P1, P2, P3, P4, P5, P6, P7, P8, R1, N1, B1, K1, Q1, B2, N2, R2};


    public Board() {
    }

    public void setup() {
        System.out.println("setup");
        player2pieces.add(p1);
        player2pieces.add(p2);
        player2pieces.add(p3);
        player2pieces.add(p4);
        player2pieces.add(p5);
        player2pieces.add(p6);
        player2pieces.add(p7);
        player2pieces.add(p8);
        player2pieces.add(r1);
        player2pieces.add(n1);
        player2pieces.add(b1);
        player2pieces.add(q1);
        player2pieces.add(k1);
        player2pieces.add(b2);
        player2pieces.add(n2);
        player2pieces.add(r2);

        player1pieces.add(P1);
        player1pieces.add(P2);
        player1pieces.add(P3);
        player1pieces.add(P4);
        player1pieces.add(P5);
        player1pieces.add(P6);
        player1pieces.add(P7);
        player1pieces.add(P8);
        player1pieces.add(R1);
        player1pieces.add(N1);
        player1pieces.add(B1);
        player1pieces.add(Q1);
        player1pieces.add(K1);
        player1pieces.add(B2);
        player1pieces.add(N2);
        player1pieces.add(R2);

        for(int g = 0; g < player2pieces.size(); g++){
                Piece pieceToSet = player2pieces.get(g);
                board[pieceToSet.getX()][pieceToSet.getY()] = pieceToSet;
        }

        for(int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = null;
            }
        }

        for(int h = 0; h < player1pieces.size(); h++){
            Piece pieceToSet = player1pieces.get(h);
            board[pieceToSet.getX()][pieceToSet.getY()] = pieceToSet;
        }

    }

    public void renderBoard(){
        System.out.println("--|-Player2---------vs------------Player1-|");
        System.out.println("--|-0----1----2----3----4----5----6----7--|");

        int lineCount = 0;
        for (Piece[] line: board) {
            System.out.print("-"+lineCount+"|");
            for (Piece piece: line){
                if(piece == null){
                    System.out.print("----|");
                }
                else {
                    System.out.print("-"+piece.getName()+"-|");
                }
            }
            System.out.println();
            ++lineCount;
        }
    }

    public void movePiece(Piece piece, int x, int y){
        System.out.println("board move piece");
        if(board[x][y] != null){
            Piece pieceToRemove = board[x][y];
            this.removePiece(pieceToRemove);
        }

        board[piece.getX()][piece.getY()] = null;
        board[x][y] = piece;

        piece.move(x, y);
    }

    private void removePiece(Piece piece){
        System.out.println("board remove piece");
        ArrayList<Piece> playerPieces = this.getPlayerPieces(piece.isPlayer1Team());

        for(int h = 0; h < playerPieces.size(); h++){
            if(piece.equals(playerPieces.get(h))){
                playerPieces.remove(h);
                break;
            }
        }

    }

    public Piece[][] getboard() {
        return board;
    }

    public ArrayList<Piece> getPlayer1pieces() {
        return player1pieces;
    }

    public ArrayList<Piece> getPlayer2pieces() {
        return player2pieces;
    }

    public ArrayList<Piece> getPlayerPieces(Boolean isPlayer1){

        if(isPlayer1){
            return this.getPlayer1pieces();
        }
        else{
            return this.getPlayer2pieces();
        }

    }

    public boolean check(boolean isPlayer1){
        System.out.println("board check");
        if(isPlayer1){
            return K1.isInCheck(K1.getX(),K1.getY());
        }
        else{
            return k1.isInCheck(k1.getX(), k1.getY());
        }
    }

    public boolean checkmate(boolean isPlayer1){
        System.out.println("board checkmate");
        if(isPlayer1){
            return K1.isInCheckmate();
        }
        else{
            return k1.isInCheckmate();
        }
    }

}
