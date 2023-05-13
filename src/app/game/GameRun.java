package src.app.game;

import jtamaro.en.IO;
import src.app.game.controller.GameController;
import src.app.game.view.GameView;

public class GameRun {
    public static void run() {
        //TODO: set the run para of game
        IO.interact(Settings.initialState)
          .withRenderer(gameState -> GameView.render(gameState))
          .withKeyPressHandler(GameController::onKeyPress);
    }
}
