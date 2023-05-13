package src.app.game.controller;

import jtamaro.en.io.KeyboardKey;
import src.app.game.Settings;
import src.app.game.state.GameState;
import src.app.game.state.Heading;

public class GameController {
    public static GameState onKeyPress(GameState now, KeyboardKey key) {
        // This is 'U' for undo
        if (key.getCode() == 0x55) {
            return now.previousState();
        }

        //This is 'R' for restart
        if (key.getCode() == 0x52) {
            // TODO: restart according to the game level of the AppState
            return Settings.initialState;
        }

        // directions
        if (key.getCode() == KeyboardKey.UP ||
            key.getCode() == KeyboardKey.DOWN ||
            key.getCode() == KeyboardKey.LEFT ||
            key.getCode() == KeyboardKey.RIGHT) {
            return now.move(Heading.fromKeyCode(key.getCode()));
        }

        return now;
    }

    // check whether the game is lost
    private static boolean hasLost(GameState currentState) {
        // TODO
        return false;
    }

    // build a new GameState for a lost game
    private static GameState buildGameLostState(GameState before) {
        // TODO
        return before;
    }

    // check whether the player win
    private static boolean hasWin(GameState currentState) {
        // TODO
        return false;
    }

    // build a new GameState for a win game
    private static GameState buildGameWinState(GameState before) {
        // TODO
        return before;
    }

    // build the next GameState for a running game
    private static GameState buildNextGameState(GameState before) {
        // TODO
        return before;
    }
}
