package src.app.game.view.graphs;

import jtamaro.en.Graphic;

import static jtamaro.en.Graphics.overlay;

public class WallGraphics implements TextGraphics {

    public final Graphic bottomLeftCorner() {
        return ImageConverter.toGraphic("bottomLeftCorner.png");
    }

    public final Graphic bottomRightCorner() {
        return ImageConverter.toGraphic("bottomRightCorner.png");
    }

    public final Graphic topLeftCorner() {
        return ImageConverter.toGraphic("topLeftCorner.png");
    }

    public final Graphic topRightCorner() {
        return ImageConverter.toGraphic("topRightCorner.png");
    }

    public final Graphic horizontal() {
        return ImageConverter.toGraphic("horizontal.png");
    }

    public final Graphic vertical() {
        return ImageConverter.toGraphic("vertical.png");
    }

    public final Graphic isolated() {
        return ImageConverter.toGraphic("wallIsolated.png");
    }

    public final Graphic endCornerRight() {
        return ImageConverter.toGraphic("endCornerRight.png");
    }

    public final Graphic endCornerLeft() {
        return ImageConverter.toGraphic("endCornerLeft.png");
    }

    public final Graphic endCornerTop() {
        return ImageConverter.toGraphic("endCornerTop.png");
    }

    public final Graphic endCornerBottom() {
        return ImageConverter.toGraphic("endCornerBottom.png");
    }

    public final Graphic linkedRightLeftTop() {
        return ImageConverter.toGraphic("linkedRightLeftTop.png");
    }

    public final Graphic linkedRightLeftBottom() {
        return ImageConverter.toGraphic("linkedRightLeftBottom.png");
    }

    public final Graphic linkedRightTopBottom() {
        return ImageConverter.toGraphic("linkedRightTopBottom.png");
    }

    public final Graphic linkedLeftTopBottom() {
        return ImageConverter.toGraphic("linkedLeftTopBottom.png");
    }

    public final Graphic linkedAllFour() {
        return ImageConverter.toGraphic("linkedAllFour.png");
    }

    public final Graphic on() {
        return ImageConverter.toGraphic("wallText.png");
    }

    public final Graphic off() {
        return ImageConverter.toGraphic("wallTextDark.png");
    }
}
