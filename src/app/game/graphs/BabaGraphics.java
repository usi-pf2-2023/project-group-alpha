package src.app.game.graphs;

import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class BabaGraphics {
    public static final Graphic babaNorth() {
        return ImageConverter.toGraphic("babaNorth.png");
    }

    public static final Graphic babaSouth() {
        return ImageConverter.toGraphic("babaSouth.png");
    }

    public static final Graphic babaWest() {
        return ImageConverter.toGraphic("babaWest.png");
    }

    public static final Graphic babaEast() {
        return ImageConverter.toGraphic("babaEast.png");
    }

    public static final Graphic babaOn() {
        return ImageConverter.toGraphic("babaText.png");
    }

    public static final Graphic babaOff() {
        return ImageConverter.toGraphic("babaTextDark.png");
    }

    public static final Graphic babaCancel() {
        return overlay(CancelGraphics.cancel(), babaOn());
    }
}
