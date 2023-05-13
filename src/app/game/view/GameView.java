package src.app.game.view;

import jtamaro.en.Graphic;
import src.app.game.Settings;
import src.app.game.state.GameState;
import src.app.game.state.Tile;

import java.util.ArrayList;

import static jtamaro.en.Colors.BLACK;
import static jtamaro.en.Graphics.*;

public class GameView {
    public static Graphic render(GameState gameState) {
        ArrayList<ArrayList<Tile>> map = gameState.gameMap();
        int n = map.size(), m = map.get(0).size();
        Graphic fore = emptyGraphic();
        for (int i = 0; i < n; i++) {
            Graphic rowFore = emptyGraphic();
            for (int j = 0; j < m; j++) {
                rowFore = beside(rowFore, map.get(i).get(j).toGraphic());
            }
            fore = above(fore, rowFore);
        }
        Graphic back = rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, BLACK);
        return overlay(fore, back);
    }
}
