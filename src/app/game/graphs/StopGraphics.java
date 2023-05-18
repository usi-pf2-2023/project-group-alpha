package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;

public class StopGraphics {

    public static Graphic stopOn() throws IOException {
        return ImageConverter.toGraphic("stop.png");
    }

    public static Graphic stopOff() throws IOException {
        return ImageConverter.toGraphic("stopTextDark.png");
    }
}
