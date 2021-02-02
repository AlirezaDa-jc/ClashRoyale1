
package View;

import FileAndUserIO.UserIO;
import Library.Game;
import Utilities.Consts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ClashRoyale extends Application {
    private Game game;


    @Override
    public void start(Stage primaryStage) {

        primaryStage.resizableProperty().set(false);
        Game game = new Game();
        this.game = game;
        BorderPane border = new BorderPane();
        GridPane gPane = new GridPane();
        Display aff = new Display(game, gPane);
        game.addObserver(aff);
        int column = 0;
        int row = 0;
        for (int i = 0; i < game.getState().getCells().length; i++) {
            for (int j = 0; j < game.getState().getCells()[i].length; j++) {
                String imagePath = this.game.getState().getCells()[i][j].getImagePath();
                ImageView img = new ImageView(imagePath);

                gPane.add(img, column++, row);

                if (column >= game.getState().getCells()[i].length) {
                    column = 0;
                    row++;
                }
            }
        }
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.WHITESMOKE);
        primaryStage.setTitle("PACMAN FX");
        primaryStage.setScene(scene);
        UserIO.addEvents(primaryStage, this.game);
        primaryStage.show();
        new Thread(game).start();
    }

    @Override
    public void stop() {
        this.game.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
