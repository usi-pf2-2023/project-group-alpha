package src.app.game.view.graphs;

import jtamaro.en.Graphic;

public class YouGraphics implements TextGraphics {

    public final Graphic on() {
        return ImageConverter.toGraphic("you.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("youTextDark.png");
    }
}
