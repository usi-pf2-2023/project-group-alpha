package src.app.game.controller;


import jtamaro.en.io.KeyboardKey;

import src.app.game.state.GameState;

import src.app.game.state.Tile;
import src.app.game.Settings;
import src.app.game.state.Heading;
import src.app.game.state.Stage;
import src.app.game.state.MenuStage;
import src.app.game.state.GameStage;

import jtamaro.en.Sequence;
import jtamaro.en.Sequences;

import jtamaro.en.Sequence.*;
import static jtamaro.en.Sequences.*;
import static src.app.game.state.GameStage.*;
import static src.app.game.state.MenuStage.*;
import jtamaro.en.IO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GameController {
    public static GameState onKeyPress(GameState now, KeyboardKey key) {
        // If we are currently in a game:
        //if(now.currentStage() instanceof GameStage) {

            //if (key.getCode() == KeyboardKey.BACK_SPACE) {

            //}

                // Pressing "U" triggers an undo
            if (key.getCode() == 0x55) {
                if (now.previousState() != null) {
                    return now.previousState();
                } else {
                    return now;
                }
            }
            // Pressing "R" triggers a restart to the initial state of that level
            if (key.getCode() == 0x52) {
                // TODO: restart according to the game level of the AppState
                return Settings.initialState;
            }

            // Key arrows: pressing them triggers a move (if it is possible) to that direction (left, up, down, right, left)
            if (key.getCode() == KeyboardKey.UP ||
                key.getCode() == KeyboardKey.DOWN ||
                key.getCode() == KeyboardKey.LEFT ||
                key.getCode() == KeyboardKey.RIGHT) {
                return now
                    // Move check: if a move is possible, then make the move
                    .move(Heading.fromKeyCode(key.getCode()))
                    // Apply and generate the rules according to the new gameMap
                    .applyRules();
            }
        //}
        // If we are currently in a menu
        /*if(isInGameMenu(now.currentStage())) {
            // Pressing "3"
            // Go to level select
            //
            // Pressing "4" exits the game
        }
        /*
        if(isMainMenu(now.currentStage())) {}
        if(isLevelSelectMenu(now.currentStage())) {}
        if(isWonMenu(now.currentStage())) {}
        if(isLostMenu(now.currentStage())) {}

       */

        return now;
    }

    // check whether the game is lost
    public static boolean hasLost(ArrayList<ArrayList<Tile>> gameMap) {
        // TODO
        // Question: Will we only be dealing with rectangular maps?

        /* Conditions that can lead to a loss;
        - There are no items on the map which have the "you" property
        */
       //  Outer loop: iterating on rows
        final int nRows = gameMap.size();
        final int nColumns = gameMap.get(0).size();

        for(int i = 0 ; i == nRows; i++) {
            // Inner loop: iterating on columns
             for(int j = 0; j == nColumns; j++) {
                 // If a tile contains "you", the game is not lost ==> Return false
                 if (gameMap.get(i).get(j).containsYou()) {
                     return false;
                 }
             }
         }
        // If no tile with "you" has been found, the game is lost ==> Return true
        return true;
    }


    // check whether the player win
    public static boolean hasWon(ArrayList<ArrayList<Tile>> gameMap) {
        /* Conditions that can lead to a win:
        1. An object is "you" and it is "win"
        2. An object is "you" and it is on the same tile as an object that is "win"
        */
        final int nRows = gameMap.size();
        final int nColumns = gameMap.get(0).size();

        for(int i = 0 ; i == nRows; i++) {
            // Inner loop: iterating on columns
            for(int j = 0; j == nColumns; j++) {
                // If a tile contains "you" and an object that is "win", then the player has won the game ==> return true
                if (
                    gameMap.get(i).get(j).containsYou() && gameMap.get(i).get(j).containsWin()) {
                    return true;
                }
            }
        }
        // If no tile with "you" and a "win" has been found, the game is not won ==> Return false
        return false;
    }

    /**
     * GameController.buildGameWinState() takes a GameState to turn it into a winning GameState.
     */
    // build a new GameState for a win game
    public static GameState buildGameWinState(GameState before) {
        // TODO: Test
        return new GameState(
            before.gameMap(),
            before.previousState(),
            // The new stage is the "WON_MENU"
            MenuStage.WON_MENU
        );
    }

    // build the next GameState for a running game
    private static GameState buildNextGameState(GameState before) {
        // TODO
        return before;
    }

    /**
     * GameController.buildGameLostState() takes in a GameState and returns a losing GameState
     */
    // build a new GameState for a lost game
    public static GameState buildGameLostState(GameState before) {
        // TODO
        return new GameState(
            before.gameMap(),
            before.previousState(),
            // The new stage is the "lost menu"
            MenuStage.LOST_MENU
        );
    }




    /**
     * GameState.buildInGameMenu() takes a GameState and changes its stage to an in game menu.
     * @param before is the original GameState
     * @return a new GameState that is in the "in game menu" stage.
     */
    /*
    public static GameState buildInGameMenu(GameState before) {
        return new GameState(
            before.gameMap(),
            before.previousState(),
            before.gameWon(),
            before.gameLost(),
            MenuStage.IN_GAME_MENU
        );
    }


    public static GameState buildMainMenu(GameState before) {
        return new GameState(
            before.gameMap(),
            before.previousState(),
            before.gameWon(),
            before.gameLost(),
            MenuStage.MAIN_MENU
        );
    }
    public static GameState buildLevelSelectMenu(GameState before) {
        return new GameState(
            before.gameMap(),
            before.previousState(),
            before.gameWon(),
            before.gameLost(),
            MenuStage.LEVEL_SELECT_MENU
        );
    }


    //public static GameState buildLevelOne() {
     //   return new GameState(GameState.fromString(Files.readString(Path.of("game1.txt")))

   // )
   // )
   // }
   */

}
