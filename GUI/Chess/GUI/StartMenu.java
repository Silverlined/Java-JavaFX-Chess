package Chess.GUI;

import Chess.board.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class StartMenu {
    private static TextField player1 = new TextField();
    private static TextField player2 = new TextField();
    private static Button startButton = new Button("Start");

    public static void display(Player whitePlayer, Player blackPlayer) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Welcome");
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(100);
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        player1.setPromptText("White player name");
        player2.setPromptText("Black player name");
        startButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (player1.getText() == null || player1.getText().trim().equals("")
                        || player2.getText() == null || player2.getText().trim().equals("")) {
                    throw new IllegalArgumentException("Invalid name");
                }
                whitePlayer.setName(player1.getText());
                blackPlayer.setName(player2.getText());
                System.out.println("Let the battle begin!\n" + whitePlayer.getName() + " vs " + blackPlayer.getName());
                primaryStage.close();
            }
        });

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(player1, player2, startButton);

        Scene scene = new Scene(layout, 200, 100);

        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
}
