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
        if (this.checkLose() || this.checkWin()) {
            return;
        }

        this.game.moveEntities();
        this.game.attackTowers();
        this.updateImageViews();
    }

    private boolean checkLose() {

        if (this.game.getState().getLost()) {
            this.game.stop();
            Platform.runLater(() -> this.pane.getChildren().clear());
//            Text label = new Text("Game over !");
//            label.setStyle("-fx-font: 70 arial;");
//            String score = String.valueOf(Game.getScore());
//            Text label1 = new Text("Score: " + score);
//            label.setStyle("-fx-font: 50 arial;");
//            Platform.runLater(() -> {
//                this.pane.add(label, 0, 0);
//                this.pane.add(label1, 1, 50);
//                File file = new File("scores.txt");
//                try {
//                    FileWriter fr = new FileWriter(file, true);
//                    fr.write(score+"\n");
//
//                    fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

            return true;
        }
        return false;
    }

    private boolean checkWin() {
//        if (this.game.getState().noMoreGums()) {
//            this.game.stop();
//            Platform.runLater(() -> this.pane.getChildren().clear());
//            Text label = new Text("You won !");
//            label.setStyle("-fx-font: 90 arial;");
//            Platform.runLater(() -> this.pane.add(label, 0, 0));
//
//            return true;
//        }
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
