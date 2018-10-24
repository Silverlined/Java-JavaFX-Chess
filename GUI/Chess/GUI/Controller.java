package Chess.GUI;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import static Chess.GUI.Main.*;

public class Controller {
    private final int indexOfButton = 1;
    private static int counter = 0;
    private static StackPane oldPosition;
    private static StackPane newPosition;
    private static Glow effectOnTheFirstTile;
    private static Glow effectOnTheSecondTile;

    public void handleOnClick(javafx.event.ActionEvent actionEvent) {
        Button tempButton = (Button) actionEvent.getSource();
        if (counter == 0) {
            setInitCoordinates(tempButton);
        } else {
            setNewCoordinates(tempButton);
            if(play()) {
                swapChildrenOfChosenPanes();
            }
            removeEffectsTiles();
        }
    }

    private void setNewCoordinates(Button tempButton) {
        newPosition = (StackPane) tempButton.getParent();
        addEffectToNewLocation();
        if (GridPane.getColumnIndex(newPosition) != null) {
            toX = GridPane.getColumnIndex(newPosition);
        } else {
            toX = 0;
        }
        if (GridPane.getRowIndex(newPosition) != null) {
            toY = GridPane.getRowIndex(newPosition);
        } else {
            toY = 0;
        }
        counter = 0;
    }

    private void setInitCoordinates(Button tempButton) {
        oldPosition = (StackPane) tempButton.getParent();
        addEffectToInitTile();
        if (GridPane.getColumnIndex(oldPosition) != null) {
            fromX = GridPane.getColumnIndex(oldPosition);
        } else {
            fromX = 0;
        }
        if (GridPane.getRowIndex(oldPosition) != null) {
            fromY = GridPane.getRowIndex(oldPosition);
        } else {
            fromY = 0;
        }
        counter++;
    }

    private void addEffectToNewLocation() {
        effectOnTheSecondTile = new Glow(0.7);
        newPosition.getChildren().get(0).setEffect(effectOnTheSecondTile);
    }

    private void addEffectToInitTile() {
        effectOnTheFirstTile = new Glow(0.7);
        oldPosition.getChildren().get(0).setEffect(effectOnTheFirstTile);
    }

    private void swapChildrenOfChosenPanes() {
        ObservableList<Node> childrenNewPosition = newPosition.getChildren();
        ObservableList<Node> childrenOldPosition = oldPosition.getChildren();
        ImageView newEmptyImage = getNewImageView();
        Button temp = (Button) newPosition.getChildren().get(indexOfButton);
        newPosition.getChildren().removeAll(childrenNewPosition);
        newPosition.getChildren().addAll(childrenOldPosition);
        oldPosition.getChildren().removeAll(childrenOldPosition);
        oldPosition.getChildren().addAll(newEmptyImage, temp);
    }

    private ImageView getNewImageView() {
        ImageView newEmptyImage = new ImageView();
        newEmptyImage.setFitHeight(75);
        newEmptyImage.setFitWidth(75);
        return newEmptyImage;
    }

    private void removeEffectsTiles() {
        effectOnTheFirstTile.setLevel(0);
        effectOnTheSecondTile.setLevel(0);
    }
}
