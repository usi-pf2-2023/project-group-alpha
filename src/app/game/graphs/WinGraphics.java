package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;
public class WinGraphics {

    public static final Graphic winOn() {
        return ImageConverter.toGraphic("win.png");
    }

    public static Graphic winOff() {
        return ImageConverter.toGraphic("winTextDark.png");
    }
}
