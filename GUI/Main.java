package Chess.GUI;

import Chess.board.Board;
import Chess.board.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @FXML
    static AnchorPane content;

    private static Player whitePlayer = new Player("Hero"), blackPlayer = new Player("Zeus");
    static int fromX, fromY, toX, toY;
    private static Board playingBoard;
    private static boolean isWhiteOnTurn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
        Controller controller = loader.getController();
        loader.setRoot(content);
        Scene scene = new Scene(loader.load(), 600, 600);
        primaryStage.setTitle("ChessThisOut");
        primaryStage.setScene(scene);
        primaryStage.show();

        playingBoard = new Board(whitePlayer, blackPlayer);
        isWhiteOnTurn = true;
    }

    static void play() {
        if (isWhiteOnTurn) {
            System.out.println("It was " + whitePlayer.getName() + "'s turn!");
            whitePlayerTurn();
            return;
        } else {
            System.out.println("It was " + blackPlayer.getName() + "'s turn!");
            blackPlayerTurn();
            return;
        }
    }

    private static void whitePlayerTurn() {
        if (!playingBoard.play(whitePlayer, fromX, fromY, toX, toY)) {
            System.out.println("Try again!");
            return;
        }
//        if (playingBoard.isCheck(whitePlayer, blackPlayer)) {
//            System.out.println("Check!");
//        }
        isWhiteOnTurn = false;
    }

    private static void blackPlayerTurn() {
        if (!playingBoard.play(blackPlayer, fromX, fromY, toX, toY)) {
            System.out.println("Try again!");
            return;
        }
//        if (playingBoard.isCheck(blackPlayer, whitePlayer)) {
//            System.out.println("Check!");
//        }
        isWhiteOnTurn = true;
    }
}
