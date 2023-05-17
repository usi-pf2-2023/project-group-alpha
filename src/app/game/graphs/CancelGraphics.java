package src.app.game.graphs;
import jtamaro.en.Graphic;
import src.app.game.Settings;

import java.io.IOException;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.IO.*;
import static jtamaro.en.Colors.*;

public class CancelGraphics {
    public static final Graphic cancel() {
        return overlay(
            rotate(45, rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT / 10, rgb(215, 91, 75))),
            rotate(135, rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT / 10, rgb(215, 91, 75)))
        );
    }
}
