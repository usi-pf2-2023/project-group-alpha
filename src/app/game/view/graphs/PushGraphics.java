package src.app.game.view.graphs;

import jtamaro.en.Graphic;

public class PushGraphics implements TextGraphics {

    public final Graphic on() {
        return ImageConverter.toGraphic("push.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("pushTextDark.png");
    }
}
