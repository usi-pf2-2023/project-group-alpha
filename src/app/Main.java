package src.app;

import jtamaro.en.IO;
import src.app.game.Settings;
import src.app.game.controller.GameController;
import src.app.game.view.GameView;
import src.app.game.view.graphs.ImageConverter;

public class Main {
    public static void main(String[] args) {
        IO.interact(Settings.initialState)
          .withBackground(
              ImageConverter.toHints("mainBackground.png", 39.52 * Settings.UNIT_WIDTH, 27.52 * Settings.UNIT_HEIGHT))
          .withRenderer(gameState -> GameView.render(gameState))
          .withKeyPressHandler(GameController::onKeyPress)
          .run();
    }
}