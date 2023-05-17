package src.app.game.graphs;
import jtamaro.en.Graphic;
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
    //7 TODO: Design icon for an isolated wall
    public static final Graphic isolated() {
        return emptyGraphic();
    }
    //8 TODO: Design icon for an endCornerRight wall (i.e it is linked from the left, and there is no other wall around it)
    public static final Graphic endCornerRight() {
        return emptyGraphic();
    }
    //9 TODO: Design icon for an endCornerLeft wall (i.e it is linked from the right and there is no other wall around it)
    public static final Graphic endCornerLeft() {
        return emptyGraphic();
    }
    //10 TODO: Design icon for an endCornerTop wall (i.e it is linked from below and there is no other wall around it)
    public static final Graphic endCornerTop() {
        return emptyGraphic();
    }
    // 11 TODO: Design icon for an endCornerBottom wall (i.e it is linked from above and there is no other wall around it)
    public static final Graphic endCornerBottom() {
        return emptyGraphic();
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
    // 17 TODO: Design a text box "wall" that is activated (i.e it is light)
    public static final Graphic wallOn() {
        return emptyGraphic();
    }
    // 18 TODO: Design a text box "wall" that is not activated (i.e it is dark)
    public static final Graphic wallOff() {
        return emptyGraphic();
    }
    public static final Graphic wallCancel() {
        return overlay(CancelGraphics.cancel(), wallOn());
    }
}
