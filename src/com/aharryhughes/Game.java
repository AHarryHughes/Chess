package com.aharryhughes;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by ahhughes8 on 7/26/17.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean isAI;
    private boolean player1Turn;
    private Player currentPlayer;
    private Player otherPlayer;

    public Game() {
    }

    public void play(){
        System.out.println("play ====================================================");
        this.gameSetUp();

        player1Turn = player1GoesFirst();

        do{

            if(player1Turn){
                currentPlayer = player1;
                otherPlayer = player2;
            }
            else{
                currentPlayer = player2;
                otherPlayer = player1;
            }

            System.out.println("It is "+currentPlayer.getName()+"'s turn:");

            board.renderBoard();

            System.out.println("1------------------------------------------------------------------------");
            currentPlayer.turn(board);

            System.out.println("2------------------------------------------------------------------------");
            currentPlayer.setInCheck(board.check(currentPlayer.isPlayer1()));

            System.out.println("3------------------------------------------------------------------------");
            if(board.check(otherPlayer.isPlayer1())) {
                //output the players move caused a check
                System.out.println(otherPlayer.getName()+" is in check");
            }

            player1Turn = !player1Turn;

            System.out.println("4------------------------------------------------------------------------");
        }while(!board.checkmate(currentPlayer.isPlayer1()));
        System.out.println(otherPlayer.getName()+" is in checkmate. "+currentPlayer.getName()+" is the victor!!");

        //Ask if they want to play again or quit
        System.out.println("Would you like to play again?");
        System.out.println("Enter 1. Yes or 2. No");
        Scanner scanner = new Scanner(System.in);
        int playAgain = scanner.nextInt();
        if(playAgain == 1){
            this.restart();
        }
        else{
            this.exit();
        }
    }

    private void restart(){
        this.play();

    }

    private void exit(){

    }

    private void gameSetUp(){
        System.out.println("game setup");
        this.playersSetUp();
        this.boardSetUp();
    }

    private void boardSetUp(){
        //init board and board.setUp
        System.out.println("board setup");
        board = new Board();
        board.setup();
    }

    private void playersSetUp(){
        System.out.println("player setup");
        this.isAI = this.userInputForAIChoice();

        System.out.println("Player1: ");
        this.player1 = new Human(this.getPlayerName(), true);

        //if isAI set up AI vs Human
        if(isAI){
            this.player2 = new AI(this.userInputForAIDifficulty());
        }
        //else Human vs Human
        else{
            System.out.println("Player2: ");
            this.player2 = new Human(this.getPlayerName(),  false);
        }
    }

    private boolean userInputForAIChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0. AI vs Human, 1. Human vs Human");
        int aiChoiceInt = scanner.nextInt();
        return aiChoiceInt == 0;
    }

    private int userInputForAIDifficulty(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 3. For Easy, 5. Medium, 7. Hard");
        return scanner.nextInt();
    }

    private String getPlayerName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Human Name");
        return scanner.nextLine();
    }

    public boolean player1GoesFirst(){
        Random rand = new Random();
        //if 0:false, 1:true
        return true;
    }
}
