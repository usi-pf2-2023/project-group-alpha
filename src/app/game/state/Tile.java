package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;
import src.app.game.Settings;

import java.util.ArrayList;

import static jtamaro.en.Colors.*;
import static jtamaro.en.Graphics.*;
import static jtamaro.en.Sequences.*;

// a `Tile` is a Sequence of items arranged from top to bottom
public record Tile(Sequence<Item> items) {
    // convert a Tile to a Graphic
    public Graphic toGraphic(ArrayList<ArrayList<Tile>> map, int x, int y) {
        return
            overlay(
                reduce(
                    (graphic, item) -> overlay(graphic, item.toGraphic(map, x, y)),
                    emptyGraphic(),
                    items
                ),
                rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, BLACK)
            );
    }

    // support to assert, only used here?
    public static <T> int size(Sequence<T> seq) {
        return isEmpty(seq) ? 0 : 1 + size(rest(seq));
    }

    // check whether it's boundary
    public boolean isBoundary() {
        for (Item item : items) {
            if (item.name() == Kind.BOUNDARY) {
                assert size(items) == 1;
                return true;
            }
        }
        return false;
    }

    //check whether it contains stop
    public boolean containsStop() {
        for (Item item : items) {
            if (item.stop()) {
                return true;
            }
        }
        return false;
    }

    // return whether this tile contains TEXT_IS
    public boolean containsIs() {
        for (Item item : items) {
            if (item.name() == Kind.TEXT_IS) {
                return true;
            }
        }
        return false;
    }

    // return whether this tile contains any object text
    public boolean containsObjectText() {
        for (Item item : items) {
            if (item.name().isObjectText()) {
                return true;
            }
        }
        return false;
    }

    // return whether this tile contains any state text
    public boolean containsStateText() {
        for (Item item : items) {
            if (item.name().isStateText()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsYou() {
        for (Item item : items) {
            if (item.you()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPush() {
        for (Item item : items) {
            if (item.push()) {
                return true;
            }
        }
        return false;
    }
    public boolean containsWin() {
        for (Item item : items) {
            if (item.win()) {
                return true;
            }
        }
        return false;
    }
    // return a new Tile after add the item to the top of old Tile
    public Tile add(Item item) {
        return new Tile(cons(item, items));
    }

    public Tile setToDark() {
        Sequence<Item> newItems = empty();
        for (Item item : items) {
            newItems = concat(newItems, of(item.setToDark()));
        }
        return new Tile(newItems);
    }

    public Tile setToCancel() {
        Sequence<Item> newItems = empty();
        for (Item item : items) {
            newItems = concat(newItems, of(item.setToCancel()));
        }
        return new Tile(newItems);
    }

    public Tile setToReactive() {
        Sequence<Item> newItems = empty();
        for (Item item : items) {
            newItems = concat(newItems, of(item.setToReactive()));
        }
        return new Tile(newItems);
    }

    // return a Tile from a Character
    // 0 : no item, lowercase letters : icon, capital letters: text, other characters: boundary
    public static Tile fromChar(char c) {
        if (c == '0') return new Tile(empty());
        Kind kind = Kind.BOUNDARY;
        boolean light = false;
        boolean cancel = false;
        boolean stop = false;
        boolean push = false;
        boolean you = false;
        boolean win = false;
        if (c == 'w') {
            kind = Kind.ICON_WALL;
        } else if (c == 'b') {
            kind = Kind.ICON_BABA;
        } else if (c == 'f') {
            kind = Kind.ICON_FLAG;
        } else if (c == 'r') {
            kind = Kind.ICON_ROCK;
        } else if (c == 'I') {
            kind = Kind.TEXT_IS;
        } else if (c == 'W') {
            kind = Kind.TEXT_WALL;
        } else if (c == 'B') {
            kind = Kind.TEXT_BABA;
        } else if (c == 'F') {
            kind = Kind.TEXT_FLAG;
        } else if (c == 'R') {
            kind = Kind.TEXT_ROCK;
        } else if (c == 'W') {
            kind = Kind.TEXT_WALL;
        } else if (c == 'P') {
            kind = Kind.TEXT_PUSH;
        } else if (c == 'Y') {
            kind = Kind.TEXT_YOU;
        } else if (c == 'S') {
            kind = Kind.TEXT_STOP;
        }
        if (kind == Kind.BOUNDARY) stop = true;
        if (kind.isStateText() || kind.isObjectText() || kind == Kind.TEXT_IS) {
            stop = true;
            push = true;
        }
        Item item = new Item(kind, light, cancel, stop, push, you, win);
        return new Tile(cons(item, empty()));
    }
}
