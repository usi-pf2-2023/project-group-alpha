package src.app.game.state;

import jtamaro.en.Graphic;
import jtamaro.en.data.Empty;

import java.util.ArrayList;
import java.util.HashMap;

import static jtamaro.en.Graphics.*;

public record Item(Kind name, // describes what kind of object it is
                   boolean light, // true if the object is one part of an effective rule
                   boolean cancel, // true if the item is a part of an invalid rule
                   boolean stop, // true if the object cannot be traversed
                   boolean push, // true if the object can be pushed
                   boolean you, // true if that object is controlled by the player
                   boolean win // true if touching the item triggers a win
) {
    public Graphic toGraphic(ArrayList<ArrayList<Tile>> gameStateMap, int x, int y) {
        HashMap<String, Graphic> hashMap = name.getGraphic_map();
        if (name.isObjectText() || name.isStateText() || name == Kind.TEXT_IS) {
            if (light) {
                return hashMap.get("light");
            } else if (cancel) {
                return hashMap.get("cancel");
            } else {
                return hashMap.get("dark");
            }
        } else if (name == Kind.ICON_WALL) {
            return renderWall(hashMap, gameStateMap, x, y);
        } else {
            return hashMap.get("normal");
        }
    }
    public Graphic renderWall(HashMap<String, Graphic> hashMap, ArrayList<ArrayList<Tile>> gameStateMap, int x, int y) {
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        String keyWall = new String("");
        for(int i = 0; i < 4; i++) {
            keyWall += hasWall(gameStateMap, x + dx[i], y + dy[i]);
        }
        return hashMap.get(keyWall);
    }
    public String hasWall(ArrayList<ArrayList<Tile>> gameStateMap, int x, int y) {
        for(Item item : gameStateMap.get(x).get(y).items()) {
            if(item.name == Kind.ICON_WALL) {
                return "1";
            }
        }
        return "0";
    }

    public Item applyRules(HashMap<Kind, ArrayList<Kind>> stateMap) {
        boolean stop = false, push = false, you = false, win = false;
        if (stateMap.containsKey(name)) {
            for (Kind kind : stateMap.get(name)) {
                if (kind == Kind.TEXT_STOP) {
                    stop = true;
                } else if (kind == Kind.TEXT_PUSH) {
                    push = true;
                } else if (kind == Kind.TEXT_YOU) {
                    you = true;
                } else if (kind == Kind.TEXT_WIN) {
                    win = true;
                } else {
                    assert false;
                }
            }
        }
        return new Item(name, light, cancel, stop, push, you, win);
    }

    public Item setName(Kind name) {
        return new Item(name, light, cancel, stop, push, you, win);
    }

    // set text Items to Dark
    public Item setToDark() {
        return new Item(name, false, cancel, true, true, you, win);
    }

    public Item setToCancel() {
        return new Item(name, true, true, true, true, you, win);
    }

    public Item setToReactive() {
        return new Item(name, true, false, true, true, you, win);
    }
}
