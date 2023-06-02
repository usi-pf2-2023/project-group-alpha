package src.app.game.view;

import jtamaro.en.Graphic;
import jtamaro.en.Sequence;
import src.app.game.Settings;
import src.app.game.state.*;
import src.app.game.controller.GameController;
import src.app.game.view.graphs.ImageConverter;

import java.util.ArrayList;
import java.util.HashMap;

import static jtamaro.en.Colors.*;
import static jtamaro.en.Graphics.*;
import static jtamaro.en.Sequences.reduce;
import static jtamaro.en.Points.*;

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
            fore = above(fore, rowFore);
        }
        Graphic back = rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, BLACK);
        Graphic ret = overlay(fore, back);
        if (GameController.hasWon(gameState.gameMap())) {
            Graphic winText =
                overlay(
                    ImageConverter.toHints(gameState.level() < Settings.totalLevel ? "win1.png" : "win2.png",
                                           18 * Settings.UNIT_WIDTH, 8 * Settings.UNIT_HEIGHT),
                    rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, rgb(255, 255, 255, 0.3))
                );
            ret = overlay(winText, ret);
        }
        if (GameController.hasLost(gameState.gameMap())) {
            Graphic loseText =
                overlay(
                    ImageConverter.toHints("lost.png", 18 * Settings.UNIT_WIDTH, 8 * Settings.UNIT_HEIGHT),
                    rectangle(m * Settings.UNIT_WIDTH, n * Settings.UNIT_HEIGHT, rgb(255, 255, 255, 0.3))
                );
            ret = overlay(loseText, ret);
        }
        if (gameState.level() == 0) {
            ret = compose(
                pin(BOTTOM_CENTER,
                    overlay(
                        ImageConverter.toHints("gameHint.png", 17 * Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT),
                        rectangle(17 * Settings.UNIT_WIDTH, 3 * Settings.UNIT_HEIGHT, rgb(0x21, 0x28, 0x3e))
                    )
                ),
                pin(BOTTOM_CENTER, ret)
            );
        }
        return ret;
    }

    /**
     * The method takes in an Item and renders it to a Graphic depending on its characteristics.
     */
    public static Graphic itemToGraphic(Item item, GameState gameState, int x, int y) {
        HashMap<String, Graphic> map = item.name().getGraphicMap();
        // Rendering for texts
        Graphic ret = null;
        if (item.name().isObjectText() || item.name().isStateText() || item.name() == Kind.TEXT_IS) {
            if (item.cancel()) {
                ret = map.get("cancel");
            } else if (item.light()) {
                ret = map.get("light");
            } else {
                ret = map.get("dark");
            }
        } else if (item.isIconBaba()) {
            if (item.heading() instanceof South) {
                ret = map.get("south");
            } else if (item.heading() instanceof West) {
                ret = map.get("west");
            } else if (item.heading() instanceof North) {
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

    /**
     * Given an {@code Item}, a {@code GameState} and two coordinates {@code x, y},  renders a wall on a {@code Tile}.
     * The method checks the direct neighbors (left, top, bottom, right) of the {@code Tile}
     * and hashes it to a 4 digit String. The method reads that {@code String} and outputs the corresponding wall
     * {@code Graphic}.
     * @param item  the {@code Item} to render (which is a wall).
     * @param gameState  the current {@code GameState}.
     * @param x  the x coordinate of the {@code Tile} containing the {@code Item}.
     * @param y  the y coordinate of the {@code Tile} containing the {@code Item}.
     * @return a {@code Graphic} representing a wall accordingly to its surroundings.
     */
    public static Graphic renderWall(Item item, GameState gameState, int x, int y) {
        /* Declaring a variable for the item's hashmap;
         most items have different graphical representation depending on various criteria such as their heading,
         whether they are part of a valid rule or not etc...
        */
        HashMap<String, Graphic> hashMap = item.name().getGraphicMap();
        ArrayList<ArrayList<Tile>> gameMap = gameState.gameMap();
        // Introducing changes of coordinates in x and y respectively
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        String keyWall = "";
        // Iterating over all neighbors of the Tile holding the Item:
        for (int i = 0; i < 4; i++) {
            // Concatenating 4 single digit Strings into one 4 digits String representing the type of wall we have
            // to render.
            keyWall += item.hashWall(gameMap, x + dx[i], y + dy[i]);
        }
        // Fetch the corresponding wall Graphic inside its hashMap containing all of the possible wallGraphics
        return hashMap.get(keyWall);
    }

    /**
     * Given the content of a {@code Tile}, i.e a {@code Sequence of Items }, a {@code GameState} and two coordinates
     * {@code x} and {@code y}, renders that {@code Tile} to a {@code Graphic}.
     * @param items the {@code Items} contained on the {@code Tile} that we are rendering.
     * @param gameState is the current GameState.
     * @param x the x coordinate of the {@code Tile} we are rendering.
     * @param y the y coordinate of the {@code Tile} we are rendering.
     * The coordinates are needed to figure out what type of wall to render when a wall is present on the {@code Tile}.
     * @return a Graphic where all {@code Item}s are overlayed. If there are no {@code Item}s on the {@code Tile},
     * the background {@code Tile} that is black and of unit sizes (cf Settings class) is rendered.
     */
    public static Graphic tileToGraphic(Sequence<Item> items, GameState gameState, int x, int y) {
        return overlay(
            // Overlayed Graphic of all Items on the Tile
            reduce((graphic, item) -> overlay(graphic, itemToGraphic(item, gameState, x, y)), emptyGraphic(), items),
            // Black background Tile with unit width and height
            rectangle(Settings.UNIT_WIDTH, Settings.UNIT_HEIGHT, BLACK));
    }


}

