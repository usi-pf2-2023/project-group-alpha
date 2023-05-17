package src.app.game.graphs;
import jtamaro.en.Graphic;
import static jtamaro.en.Graphics.*;

public class IsGraphics {
    // TODO: Design the "is" text box when it is activated (i.e light)
    public static final Graphic isOn() {
        return emptyGraphic();
    }
    //TODO: Design the "is" text box when it is not activated (i.e dark)
    public static final Graphic isOff() {
        return emptyGraphic();
    }
    public static final Graphic isCancel() {
        return overlay(CancelGraphics.cancel(), isOn());
    }
}
