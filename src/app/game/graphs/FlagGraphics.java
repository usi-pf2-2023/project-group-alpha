package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class FlagGraphics {

    public static final Graphic flag() throws IOException {
        return ImageConverter.toGraphic("flag.png");
    }

    public static final Graphic flagOn() throws IOException {
        return ImageConverter.toGraphic("flagText.png");
    }

    public static final Graphic flagOff() throws IOException {
        return ImageConverter.toGraphic("flagTextDark.png");
    }
    public static final Graphic flagCancel() throws IOException {
        return overlay(CancelGraphics.cancel(), flagOn());
    }
}
