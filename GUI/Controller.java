package Chess.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.awt.event.ActionEvent;

import static Chess.GUI.Main.*;

public class Controller {
    private static int counter = 0;

    public void handleOnClick(javafx.event.ActionEvent actionEvent) {
        Button tempButton = (Button) actionEvent.getSource();
        if (counter == 0) {
            fromX = GridPane.getColumnIndex(tempButton.getParent());
            fromY = GridPane.getRowIndex(tempButton.getParent());
            counter++;
        } else {
            toX = GridPane.getColumnIndex(tempButton.getParent());
            toY = GridPane.getRowIndex(tempButton.getParent());
            System.out.println(fromX + " " + fromY + " " + toX + " " + toY);
            counter = 0;
            play();
        }
    }
}
