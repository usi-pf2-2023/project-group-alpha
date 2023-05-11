package src.app.game.controller;

import com.sun.source.tree.ReturnTree;
import jtamaro.en.io.KeyboardKey;
import src.app.game.state.GameState;

public class GameController {
    public static GameState onKeyPress(GameState before, KeyboardKey key) {
        // TODO
        return before;
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
