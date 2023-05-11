package src.app.game;

import jtamaro.en.IO;
import src.app.game.controller.GameController;

public class GameRun {
    public static void run() {
        //TODO: set the run para of game
        IO.interact(Settings.initialState)
          .withRenderer(gameState -> gameState.render())
          .withKeyPressHandler(GameController::onKeyPress);
    }
}
