package src.app.game.graphs;
import jtamaro.en.Graphic;
import static jtamaro.en.Graphics.*;

public class FlagGraphics {
    // TODO: Design flag
    public static final Graphic flag() {
        return emptyGraphic();
    }
    // TODO: Design a text box "flag" that is activated (i.e it is light)
    public static final Graphic flagOn() {
        return emptyGraphic();
    }
    // TODO: Design a text box "flag" that is not activated (i.e it is dark)
    public static final Graphic flagOff() {
        return emptyGraphic();
    }
    public static final Graphic flagCancel() {
        return overlay(CancelGraphics.cancel(), flagOn());
    }
}
