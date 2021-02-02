
package FileAndUserIO;

import Library.Game;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;


public class UserIO {
    public static void addEvents(Stage currentStage, Game g) {
        AtomicInteger i = new AtomicInteger();
        currentStage.getScene().setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.Q) {
                    g.makeGoblin("player1");
                } else if (event.getCode() == KeyCode.W) {
                    g.makeShield("player1");
                } else if (event.getCode() == KeyCode.E) {
                    g.makeKnight("player1");
                } else if (event.getCode() == KeyCode.R) {
                    g.makeArcher("player1");
                } else if (event.getCode() == KeyCode.U) {
                    g.makeGoblin("player2");
                } else if (event.getCode() == KeyCode.I) {
                    g.makeShield("player2");
                } else if (event.getCode() == KeyCode.O) {
                    g.makeKnight("player2");
                } else if (event.getCode() == KeyCode.P) {
                    g.makeArcher("player2");
                }
        });
    }

}
