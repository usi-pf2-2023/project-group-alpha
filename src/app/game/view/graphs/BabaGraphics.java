package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public class BabaGraphics implements TextGraphics {
    public final Graphic babaNorth() {
        return ImageConverter.toGraphic("babaNorth.png");
    }

    public final Graphic babaSouth() {
        return ImageConverter.toGraphic("babaSouth.png");
    }

    public final Graphic babaWest() {
        return ImageConverter.toGraphic("babaWest.png");
    }

    public final Graphic babaEast() {
        return ImageConverter.toGraphic("babaEast.png");
    }

    public final Graphic on() {
        return ImageConverter.toGraphic("babaText.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("babaTextDark.png");
    }
}
