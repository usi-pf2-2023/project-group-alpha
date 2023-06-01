package src.app.game.view.graphs;

import jtamaro.en.Graphic;

public class WinGraphics implements TextGraphics {

    public final Graphic on() {
        return ImageConverter.toGraphic("win.png");
    }

    public Graphic off() {
        return ImageConverter.toGraphic("winTextDark.png");
    }
}
