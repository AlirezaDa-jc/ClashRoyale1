package View;

import Library.Game;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class Display implements Observer {

    private final Game game;
    private final GridPane pane;

    public Display(Game game, GridPane pane) {
        this.game = game;
        this.pane = pane;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (this.checkLose()) {
            return;
        }

        this.game.moveEntities();
        this.game.attackTowers();
        this.updateImageViews();
    }

    private boolean checkLose() {

        if (this.game.getState().checkPlayer1Lost()) {
            this.game.stop();
            Platform.runLater(() -> this.pane.getChildren().clear());
            Text label = new Text("Game over !");
            label.setStyle("-fx-font: 70 arial;");
            Text label1 = new Text("Player2 Wins");
            label1.setStyle("-fx-font: 70 arial;");
            return true;
        }else  if (this.game.getState().checkPlayer2Lost()) {
            this.game.stop();
            Platform.runLater(() -> this.pane.getChildren().clear());
            Text label = new Text("Game over !");
            label.setStyle("-fx-font: 70 arial;");
            Text label1 = new Text("Player1 Wins");
            label1.setStyle("-fx-font: 70 arial;");
            return true;
        }
        return false;
    }


    private void updateImageViews() {
        for (int i = 0; i < this.game.getState().getCells().length; i++) {
            for (int j = 0; j < this.game.getState().getCells()[i].length; j++) {
                Node imgAsNode = this.getNodeByRowColumnIndex(i, j, pane);
                ImageView oldImage = (ImageView) imgAsNode;
                Image newImage = new Image(this.game.getState().getCells()[i][j].getImagePath());
                oldImage.setImage(newImage);
            }
        }
    }


    private Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
}
