package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;

import static jtamaro.en.Sequences.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The map of the game is divided into {@code Tile}s. Each {@code Tile} contains an {@code ArrayList} of {@code Item}s.
 * Each {@code Item} has a certain amount of properties that dictate how it is rendered, how it moves upon key interactions
 * and how it can be interacted with by other {@code Item}s.
 * @param name indicates what type of {@code Item} it is. There are a limited amount of types of {@code Item}s in the game.
 * This is why this "belonging" to a type of object you can find on the map is described by an Enum class {@code Kind}.
 * @param light is {@code true} if the {@code Item} is part of a valid {@code Rule}, {@code false} otherwise.
 * @param cancel is {@code true} if it is part of a contradiction, {@code false} otherwise.
 * @param stop is {@code true} if the {@code Item} cannot be walked on, {@code false} otherwise.
 * @param push is {@code true} if the {@code Item} can be pushed, {@code false} otherwise.
 * @param you is {@code true} if that {@code Item} is controlled by the player, {@code false} otherwise.
 * @param win is {@code true} if that {@code Item} triggers a win upon touching it, {@code false} otherwise. <br>
 *            Note that by convention, if an {@code Item} is {@code you} and {@code win}, then a win is triggered. Trivially,
 *            the player is on the same {@code Tile} as an {@code Item} triggering a win.
 * @param heading describes in what direction an {@code Item} is facing. By default, all {@code Item}s face south.
 */
public record Item(Kind name,
                   boolean light,
                   boolean cancel,
                   boolean stop,
                   boolean push,
                   boolean you,
                   boolean win,
                   Heading heading
) {

    /**
     * Given an {@code HashMap<Kind, ArrayList<Kind>>} and {@code this}, creates a new {@code Item} with the correct
     * properties (i.e correct field values for {@code stop, push, you and win}). Whenever the {@code Kind} of an {@code Item}
     * changes, we must create a new {@code Item} to replace {@code this} on a given {@code Tile}.
     * @param stateMap contains a list of properties using the name of the {@code Item} (i.e its {@code Kind}) as a key.
     * @return an {@code Item} with the proper field values according to its Kind.
     */
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
    /**
     * Given {@code this} and a {@code Kind} creates a new {@code Item} with the same fields as {@code this} except for
     * its name who is now equal to the {@code name} parameter.
     * This method is called whenever a new rule dictates that a type of {@code Item} is now another type of {@code Item}.
     * For example; If the rule "Baba is rock" is activated, then the {@code Item}s of {@code Kind Rock} must be transformed
     * to Babas.
     * @return an {@code Item} with the parameter {@code name} as its {@code name}.
     */
    public Item setName(Kind name) {
        return new Item(name, light, cancel, stop, push, you, win, heading);
    }

    /**
     * Given {@code this}, creates a new {@code Item} with the same fields as {@code this} except for its
     * {@code light, true} and {@code stop} fields who now correspond
     * (visually) to a dark {@code Item}. I.e the method sets:
     *  {@code light} to {@code true}, to {@code false} ,{@code stop} to {@code true},
     *  {@code push} to {@code true}
     *  An {@code Item} being dark means that it will be rendered dark by the {@code GameView.render()} method.
     * @return {@code true} if it is the case, {@code false} otherwise.
     */
    public Item setToDark() {
        return new Item(name, false, cancel, true, true, you, win, heading);
    }

    /**
     * Given {@code this}, creates a new {@code Item} with the same fields as {@code this} except for
     * its {@code light, true, stop} and {@code push} fields who now correspond (visually and behaviourally) to a
     * cancelled {@code Item}. I.e this {@code Item} is not part of any {@code Rule}, because it was determined to be part
     * of a contradiction. <br>
     * In other words, the method sets: {@code light} to {@code true}, {@code cancel} to
     * {@code true}, {@code stop} to {@code true} amd {@code push} to {@code true}.
     * <br> <br>
     * This method is called whenever a contradiction is encountered and one of the contradicting {@code Rule}s needs to be
     * eliminated.
     * @return an {@code Item} with the parameter {@code name} as its {@code name}.
     */
    public Item setToCancel() {
        return new Item(name, true, true, true, true, you, win, heading);
    }
    /**
     * Given {@code this}, creates a new {@code Item} with the same fields as {@code this} except for its
     * {@code light, true, cancel} and {@code stop} fields who now correspond
     * (visually and behaviourally) to a reactive {@code Item}. I.e the method sets:
     *  {@code light} to {@code true}, {@code cancel} to {@code false} ,{@code stop} to {@code true},
     *  {@code push} to {@code true}
     * @return {@code true} if it is the case, {@code false} otherwise.
     */
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
     * object moved (i.e {@code this}), thus its appearance, must change. <br>
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
