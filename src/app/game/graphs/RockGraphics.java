package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class RockGraphics {
    public static final Graphic rock() {
        return ImageConverter.toGraphic("rockIcon.png");
    }

    public static final Graphic rockOn() {
        return ImageConverter.toGraphic("rockText.png");
    }

    public static final Graphic rockOff() {
        return ImageConverter.toGraphic("rockTextDark.png");
    }

    public static final Graphic rockCancel() {
        return overlay(CancelGraphics.cancel(), rockOn());
    }
}
