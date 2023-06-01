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
    public static final HashMap<String, Graphic> ICON_WALL = new HashMap() {
        {
            put("0000", WallGraphics.isolated());
            put("0001", WallGraphics.endCornerTop());
            put("0010", WallGraphics.endCornerLeft());
            put("0011", WallGraphics.topLeftCorner());
            put("0100", WallGraphics.endCornerBottom());
            put("0101", WallGraphics.vertical());
            put("0110", WallGraphics.bottomLeftCorner());
            put("0111", WallGraphics.linkedLeftTopBottom());
            put("1000", WallGraphics.endCornerRight());
            put("1001", WallGraphics.topRightCorner());
            put("1010", WallGraphics.horizontal());
            put("1011", WallGraphics.linkedRightLeftTop());
            put("1100", WallGraphics.bottomRightCorner());
            put("1101", WallGraphics.linkedRightTopBottom());
            put("1110", WallGraphics.linkedRightLeftBottom());
            put("1111", WallGraphics.linkedAllFour());
        }
    };

    // TODO: design the graphic of TEXT_WALL
    public static final HashMap<String, Graphic> TEXT_WALL = new HashMap() {
        {
            put("dark", WallGraphics.wallOff());
            put("light", WallGraphics.wallOn());
            put("cancel", WallGraphics.wallCancel());
        }
    };

    // TODO: design the graphic of ICON_BABA
    public static final HashMap<String, Graphic> ICON_BABA = new HashMap() {
        {
            put("east", BabaGraphics.babaEast());
            put("west", BabaGraphics.babaWest());
            put("north", BabaGraphics.babaNorth());
            put("south", BabaGraphics.babaSouth());
        }
    };

    // TODO: design the graphic of TEXT_BABA
    public static final HashMap<String, Graphic> TEXT_BABA = new HashMap() {
        {
            put("dark", BabaGraphics.babaOff());
            put("light", BabaGraphics.babaOn());
            put("cancel", BabaGraphics.babaCancel());
        }
    };

    // TODO: design the graphic of ICON_FLAG
    public static final HashMap<String, Graphic> ICON_FLAG = new HashMap() {
        {
            put("normal", FlagGraphics.flag());
        }
    };

    // TODO: design the graphic of TEXT_FLAG
    public static final HashMap<String, Graphic> TEXT_FLAG = new HashMap() {
        {
            put("dark", FlagGraphics.flagOff());
            put("light", FlagGraphics.flagOn());
            put("cancel", FlagGraphics.flagCancel());
        }
    };

    // TODO: design the graphic of ICON_ROCK
    public static final HashMap<String, Graphic> ICON_ROCK = new HashMap() {
        {
            put("normal", RockGraphics.rock());
        }
    };

    // TODO: design the graphic of TEXT_ROCK
    public static final HashMap<String, Graphic> TEXT_ROCK = new HashMap() {
        {
            put("dark", RockGraphics.rockOff());
            put("light", RockGraphics.rockOn());
            put("cancel", RockGraphics.rockCancel());
        }
    };

    // TODO: design the graphic of TEXT_IS
    public static final HashMap<String, Graphic> TEXT_IS = new HashMap() {
        {
            put("dark", IsGraphics.isOff());
            put("light", IsGraphics.isOn());
            put("cancel", IsGraphics.isCancel());
        }
    };

    // TODO: design the graphic of TEXT_WIN
    public static final HashMap<String, Graphic> TEXT_WIN = new HashMap() {
        {
            put("dark", WinGraphics.winOff());
            put("light", WinGraphics.winOn());
        }
    };

    // TODO: design the graphic of TEXT_PUSH
    public static final HashMap<String, Graphic> TEXT_PUSH = new HashMap() {
        {
            put("dark", PushGraphics.pushOff());
            put("light", PushGraphics.pushOn());
        }
    };

    // TODO: design the graphic of TEXT_YOU
    public static final HashMap<String, Graphic> TEXT_YOU = new HashMap() {
        {
            put("dark", YouGraphics.youOff());
            put("light", YouGraphics.youOn());
        }
    };

    // TODO: design the graphic of TEXT_STOP
    public static final HashMap<String, Graphic> TEXT_STOP = new HashMap() {
        {
            put("dark", StopGraphics.stopOff());
            put("light", StopGraphics.stopOn());
        }
    };

    public static final HashMap<String, Graphic> LEVEL_1 = new HashMap() {
        {
            put("normal", LevelGraphics.level1());
        }
    };

    public static final HashMap<String, Graphic> LEVEL_2 = new HashMap() {
        {
            put("normal", LevelGraphics.level2());
        }
    };

    public static final HashMap<String, Graphic> LEVEL_3 = new HashMap() {
        {
            put("normal", LevelGraphics.level3());
        }
    };
}
