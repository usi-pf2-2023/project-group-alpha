package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;
import src.app.game.Settings;

import static jtamaro.en.Graphics.*;
import static jtamaro.en.Colors.*;

import java.util.ArrayList;


public class GameState {

    public ArrayList<ArrayList<Tile>> map;

    public Sequence<Rule> rules;

    public GameState previousState;

    public Graphic render() {
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
