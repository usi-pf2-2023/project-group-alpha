package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class IsGraphics {
    public static final Graphic isOn() {
        return ImageConverter.toGraphic("is.png");
    }

    public static final Graphic isOff() {
        return ImageConverter.toGraphic("isTextDark.png");
    }
    public static final Graphic isCancel() {
        return overlay(CancelGraphics.cancel(), isOn());
    }
}
