package Chess.UnitTest;

import Chess.board.Board;
import Chess.board.Player;

import java.util.Scanner;


public class TryMyChess {

    private static Scanner input = new Scanner(System.in);
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static int fromX, fromY, toX, toY;

    public static void main(String[] args) {
        welcome();
        setUp();
        Board playingBoard = new Board(whitePlayer, blackPlayer);
        int counter = 1;
        drawTheBoard(playingBoard);
        while (true) {
            if (counter % 2 == 0) {
                System.out.println(blackPlayer.getName() + "'s turn!");
                makeATurn();
                if (!playingBoard.play(blackPlayer, fromX, fromY, toX, toY)) {
                    System.out.println("Try again!");
                    continue;
                }
            } else {
                System.out.println(whitePlayer.getName() + "'s turn!");
                makeATurn();
                if (!playingBoard.play(whitePlayer, fromX, fromY, toX, toY)) {
                    System.out.println("Try again!");
                    continue;
                }
            }
            counter++;
            drawTheBoard(playingBoard);
        }
    }

    private static void makeATurn() throws NumberFormatException{
        System.out.println("Coordinate X of figure you would like to move?");
        fromX = Integer.parseInt(input.nextLine());
        System.out.println("Coordinate Y of figure you would like to move?");
        fromY = Integer.parseInt(input.nextLine());
        System.out.println("Move onto (X)?");
        toX = Integer.parseInt(input.nextLine());
        System.out.println("Move onto (Y)?");
        toY = Integer.parseInt(input.nextLine());
    }

    private static void welcome() {
        System.out.println("Greetings, your majesty.");
        System.out.println("What is the name of the first player?");
        whitePlayer = new Player(input.nextLine());
        System.out.println("And the name of the second one?");
        blackPlayer = new Player(input.nextLine());
    }

    private static void setUp() {
        System.out.println("It is time for a mind battle!");
        System.out.println(blackPlayer.getName() + " vs " + whitePlayer.getName());
        System.out.println("First shall be " + whitePlayer.getName());
    }

    private static void drawTheBoard(Board playingBoard) {
        System.out.println("------------------------------------------------");
        for (int i = 0; i < playingBoard.getPlayingBoard().length; i++) {
            for (int j = 0; j < playingBoard.getPlayingBoard()[i].length; j++) {
                try {
                    switch (playingBoard.getPlayingBoard()[i][j].getPiece().getType()) {
                        case Pawn:
                            System.out.print('\u2659');
                            break;
                        case Knight:
                            System.out.print('\u2658');
                            break;
                        case Rook:
                            System.out.print('\u2656');
                            break;
                        case Bishop:
                            System.out.print('\u2657');
                            break;
                        case King:
                            System.out.print('\u2654');
                            break;
                        case Queen:
                            System.out.print('\u2655');
                            break;
                    }
                } catch (NullPointerException e) {
                    System.out.print('\u3000');
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------");
    }
}
