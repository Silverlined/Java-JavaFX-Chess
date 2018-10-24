package Chess.GUI;

import Chess.board.Board;
import Chess.board.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @FXML
    static AnchorPane content;

    private static Player whitePlayer, blackPlayer;
    static int fromX, fromY, toX, toY;
    private static Board playingBoard;
    private static boolean isWhiteOnTurn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Chess.GUI.StartMenu.display(whitePlayer = new Player(), blackPlayer = new Player());
        SetUpGUI(primaryStage);
        playingBoard = new Board(whitePlayer, blackPlayer);
        isWhiteOnTurn = true;
    }

    private void SetUpGUI(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chess/GUI/UI.fxml"));
        Controller controller = loader.getController();
        loader.setRoot(content);
        Scene scene = new Scene(loader.load(), 600, 600);
        primaryStage.setTitle("ChessThisOut");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static boolean play() {
        if (isWhiteOnTurn) {
            return whitePlayerTurn();
        } else {
            return blackPlayerTurn();
        }
    }

    private static boolean whitePlayerTurn() {
        if (!playingBoard.play(whitePlayer, fromX, fromY, toX, toY, blackPlayer)) {
            System.out.println("Try again!");
            return false;
        }
        showLastMadeMove(whitePlayer, fromX, fromY, toX, toY);
        if (playingBoard.isCheck(whitePlayer, blackPlayer)) {
            System.out.println("Check!");
            if (playingBoard.isCheckMate(whitePlayer, blackPlayer).equals("true")) {
                MessageBox.display("CheckMate");
            }
        }
        isWhiteOnTurn = false;
        return true;
    }

    private static boolean blackPlayerTurn() {
        if (!playingBoard.play(blackPlayer, fromX, fromY, toX, toY, whitePlayer)) {
            System.out.println("Try again!");
            return false;
        }
        showLastMadeMove(blackPlayer, fromX, fromY, toX, toY);
        if (playingBoard.isCheck(blackPlayer, whitePlayer)) {
            System.out.println("Check!");
            if (playingBoard.isCheckMate(blackPlayer, whitePlayer).equals("true")) {
                MessageBox.display("CheckMate");
            }
        }
        isWhiteOnTurn = true;
        return true;
    }

    private static void showLastMadeMove(Player playerOnTurn, int fromX, int fromY, int toX, int toY) {
        System.out.println(playerOnTurn.getName() + "'s last move:");
        printTheMove(fromX, fromY);
        System.out.print(" -> ");
        printTheMove(toX, toY);
        System.out.println("\n");
    }

    private static void printTheMove(int x, int y) {
        switch (x) {
            case 0:
                System.out.print("A");
                break;
            case 1:
                System.out.print("B");
                break;
            case 2:
                System.out.print("C");
                break;
            case 3:
                System.out.print("D");
                break;
            case 4:
                System.out.print("E");
                break;
            case 5:
                System.out.print("F");
                break;
            case 6:
                System.out.print("G");
                break;
            case 7:
                System.out.print("H");
                break;
        }
        System.out.print(String.valueOf(y + 1));
    }
}
