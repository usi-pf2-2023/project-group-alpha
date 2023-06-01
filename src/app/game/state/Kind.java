package src.app.game.state;

import jtamaro.en.Graphic;
import src.app.game.view.graphs.GameGraphics;

import java.util.HashMap;

/**
 * {@code Kind}s are used as names for {@code Item}s in the corresponding field. Each {@code Kind} represents one type of objects
 * ({@code Item}s) that can be found in the game. (For example the icon for the rock item, but also the block of text
 * saying "rock"). <br> <br>
 * Using a dedicated class for it allows us to introduce boolean fields that describe the type of "impact" that the object
 * associated to that {@code Kind} could have in a sentence (reminder, sentences are composed of three blocks of text items).
 * Hence we introduce the {@code isObjectText} and {@code isStateText} boolean fields. StateText and ObjectText are not
 * classes but are concepts used to classify text objects.
 * <br>Examples: <br> <br>
 * <b>StateText:</b> "win" and "push" are texts that impact how objects can be interacted with and therefore are referred to as
 * "StateText".For instance " X is win" means that <i>touching X</i> or <i>being X</i> triggers a win.<br><br>
 * <b>ObjectText:</b> They describe texts referring to characters or elements of the decor such as Baba, Wall, Rock and so on.
 * In rules, they either trigger an {@code Item} to turn into another {@code Item} and therefore taking on its properties
 * (i.e can it be pushed? does it trigger a win? and so on), or they are used to define properties of the icons they represent.
 * For example, TEXT_BABA dictates the behaviour of ICON_BABA. <br><br>
 *
 * A {@code Kind} contains a HashMap<String, Graphic>, which represents all of the graphical representations of that
 * {@code Kind} and two {@code boolean} fields determining if the {@code Kind} is a StateText, and if it is an ObjectText.
 * or they
 * A StateText is not a class. It represents the concept of a {@code Kind}
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
