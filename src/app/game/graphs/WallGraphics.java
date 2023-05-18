package src.app.game.graphs;
import jtamaro.en.Graphic;

import java.io.IOException;

import static jtamaro.en.Graphics.emptyGraphic;
import static jtamaro.en.Graphics.overlay;

public class WallGraphics {

    //1 TODO: Design icon for the bottom left corner of a wall (i.e it is linked from above and from the right)
    public static final Graphic bottomLeftCorner() {
        return emptyGraphic();
    }
    //2 TODO: Design icon for the bottom right corner of a wall (i.e it is linked from above and from the left)
    public static final Graphic bottomRightCorner() {
        return emptyGraphic();
    }
    //3 TODO: Design icon for the top left corner of a wall (i.e it is linked from below and from the right)
    public static final Graphic topLeftCorner() {
        return emptyGraphic();
    }
    //4 TODO: Design icon for the top right corner of a wall (i.e it is linked from below and from the left)
    public static final Graphic topRightCorner() {
        return emptyGraphic();
    }
    //5 TODO: Design icon for a linked horizontal wall (i.e it is linked from the right and from the left)
    public static final Graphic horizontal() {
        return emptyGraphic();
    }
    //6 TODO: Desin icon for a linked vertical wall
    public static final Graphic vertical() {
        return emptyGraphic();
    }

    public static final Graphic isolated() {
        return ImageConverter.toGraphic("wallIsolated.png");
    }

    public static final Graphic endCornerRight() {
        return ImageConverter.toGraphic("endCornerRight.png");
    }

    public static final Graphic endCornerLeft() {
        return ImageConverter.toGraphic("endCornerCornerLeft.png");
    }

    public static final Graphic endCornerTop() {
        return ImageConverter.toGraphic("endCornerTop.png");
    }

    public static final Graphic endCornerBottom() {
        return ImageConverter.toGraphic("endCornerBottom.png");
    }

    // 12 TODO: Design icon for a wall that is linked from the right, from the left and from above
    public static final Graphic linkedRightLeftTop() {
        return emptyGraphic();
    }
    // 13 TODO: Design icon for a wall that is linked from the right, from the left and from below
    public static final Graphic linkedRightLeftBottom() {
        return emptyGraphic();
    }
    // 14 TODO: Design icon for a wall that is linked from the right, from the top and from below
    public static final Graphic linkedRightTopBottom() {
        return emptyGraphic();
    }
    // 15 TODO: Design an icon for a wall that is linked from the left, from the top and from below
    public static final Graphic linkedLeftTopBottom() {
        return emptyGraphic();
    }
    // 16 TODO: Design an icon for a wall that is linked from all four directions
    public static final Graphic linkedAllFour() {
        return emptyGraphic();
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
