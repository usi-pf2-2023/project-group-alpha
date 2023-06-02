package src.app.game.state;

import jtamaro.en.io.KeyboardKey;

// Heading is a model class. It's independent of the UI.
public interface Heading {

    public abstract int newXCoordinate();
    public abstract int newYCoordinate();

    public static Heading newHeading(KeyboardKey key) {
        int KEY_CODE = key.getCode();
        return
            KEY_CODE == KeyboardKey.UP ? new North() :
            KEY_CODE == KeyboardKey.DOWN ? new South() :
            KEY_CODE == KeyboardKey.RIGHT ? new East() :
            new West();
    }
}
/*
    NORTH,
    SOUTH,
    EAST,
    WEST;
    // convert a keycode to direction.
    public static Heading fromKeyCode(int keyCode) {
        return
            keyCode == KeyboardKey.UP ? NORTH :
            keyCode == KeyboardKey.DOWN ? SOUTH :
            keyCode == KeyboardKey.RIGHT ? EAST :
            WEST;
    }

    // Heading.newX()
*/

