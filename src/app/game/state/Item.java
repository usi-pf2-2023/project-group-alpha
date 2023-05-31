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
     * isIconBaba() determines whether an object is an iconBaba.
     *
     * @return true if that is the case, false otherwise.
     */
    public boolean isIconBaba() {
        return this.name().equals(Kind.ICON_BABA);
    }

    public boolean isIconWall() {
        return this.name().equals(Kind.ICON_WALL);
    }

    public boolean isText() {
        return this.name().isObjectText() || this.name().isStateText() || this.name() == Kind.TEXT_IS;
    }

    /**
     * Item.withHeading() creates a new Item object whose fields have the same values as the input object except for the
     * heading field which is now equal to the new heading if the object is "you"
     *
     * @param heading is obtained through keyboard interactions
     * @return an Item
     */
    public Item withHeading(Heading heading) {
        return this.you()
               ? new Item(name, light, cancel, stop, push, you, win, heading)
               : this;
    }

    /**
     * Item.hashWall() determines whether the Tile at coordinates x and y contains a wall or not.
     * Its final goal is to hash the type of wall to a String. Ultimately, a type of wall is represented by a 4 digits
     * String (we have 16 types of walls). The string outputted by this method is concatenated with 3 other digits in the
     * GameView.renderWall() method.
     * @param gameStateMap is the current GameState's map
     * @param x is the x coordinate of the Tile we are checking
     * @param y is the y coordinate of the Tile we are checking
     * @return "1" if the Tile contains a wall, "0" otherwise
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
     * Item.withHeadings() changes the Items in a Sequence correspondingly to a new Heading
     * @param heading is the Heading used to udpate/create the new Sequence of Items
     * @param items is a list of Item that will be on the same Tile
     * @return a new Sequence of Items with the correct Heading
     */
    public static Sequence<Item> withHeadings(Heading heading, Sequence<Item> items) {
        return map(
            (Item item) -> item.withHeading(heading),
            items
        );
    }
}
