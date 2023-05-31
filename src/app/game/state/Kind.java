package src.app.game.state;

import jtamaro.en.Graphic;
import src.app.game.view.graphs.GameGraphics;

import java.util.HashMap;

/**
 * Contains all the types of objects
 */
public enum Kind {
    // All possible instances of type Kind
    BOUNDARY(GameGraphics.BOUNDARY, false, false),

    ICON_WALL(GameGraphics.ICON_WALL, false, false),
    ICON_BABA(GameGraphics.ICON_BABA, false, false),
    ICON_FLAG(GameGraphics.ICON_FLAG, false, false),
    ICON_ROCK(GameGraphics.ICON_ROCK, false, false),

    TEXT_IS(GameGraphics.TEXT_IS, false, false),

    TEXT_WALL(GameGraphics.TEXT_WALL, true, false),
    TEXT_BABA(GameGraphics.TEXT_BABA, true, false),
    TEXT_FLAG(GameGraphics.TEXT_FLAG, true, false),
    TEXT_ROCK(GameGraphics.TEXT_ROCK, true, false),

    TEXT_WIN(GameGraphics.TEXT_WIN, false, true),
    TEXT_PUSH(GameGraphics.TEXT_PUSH, false, true),
    TEXT_YOU(GameGraphics.TEXT_YOU, false, true),
    TEXT_STOP(GameGraphics.TEXT_STOP, false, true),

    LEVEL_1(GameGraphics.LEVEL_1, false, false),
    LEVEL_2(GameGraphics.LEVEL_2, false, false),
    LEVEL_3(GameGraphics.LEVEL_3, false, false);

    // Class variables
    private HashMap<String, Graphic> graphic_map;
    private boolean isObjectText;
    private boolean isStateText;

    // Constructors
    Kind(HashMap<String, Graphic> graphic_map, boolean isObjectText, boolean isStateText) {
        this.graphic_map = graphic_map;
        this.isObjectText = isObjectText;
        this.isStateText = isStateText;
    }

    public Kind textToIcon() {
        if (this == TEXT_BABA) {
            return ICON_BABA;
        } else if (this == TEXT_FLAG) {
            return ICON_FLAG;
        } else if (this == TEXT_ROCK) {
            return ICON_ROCK;
        } else if (this == TEXT_WALL) {
            return ICON_WALL;
        }
        assert false;
        return null;
    }

    public HashMap<String, Graphic> getGraphic_map() {
        return graphic_map;
    }

    public boolean isObjectText() {
        return isObjectText;
    }

    public boolean isStateText() {
        return isStateText;
    }

}
