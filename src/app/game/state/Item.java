package src.app.game.state;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public record Item(Kind name, // describes what kind of object it is
                   boolean light, // true if the object is one part of an effective rule
                   boolean cancel, // true if the item is a part of an invalid rule
                   boolean stop, // true if the object cannot be traversed
                   boolean push, // true if the object can be pushed
                   boolean you, // true if that object is controlled by the player
                   boolean win // true if touching the item triggers a win
) {
    public Graphic toGraphic() {
        //TODO: implementation
        return emptyGraphic();
    }
}
