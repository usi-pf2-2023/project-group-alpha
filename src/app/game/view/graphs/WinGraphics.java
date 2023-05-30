package src.app.game.view.graphs;
import jtamaro.en.Graphic;

public class WinGraphics {

    public static final Graphic winOn() {
        return ImageConverter.toGraphic("win.png");
    }

    public static Graphic winOff() {
        return ImageConverter.toGraphic("winTextDark.png");
    }
}
