package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;

import static jtamaro.en.Graphics.*;

// from top to down
public record Tile(Sequence<Item> items) {
    public Graphic toGraphic() {
        //TODO
        return emptyGraphic();
    }
}
