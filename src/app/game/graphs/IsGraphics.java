package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class IsGraphics {
    public static final Graphic isOn() throws IOException {
        return ImageConverter.toGraphic("is.png");
    }

    public static final Graphic isOff() throws IOException {
        return ImageConverter.toGraphic("isTextDark.png");
    }
    public static final Graphic isCancel() throws IOException {
        return overlay(CancelGraphics.cancel(), isOn());
    }
}
