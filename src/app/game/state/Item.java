package src.app.game.state;

import jtamaro.en.Graphic;

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
    public Graphic toGraphic() {
        HashMap<String, Graphic> map = name.getGraphic_map();
        if(name.isObjectText() || name.isStateText() || name == Kind.TEXT_IS) {
            if (light) {
                return map.get("light");
            } else if (cancel) {
                return map.get("cancel");
            } else {
                return map.get("dark");
            }
        }
        else {
            return map.get("normal");
        }
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
