package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;

public class StopGraphics {

    public static Graphic stopOn() {
        return ImageConverter.toGraphic("stop.png");
    }

    public static Graphic stopOff() {
        return ImageConverter.toGraphic("stopTextDark.png");
    }
}
