package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class RockGraphics {
    public static final Graphic rock() throws IOException {
        return ImageConverter.toGraphic("rockIcon.png");
    }

    public static final Graphic rockOn() throws IOException {
        return ImageConverter.toGraphic("rockText.png");
    }

    public static final Graphic rockOff() throws IOException {
        return ImageConverter.toGraphic("rockTextDark.png");
    }

    public static final Graphic rockCancel() throws IOException {
        return overlay(CancelGraphics.cancel(), rockOn());
    }
}
