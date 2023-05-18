package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class FlagGraphics {

    public static final Graphic flag() {
        return ImageConverter.toGraphic("flag.png");
    }

    public static final Graphic flagOn() {
        return ImageConverter.toGraphic("flagText.png");
    }

    public static final Graphic flagOff() {
        return ImageConverter.toGraphic("flagTextDark.png");
    }
    public static final Graphic flagCancel() {
        return overlay(CancelGraphics.cancel(), flagOn());
    }
}
