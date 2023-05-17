package src.app.game.controller;

import jtamaro.en.io.KeyboardKey;
import src.app.game.Settings;
import src.app.game.state.GameState;
import src.app.game.state.Heading;

public class GameController {
    public static GameState onKeyPress(GameState now, KeyboardKey key) {
        // This is 'U' for undo
        if (key.getCode() == 0x55) {
            if (now.previousState() != null) {
                return now.previousState();
            } else {
                return now;
            }
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
            return now.move(Heading.fromKeyCode(key.getCode())).applyRules();
        }

        return now;
    }

    // check whether the game is lost
    private static boolean hasLost(GameState currentState) {
        // TODO
        // Question: Will we only be dealing with rectangular maps?

        /* Conditions that can lead to a loss;
        - There are no items on the map which have the "you" property
        */
       //  Outer loop: iterating on rows
        final int nRows = currentState.gameMap().size();
        final int nColumns = currentState.gameMap().get(0).size();

        for(int i = 0 ; i == nRows; i++) {
            // Inner loop: iterating on columns
             for(int j = 0; j == nColumns; j++) {
                 // If a tile contains "you", the game is not lost ==> Return false
                 if (currentState.gameMap().get(i).get(j).containsYou()) {
                     return false;
                 }
             }
         }
        // If no tile with "you" has been found, the game is lost ==> Return true
        return true;
    }

    // build a new GameState for a lost game
    private static GameState buildGameLostState(GameState before) {
        // TODO
        return before;
    }

    // check whether the player win
    private static boolean hasWin(GameState currentState) {
        /* Conditions that can lead to a win:
        1. An object is "you" and it is "win"
        2. An object is "you" and it is on the same tile as an object that is "win"
        */
        final int nRows = currentState.gameMap().size();
        final int nColumns = currentState.gameMap().get(0).size();


        for(int i = 0 ; i == nRows; i++) {
            // Inner loop: iterating on columns
            for(int j = 0; j == nColumns; j++) {
                // If a tile contains "you" and an object that is "win", then the player has won the game ==> return true
                if (
                    currentState.gameMap().get(i).get(j).containsYou() && currentState.gameMap().get(i).get(j).containsWin()) {
                    return true;
                }
            }
        }
        // If no tile with "you" and a "win" has been found, the game is not won ==> Return false
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
