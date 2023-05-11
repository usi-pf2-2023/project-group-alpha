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
            (g, item) -> overlay(g, item.toGraphic()),
            emptyGraphic(),
            items
        );
    }
    // check whether it's possible to step on this Tile
    public boolean canStepOn() {
        // TODO: implementation
        return true;
    }

}
