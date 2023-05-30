package src.app.game.view.graphs;
import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.overlay;

public class WallGraphics {

    public static final Graphic bottomLeftCorner() {
        return ImageConverter.toGraphic("bottomLeftCorner.png");
    }

    public static final Graphic bottomRightCorner() {
        return ImageConverter.toGraphic("bottomRightCorner.png");
    }

    public static final Graphic topLeftCorner() {
        return ImageConverter.toGraphic("topLeftCorner.png");
    }

    public static final Graphic topRightCorner() {
        return ImageConverter.toGraphic("topRightCorner.png");
    }

    public static final Graphic horizontal() {
        return ImageConverter.toGraphic("horizontal.png");
    }

    public static final Graphic vertical() {
        return ImageConverter.toGraphic("vertical.png");
    }

    public static final Graphic isolated() {
        return ImageConverter.toGraphic("wallIsolated.png");
    }

    public static final Graphic endCornerRight() {
        return ImageConverter.toGraphic("endCornerRight.png");
    }

    public static final Graphic endCornerLeft() {
        return ImageConverter.toGraphic("endCornerLeft.png");
    }

    public static final Graphic endCornerTop() {
        return ImageConverter.toGraphic("endCornerTop.png");
    }

    public static final Graphic endCornerBottom() {
        return ImageConverter.toGraphic("endCornerBottom.png");
    }

    public static final Graphic linkedRightLeftTop() {
        return ImageConverter.toGraphic("linkedRightLeftTop.png");
    }

    public static final Graphic linkedRightLeftBottom() {
        return ImageConverter.toGraphic("linkedRightLeftBottom.png");
    }

    public static final Graphic linkedRightTopBottom() {
        return ImageConverter.toGraphic("linkedRightTopBottom.png");
    }

    public static final Graphic linkedLeftTopBottom() {
        return ImageConverter.toGraphic("linkedLeftTopBottom.png");
    }

    public static final Graphic linkedAllFour() {
        return ImageConverter.toGraphic("linkedAllFour.png");
    }

    public static final Graphic wallOn() {
        return ImageConverter.toGraphic("wallText.png");
    }

    public static final Graphic wallOff() {
        return ImageConverter.toGraphic("wallTextDark.png");
    }
    public static final Graphic wallCancel() {
        return overlay(CancelGraphics.cancel(), wallOn());
    }
}
