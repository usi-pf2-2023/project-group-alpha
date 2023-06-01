package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public class FlagGraphics implements TextGraphics {

    public final Graphic flag() {
        return ImageConverter.toGraphic("flag.png");
    }

    public final Graphic on() {
        return ImageConverter.toGraphic("flagText.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("flagTextDark.png");
    }
}
