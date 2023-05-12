package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.Sequences.*;

// a `Tile` is a Sequence of items arranged from top to bottom
public record Tile(Sequence<Item> items) {
    // convert a Tile to a Graphic
    public Graphic toGraphic() {
        return reduce(
            (graphic, item) -> overlay(graphic, item.toGraphic()),
            emptyGraphic(),
            items
        );
    }

    // check whether it's possible to step on this Tile
    public boolean canStepOn() {
        for (Item item : items) {
            if (item.stop() || item.name() == Kind.BOUNDARY) {
                return false;
            }
        }
        return true;
    }

    // support to assert, only used here?
    public static <T> int size(Sequence<T> seq) {
        return isEmpty(seq) ? 0 : 1 + size(rest(seq));
    }

    // return whether this tile contains TEXT_IS
    public boolean containsIs() {
        for (Item item : items) {
            if (item.name() == Kind.TEXT_IS) {
                assert size(items) == 1;
                return true;
            }
        }
        return false;
    }

    // return whether this tile contains any object text
    public boolean containsObjectText() {
        for (Item item : items) {
            if (item.name().isObjectText()) {
                assert size(items) == 1;
                return true;
            }
        }
        return false;
    }

    // return whether this tile contains any state text
    public boolean containsStateText() {
        for (Item item : items) {
            if (item.name().isStateText()) {
                assert size(items) == 1;
                return true;
            }
        }
        return false;
    }


}
