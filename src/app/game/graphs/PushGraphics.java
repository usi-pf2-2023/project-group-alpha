package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;

public class PushGraphics {

    public static final Graphic pushOn() {
        return ImageConverter.toGraphic("push.png");
    }

    public static final Graphic pushOff() {
        return ImageConverter.toGraphic("pushTextDark.png");
    }
}
