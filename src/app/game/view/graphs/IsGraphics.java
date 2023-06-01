package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public class IsGraphics implements TextGraphics {
    public final Graphic on() {
        return ImageConverter.toGraphic("is.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("isTextDark.png");
    }
}
