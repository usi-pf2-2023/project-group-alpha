package src.app.game.controller;


import jtamaro.en.io.KeyboardKey;

import src.app.game.state.GameState;

import src.app.game.state.Tile;
import src.app.game.Settings;
import src.app.game.state.Heading;

import jtamaro.en.Sequence;
import jtamaro.en.Sequences;

import jtamaro.en.Sequence.*;

import static jtamaro.en.Sequences.*;

import jtamaro.en.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GameController {
    public static GameState onKeyPress(GameState now, KeyboardKey key) {
        // Check if we are currently in a game:
        if (hasWon(now.gameMap())) {
            // Pressing "SPACE" triggers the next level transition in a winning state
            if (key.getCode() == 0x20) {
                int nextLevel = (now.level() + 1) % (Settings.totalLevel + 1);
                return now.updateLevel(nextLevel);
            }
        } else {
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
                return now.updateLevel(now.level());
            }
            // Key arrows: pressing them triggers a move (if it is possible) to that direction (left, up, down, right, left)
            if (key.getCode() == KeyboardKey.UP || key.getCode() == KeyboardKey.DOWN ||
                key.getCode() == KeyboardKey.LEFT || key.getCode() == KeyboardKey.RIGHT) {
                return now
                    // Move check: if a move is possible, then make the move
                    .move(Heading.fromKeyCode(key.getCode()))
                    // Apply and generate the rules according to the new gameMap
                    .applyRules();
            }
        }
        // Pressing "SPACE" triggers entering the according level
        if (key.getCode() == 0x20 && now.level() == 0) {
            int nextLevel = stepOnLevel(now.gameMap());
            if (nextLevel > 0) return now.updateLevel(nextLevel);
        }
        // Pressing "H" triggers come back to select_menu
        if (key.getCode() == 0x48) {
            return now.updateLevel(0);
        }
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

        /* Conditions that can lead to a loss;
        - There are no items on the map which have the "you" property
        */
        //  Outer loop: iterating on rows
        final int nRows = gameMap.size();
        final int nColumns = gameMap.get(0).size();

        for (int i = 0; i < nRows; i++) {
            // Inner loop: iterating on columns
            for (int j = 0; j < nColumns; j++) {
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

        for (int i = 0; i < nRows; i++) {
            // Inner loop: iterating on columns
            for (int j = 0; j < nColumns; j++) {
                // If a tile contains "you" and an object that is "win", then the player has won the game ==> return true
                if (gameMap.get(i).get(j).containsYou() && gameMap.get(i).get(j).containsWin()) {
                    return true;
                }
            }
        }
        // If no tile with "you" and a "win" has been found, the game is not won ==> Return false
        return false;
    }

    private static int stepOnLevel(ArrayList<ArrayList<Tile>> gameMap) {
        final int nRows = gameMap.size();
        final int nColumns = gameMap.get(0).size();

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                if (gameMap.get(i).get(j).containsYou() && gameMap.get(i).get(j).containsLevel1()) {
                    return 1;
                }
                if (gameMap.get(i).get(j).containsYou() && gameMap.get(i).get(j).containsLevel2()) {
                    return 2;
                }
                if (gameMap.get(i).get(j).containsYou() && gameMap.get(i).get(j).containsLevel3()) {
                    return 3;
                }
            }
        }

        return 0;
    }

}
