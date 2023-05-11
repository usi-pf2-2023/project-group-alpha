package src.app.game.state;

import jtamaro.en.io.KeyboardKey;

// Heading is a model class. It's independent of the UI.
public enum Heading {
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
}
