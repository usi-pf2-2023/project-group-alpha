package src.app.game.view;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;
import src.app.game.Settings;
import src.app.game.state.GameState;
import src.app.game.state.Kind;
import src.app.game.state.Tile;
import src.app.game.state.Item;

import java.util.ArrayList;
import java.util.HashMap;

import static jtamaro.en.Colors.BLACK;
import static jtamaro.en.Graphics.*;
import static jtamaro.en.Sequences.reduce;

public class GameView {
    public static Graphic render(GameState gameState) {
        ArrayList<ArrayList<Tile>> map = gameState.gameMap();
        int n = map.size(), m = map.get(0).size();
        Graphic fore = emptyGraphic();
        for (int i = 0; i < n; i++) {
            Graphic rowFore = emptyGraphic();
            for (int j = 0; j < m; j++) {
                rowFore = beside(rowFore, tileToGraphic(map.get(i).get(j).items()));
            }
            //    rowFore = beside(rowFore, map.get(i).get(j).toGraphic());
            fore = above(fore, rowFore);
        }
        Graphic back = rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, BLACK);
        return overlay(fore, back);
    }

    /**
     * The method takes in an Item and renders it to a Graphic depending on its characteristics.
     */
    public static Graphic itemToGraphic(Item item) {
        HashMap<String, Graphic> map = item.name().getGraphic_map();
        // Rendering for texts
        if (item.name().isObjectText() || item.name().isStateText() || item.name() == Kind.TEXT_IS) {
            if (item.light()) {
                return map.get("light");
            } else if (item.cancel()) {
                return map.get("cancel");
            } else {
                return map.get("dark");
            }
        }
        // Rendering for Babas:
        if (item.isIconBaba()) {
            return null;
        } else {
            return map.get("normal");
        }
    }
    // convert a Tile to a Graphic

    public static Graphic tileToGraphic(Sequence<Item> items) {
        return
            overlay(
                reduce(
                    (graphic, item) -> overlay(graphic, itemToGraphic(item)),
                    emptyGraphic(),
                    items
                ),
                rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, BLACK)
            );
    }
}

