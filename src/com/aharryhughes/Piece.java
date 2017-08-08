package com.aharryhughes;

import java.util.ArrayList;

/**
 * Created by ahhughes8 on 7/27/17.
 */
public abstract class Piece {
    private boolean isPlayer1Team;
    private Integer x;
    private Integer y;
    private String name;
    private Board board;

    public Piece(boolean isPlayer1Team, Integer x, Integer y, String name, Board board) {
        this.isPlayer1Team = isPlayer1Team;
        this.x = x;
        this.y = y;
        this.name = name;
        this.board = board;
    }

    public ArrayList<ArrayList<Integer>> getMoves(){
        ArrayList<ArrayList<Integer>> nullMoves = new ArrayList<>();
        ArrayList<Integer> nullMove = new ArrayList<>();
        nullMove.add(null);
        nullMove.add(null);
        nullMoves.add(nullMove);
        return nullMoves;
    }

    public ArrayList<ArrayList<Integer>> getCheckMoves(){
        ArrayList<ArrayList<Integer>> nullMoves = new ArrayList<>();
        ArrayList<Integer> nullMove = new ArrayList<>();
        nullMove.add(null);
        nullMove.add(null);
        nullMoves.add(nullMove);
        return nullMoves;
    }

    //Common Movement Patterns
    //Diagonal lines(use for Rook and Queen)
    public ArrayList<ArrayList<Integer>> horizontalVerticalMoves(Integer x, Integer y, Piece[][] board){
        ArrayList<ArrayList<Integer>> allMoves = new ArrayList<>();

        Boolean n = true;
        Boolean e = true;
        Boolean s = true;
        Boolean w = true;

        for(Integer i = 1; i < 8; i++){

            //N Vertical
            if(n && this.isInsideBoard(x, y - i)) {
                if(this.isEnemy(x, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                    n = false;
                }
                else if(this.isEmpty(x, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                }
                else{
                    n = false;
                }
            }
            else{n = false;}

            //S Vertical
            if(s && isInsideBoard(x, y + i)) {
                if(isEnemy(x, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                    s = false;
                }
                else if(this.isEmpty(x, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                }
                else{
                    s = false;
                }
            }
            else{s = false;}

            //E Horizontal
            if(e && isInsideBoard(x + i, y)) {
                if(isEnemy(x + i, y, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y);
                    allMoves.add(temporaryMove);
                    e = false;
                }
                else if(this.isEmpty(x + i, y, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y);
                    allMoves.add(temporaryMove);
                }
                else{
                    e = false;
                }
            }
            else{e = false;}

            //W Horizontal
            if(w && isInsideBoard(x - i, y)) {
                if(isEnemy(x - i, y, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y);
                    allMoves.add(temporaryMove);
                    w = false;
                }
                else if(this.isEmpty(x - i, y, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y);
                    allMoves.add(temporaryMove);
                }
                else{
                    w = false;
                }
            }
            else{w = false;}
        }

        return allMoves;
    }

    //Diagonal lines(use for Bishop and Queen)
    public ArrayList<ArrayList<Integer>> diagonalMoves(Integer x, Integer y, Piece[][] board){
        ArrayList<ArrayList<Integer>> allMoves = new ArrayList<>();

        Boolean nW = true;
        Boolean nE = true;
        Boolean sW = true;
        Boolean sE = true;

        for(Integer i = 1; i < 8; i++ ){

            //SE Diagonal
            if(sE && this.isInsideBoard(x + i, y + i)) {
                if(this.isEnemy(x + i, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                    sE = false;
                }
                else if(this.isEmpty(x + i, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                }
                else{
                    sE = false;
                }
            }
            else{sE = false;}

            //SW Diagonal
            if(sW && isInsideBoard(x - i, y + i)) {
                if(isEnemy(x - i, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                    sW = false;
                }
                else if(this.isEmpty(x - i, y + i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y + i);
                    allMoves.add(temporaryMove);
                }
                else{
                    sW = false;
                }
            }
            else{sW = false;}

            //NE Diagonal
            if(nE && isInsideBoard(x + i, y - i)) {
                if(isEnemy(x + i, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                    nE = false;
                }
                else if(this.isEmpty(x + i, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x + i);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                }

                else{
                    nE = false;
                }
            }
            else{nE = false;}

            //NW Diagonal
            if(nW && isInsideBoard(x - i, y - i)) {
                if(isEnemy(x - i, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                    nW = false;
                }
                else if(this.isEmpty(x - i, y - i, board)){
                    ArrayList<Integer> temporaryMove = new ArrayList<>();
                    temporaryMove.add(x - i);
                    temporaryMove.add(y - i);
                    allMoves.add(temporaryMove);
                }
                else{
                    nW = false;
                }
            }
            else{nW = false;}
        }

        return allMoves;
    }

    //Validation tools
    public boolean isInsideBoard(Integer x, Integer y){
        return (x >= 0 && x <= 7 && y >= 0 && y <= 7 );
    }

    public boolean isEmpty(Integer x, Integer y, Piece[][] board){
        return isInsideBoard(x, y) && board[x][y] == null;
    }

    public boolean isEnemy(Integer x, Integer y, Piece[][] board){
        return !isEmpty(x, y, board) && board[x][y].isPlayer1Team() != this.isPlayer1Team();
    }

    //Movement
    public void move(Integer x, Integer y){
        this.setX(x);
        this.setY(y);
    }

    //Getters and Setters
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isPlayer1Team() {
        return isPlayer1Team;
    }

    public String getName() {
        return name;
    }

    public Board getBoardClass() {
        return board;
    }

    public Piece[][] getBoard() {
        return board.getboard();
    }

    public boolean equals(Piece o) {
        return this.getName().equals( o.getName() );
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
