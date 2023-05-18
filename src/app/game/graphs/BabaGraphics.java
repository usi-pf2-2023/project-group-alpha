package src.app.game.graphs;

import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.*;

public class BabaGraphics {
    public static final Graphic babaNorth() throws IOException {
        return ImageConverter.toGraphic("babaEast.png");
    }

    public static final Graphic babaSouth() throws IOException {
        return ImageConverter.toGraphic("babaEast.png");
    }

    public static final Graphic babaWest() throws IOException {
        return ImageConverter.toGraphic("babaWest.png");
    }

    public static final Graphic babaEast() throws IOException {
        return ImageConverter.toGraphic("babaEast.png");
    }

    public static final Graphic babaOn() throws IOException {
        return ImageConverter.toGraphic("babaText.png");
    }

    public static final Graphic babaOff() throws IOException {
        return ImageConverter.toGraphic("babaTextDark.png");
    }
    public static final Graphic babaCancel() throws IOException {
        return overlay(CancelGraphics.cancel(), babaOn());
    }


}
