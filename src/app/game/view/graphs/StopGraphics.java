package src.app.game.view.graphs;

import jtamaro.en.Graphic;

public class StopGraphics implements TextGraphics {

    public final Graphic on() {
        return ImageConverter.toGraphic("stop.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("stopTextDark.png");
    }
}
