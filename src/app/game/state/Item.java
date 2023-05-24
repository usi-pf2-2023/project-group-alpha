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

    public String hashWall(ArrayList<ArrayList<Tile>> gameStateMap, int x, int y) {
        for (Item item : gameStateMap.get(x).get(y).items()) {
            if (item.name == Kind.ICON_WALL) {
                return "1";
            }
        }
        return "0";
    }

    /**
     * Item.withHeadings() changes the items in a sequence correspondingly to a new heading
     *
     * @param heading
     * @param items   is a list of Item that will be on the same Tile
     * @return an updated sequence of items
     */
    public static Sequence<Item> withHeadings(Heading heading, Sequence<Item> items) {
        return map(
            (Item item) -> item.withHeading(heading),
            items
        );
    }
}
