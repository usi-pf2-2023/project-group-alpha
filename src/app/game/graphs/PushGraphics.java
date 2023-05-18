package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;

public class PushGraphics {

    public static final Graphic pushOn() throws IOException {
        return ImageConverter.toGraphic("push.png");
    }

    public static final Graphic pushOff() throws IOException {
        return ImageConverter.toGraphic("pushTextDark.png");
    }
}
