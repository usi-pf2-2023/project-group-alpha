package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;

import static jtamaro.en.Sequences.*;

import java.util.ArrayList;
import java.util.HashMap;

public record Item(Kind name, // describes what kind of object it is
                   boolean light, // true if the object is one part of an effective rule
                   boolean cancel, // true if the item is a part of an invalid rule
                   boolean stop, // true if the object cannot be traversed
                   boolean push, // true if the object can be pushed
                   boolean you, // true if that object is controlled by the player
                   boolean win, // true if touching the item triggers a win
                   Heading heading
                   // indicates where the item is facing. By default, all items have a Heading.SOUTH heading
) {


    public Item applyRules(HashMap<Kind, ArrayList<Kind>> stateMap) {
        boolean stop = false, push = false, you = false, win = false;
        if (stateMap.containsKey(name)) {
            for (Kind kind : stateMap.get(name)) {
                if (kind == Kind.TEXT_STOP) {
                    stop = true;
                } else if (kind == Kind.TEXT_PUSH) {
                    push = true;
                } else if (kind == Kind.TEXT_YOU) {
                    you = true;
                } else if (kind == Kind.TEXT_WIN) {
                    win = true;
                } else {
                    assert false;
                }
            }
        }
        return new Item(name, light, cancel, stop, push, you, win, heading);
    }

    public Item setName(Kind name) {
        return new Item(name, light, cancel, stop, push, you, win, heading);
    }

    // set text Items to Dark
    public Item setToDark() {
        return new Item(name, false, cancel, true, true, you, win, heading);
    }

    public Item setToCancel() {
        return new Item(name, true, true, true, true, you, win, heading);
    }

    public Item setToReactive() {
        return new Item(name, true, false, true, true, you, win, heading);
    }

    /**
     * Given {@code this}, determines if {@code this} is an icon of Baba (i.e {@code Kind.ICON_BABA}) or not
     * @return {@code true} if it is the case, {@code false} otherwise.
     */
    public boolean isIconBaba() {
        return this.name().equals(Kind.ICON_BABA);
    }
    /**
     * Given {@code this}, determines if {@code this} is a wall icon (i.e {@code Kind.ICON_WALL}) or not.
     * @return {@code true} if it is the case, {@code false} otherwise.
     */
    public boolean isIconWall() {
        return this.name().equals(Kind.ICON_WALL);
    }

    /**
     * Given {@code this}, determines if {@code this} is a text or not.
     * @return {@code true} if it is the case, {@code false} otherwise.
     */
    public boolean isText() {
        return this.name().isObjectText() || this.name().isStateText() || this.name() == Kind.TEXT_IS;
    }

    /**
     * Given a {@code Heading} creates a new {@code Item} with the same fields as {@code this} but heading equal to
     * {@code heading} if {@code this.you()} is {@code true} (i.e if {@code this} is controlled by the player.
     *
     * This method is called during move operations (cf {@code GameState} class) to assess whether the heading of the
     * object moved (i.e {@code this}), thus its appearance, must change.
     * Currently, the only {@code Item} that sees graphical changes following this is {@code ICON_BABA}.
     *
     * @param heading is the {@code Heading} of the new {@code Item}. It is obtained through keyboard interactions.
     * monitored by methods in the {@code GameController} class.
     * @return the passed {@code Item} if that {@code Item} did not have the property {@code you} OR returns a new
     * {@code Item} whose fields are all equal to the passed {@code Item}.
     */
    public Item withHeading(Heading heading) {
        return this.you()
               ? new Item(name, light, cancel, stop, push, you, win, heading)
               : this;
    }

    /**
     * Given an {@code Item}, determines whether the {@code Tile} at coordinates x and y contains a wall or not.
     * Its final goal is to hash the type of wall to a String. Ultimately, a type of wall is represented by a 4 digits.
     * {@code String} (we have 16 types of walls). The {@code String} outputted by this method is concatenated with 3 other digits in the
     * {@code GameView.renderWall()} method.
     * @param gameStateMap  the current {@code GameState}'s map.
     * @param x  the x coordinate of the {@code Tile} we are checking.
     * @param y  the y coordinate of the {@code Tile} we are checking.
     * @return {@code "1"} if the {@code Tile} contains a wall, {@code "0"} otherwise.
     */
    public String hashWall(ArrayList<ArrayList<Tile>> gameStateMap, int x, int y) {
        // Scan all the items on the Tile
        for (Item item : gameStateMap.get(x).get(y).items()) {
            // If the Tile contains a wall, return "1"
            if (item.name == Kind.ICON_WALL) {
                return "1";
            }
        }
        // No wall was found, return "0"
        return "0";
    }

    /**
     * Given a {@code Heading} and a {@code Sequence of Items}, creates a new {@code Sequence of Items} whose fields have the same values as the parameter.
     * Is implemented using the {@code Item.withHeading()} method.
     * {@code Sequence of Items}, except for the {@code Heading} which is now equal to the parameter {@code Heading}.
     * @param heading {@code Heading} of the Items in the returned {@code Sequence}
     * @param items a {@code Sequence of Items} that will be on the same {@code Tile}
     * @return a new {@code Sequence of Items} with the correct {@code Heading}
     */
    public static Sequence<Item> withHeadings(Heading heading, Sequence<Item> items) {
        return map(
            (Item item) -> item.withHeading(heading),
            items
        );
    }
}
