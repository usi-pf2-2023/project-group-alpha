package src.app.game.graphs;
import jtamaro.en.Graphic;
import static jtamaro.en.Graphics.*;

public class RockGraphics {
    // TODO: Design the rock graphics
    public static final Graphic rock() {
        return emptyGraphic();
    }
    // TODO: Design a text box "rock" that is activated (i.e it is light)
    public static final Graphic rockOn() {
        return emptyGraphic();
    }
    // TODO: Design a text box "rock" that is not activated (i.e it is dark)
    public static final Graphic rockOff() {
        return emptyGraphic();
    }
    public static final Graphic rockCancel() {
        return overlay(CancelGraphics.cancel(), rockOn());
    }
}
