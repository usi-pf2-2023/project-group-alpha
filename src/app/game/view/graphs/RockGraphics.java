package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public class RockGraphics implements TextGraphics {
    public final Graphic rock() {
        return ImageConverter.toGraphic("rockIcon.png");
    }

    public final Graphic on() {
        return ImageConverter.toGraphic("rockText.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("rockTextDark.png");
    }
}
