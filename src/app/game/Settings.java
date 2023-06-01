package src.app.game;

import jtamaro.en.Sequence;
import src.app.game.state.GameState;
import src.app.game.state.Item;
import src.app.game.state.Tile;

import static jtamaro.en.Sequences.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import jtamaro.en.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings {
    //canvas settings
    public static final double UNIT_HEIGHT = 43;
    public static final double UNIT_WIDTH = UNIT_HEIGHT;
    // the totalLevel should realize automatic calculation!!!
    public static final int totalLevel = 3;
    public static GameState initialState;

    static {
        try {
            initialState =
                new GameState(GameState.fromString(Files.readString(Path.of("game0.txt"))), null, 0)
                    .applyRules();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
