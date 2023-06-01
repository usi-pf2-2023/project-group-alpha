package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.*;

public interface TextGraphics {
    public Graphic on();

    public Graphic off();

    public default Graphic cancel() {
        return overlay(CancelGraphics.cancel(), on());
    }
}