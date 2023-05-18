package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;

public class YouGraphics {

    public static final Graphic youOn() throws IOException {
        return ImageConverter.toGraphic("you.png");
    }

    public static final Graphic youOff() throws IOException {
        return ImageConverter.toGraphic("youTextDark.png");
    }
}
