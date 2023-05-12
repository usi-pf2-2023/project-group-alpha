package src.app.game.graphs;

import jtamaro.en.Graphic;
import src.app.game.Settings;

import java.util.HashMap;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.Colors.*;

public class GameGraphics {
    public static final HashMap<String, Graphic> BOUNDARY = new HashMap() {
        {
            put("dark", rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, rgb(0x21, 0x28, 0x3e)));
        }
    };

    // TODO: design the graphic of ICON_WALL
    public static final HashMap<String, Graphic> ICON_WALL = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_WALL
    public static final HashMap<String, Graphic> TEXT_WALL = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of ICON_BABA
    public static final HashMap<String, Graphic> ICON_BABA = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_BABA
    public static final HashMap<String, Graphic> TEXT_BABA = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of ICON_FLAG
    public static final HashMap<String, Graphic> ICON_FLAG = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_FLAG
    public static final HashMap<String, Graphic> TEXT_FLAG = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of ICON_ROCK
    public static final HashMap<String, Graphic> ICON_ROCK = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_ROCK
    public static final HashMap<String, Graphic> TEXT_ROCK = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_IS
    public static final HashMap<String, Graphic> TEXT_IS = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_WIN
    public static final HashMap<String, Graphic> TEXT_WIN = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_PUSH
    public static final HashMap<String, Graphic> TEXT_PUSH = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_YOU
    public static final HashMap<String, Graphic> TEXT_YOU = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };

    // TODO: design the graphic of TEXT_STOP
    public static final HashMap<String, Graphic> TEXT_STOP = new HashMap() {
        {
            put("dark", emptyGraphic());
        }
    };
}
