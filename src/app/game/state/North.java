package src.app.game.state;

import jtamaro.en.io.KeyboardKey;

public class North implements Heading {
    public int newXCoordinate() {
        return -1;
    }
    public int newYCoordinate() {
        return 0;
    }

}
