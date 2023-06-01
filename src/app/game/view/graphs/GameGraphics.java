package src.app.game.view.graphs;

import jtamaro.en.Graphic;
import src.app.game.Settings;

import java.util.HashMap;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.Colors.*;

public class GameGraphics {

    public static Graphic generateTestGraphic(String s) {
        String ss[] = s.split("_");
        Graphic ret = emptyGraphic();
        for (int i = 0; i < ss.length; ++i) {
            ret = above(ret, text(ss[i], "arial", 18, WHITE));
        }
        return ret;
    }

    public static final HashMap<String, Graphic> BOUNDARY = new HashMap() {
        {
            put("normal", rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, rgb(0x21, 0x28, 0x3e)));
        }
    };

    // Use 0 and 1 to represent the presence or absence of walls in the corresponding directions
    // in the order of Right-Bottom-Left-Top
    private static WallGraphics wallGraphics = new WallGraphics();
    public static final HashMap<String, Graphic> ICON_WALL = new HashMap() {
        {
            put("0000", wallGraphics.isolated());
            put("0001", wallGraphics.endCornerTop());
            put("0010", wallGraphics.endCornerLeft());
            put("0011", wallGraphics.topLeftCorner());
            put("0100", wallGraphics.endCornerBottom());
            put("0101", wallGraphics.vertical());
            put("0110", wallGraphics.bottomLeftCorner());
            put("0111", wallGraphics.linkedLeftTopBottom());
            put("1000", wallGraphics.endCornerRight());
            put("1001", wallGraphics.topRightCorner());
            put("1010", wallGraphics.horizontal());
            put("1011", wallGraphics.linkedRightLeftTop());
            put("1100", wallGraphics.bottomRightCorner());
            put("1101", wallGraphics.linkedRightTopBottom());
            put("1110", wallGraphics.linkedRightLeftBottom());
            put("1111", wallGraphics.linkedAllFour());
        }
    };

    public static final HashMap<String, Graphic> TEXT_WALL = new HashMap() {
        {
            put("dark", wallGraphics.off());
            put("light", wallGraphics.on());
            put("cancel", wallGraphics.cancel());
        }
    };

    private static BabaGraphics babaGraphics = new BabaGraphics();
    public static final HashMap<String, Graphic> ICON_BABA = new HashMap() {
        {
            put("east", babaGraphics.babaEast());
            put("west", babaGraphics.babaWest());
            put("north", babaGraphics.babaNorth());
            put("south", babaGraphics.babaSouth());
        }
    };

    public static final HashMap<String, Graphic> TEXT_BABA = new HashMap() {
        {
            put("dark", babaGraphics.off());
            put("light", babaGraphics.on());
            put("cancel", babaGraphics.cancel());
        }
    };

    private static FlagGraphics flagGraphics = new FlagGraphics();
    public static final HashMap<String, Graphic> ICON_FLAG = new HashMap() {
        {
            put("normal", flagGraphics.flag());
        }
    };

    public static final HashMap<String, Graphic> TEXT_FLAG = new HashMap() {
        {
            put("dark", flagGraphics.off());
            put("light", flagGraphics.on());
            put("cancel", flagGraphics.cancel());
        }
    };

    private static RockGraphics rockGraphics = new RockGraphics();
    public static final HashMap<String, Graphic> ICON_ROCK = new HashMap() {
        {
            put("normal", rockGraphics.rock());
        }
    };

    public static final HashMap<String, Graphic> TEXT_ROCK = new HashMap() {
        {
            put("dark", rockGraphics.off());
            put("light", rockGraphics.on());
            put("cancel", rockGraphics.cancel());
        }
    };

    private static TextGraphics isGraphics = new IsGraphics();
    public static final HashMap<String, Graphic> TEXT_IS = new HashMap() {
        {
            put("dark", isGraphics.off());
            put("light", isGraphics.on());
            put("cancel", isGraphics.cancel());
        }
    };

    private static TextGraphics winGraphics = new WinGraphics();
    public static final HashMap<String, Graphic> TEXT_WIN = new HashMap() {
        {
            put("dark", winGraphics.off());
            put("light", winGraphics.on());
        }
    };

    private static TextGraphics pushGraphics = new PushGraphics();
    public static final HashMap<String, Graphic> TEXT_PUSH = new HashMap() {
        {
            put("dark", pushGraphics.off());
            put("light", pushGraphics.on());
        }
    };

    private static TextGraphics youGraphics = new YouGraphics();
    public static final HashMap<String, Graphic> TEXT_YOU = new HashMap() {
        {
            put("dark", youGraphics.off());
            put("light", youGraphics.on());
        }
    };

    private static TextGraphics stopGraphics = new StopGraphics();
    public static final HashMap<String, Graphic> TEXT_STOP = new HashMap() {
        {
            put("dark", stopGraphics.off());
            put("light", stopGraphics.on());
        }
    };

    private static LevelGraphics levelGraphics = new LevelGraphics();
    public static final HashMap<String, Graphic> LEVEL_1 = new HashMap() {
        {
            put("normal", levelGraphics.Level1());
        }
    };

    public static final HashMap<String, Graphic> LEVEL_2 = new HashMap() {
        {
            put("normal", levelGraphics.Level2());
        }
    };

    public static final HashMap<String, Graphic> LEVEL_3 = new HashMap() {
        {
            put("normal", levelGraphics.Level3());
        }
    };
}
