package src.app.game.view;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;
import src.app.game.Settings;
import src.app.game.state.*;
import src.app.game.controller.GameController;

import java.util.ArrayList;
import java.util.HashMap;

import static jtamaro.en.Colors.*;
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
                rowFore = beside(rowFore, tileToGraphic(map.get(i).get(j).items(), gameState, i, j));
            }
            //    rowFore = beside(rowFore, map.get(i).get(j).toGraphic());
            fore = above(fore, rowFore);
        }
        Graphic back = rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, BLACK);
        Graphic ret = overlay(fore, back);
        if (GameController.hasWon(gameState.gameMap())) {
            String s1 = new String();
            String s2 = new String();
            if (gameState.level() < Settings.totalLevel) {
                s1 = "Congratulations, you have won!";
                s2 = "Press SPACE to continue!";
            } else {
                s1 = "Congratulations, you You have passed all levels!";
                s2 = "Press SPACE to restart!";
            }
            Graphic winText =
                overlay(
                    above(
                        text(s1, "Helvetica", 50, WHITE),
                        text(s2, "Helvetica", 50, WHITE)
                    ),
                    rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, rgb(255, 255, 255, 0.3))
                );
            ret = overlay(winText, ret);
        }
        return ret;
    }

    /**
     * The method takes in an Item and renders it to a Graphic depending on its characteristics.
     */
    public static Graphic itemToGraphic(Item item, GameState gameState, int x, int y) {
        HashMap<String, Graphic> map = item.name().getGraphic_map();
        // Rendering for texts
        Graphic ret = null;
        if (item.name().isObjectText() || item.name().isStateText() || item.name() == Kind.TEXT_IS) {
            if (item.light()) {
                ret = map.get("light");
            } else if (item.cancel()) {
                ret = map.get("cancel");
            } else {
                ret = map.get("dark");
            }
        } else if (item.isIconBaba()) {
            if (item.heading() == Heading.SOUTH) {
                ret = map.get("south");
            } else if (item.heading() == Heading.WEST) {
                ret = map.get("west");
            } else if (item.heading() == Heading.NORTH) {
                ret = map.get("north");
            } else {
                ret = map.get("east");
            }
        } else if (item.isIconWall()) {
            ret = renderWall(item, gameState, x, y);
        } else {
            ret = map.get("normal");
        }
        if (ret == null) {
            assert false;
        }
        return ret;
    }

    public static Graphic renderWall(Item item, GameState gameState, int x, int y) {
        HashMap<String, Graphic> hashMap = item.name().getGraphic_map();
        ArrayList<ArrayList<Tile>> gameMap = gameState.gameMap();
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        String keyWall = new String("");
        for (int i = 0; i < 4; i++) {
            keyWall += item.hashWall(gameMap, x + dx[i], y + dy[i]);
        }
        return hashMap.get(keyWall);
    }

    // convert a Tile to a Graphic

    public static Graphic tileToGraphic(Sequence<Item> items, GameState gameState, int x, int y) {
        return overlay(
            reduce((graphic, item) -> overlay(graphic, itemToGraphic(item, gameState, x, y)), emptyGraphic(), items),
            rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, BLACK));
    }


}

