package src.app.game.state;

import jtamaro.en.Sequence;

import static jtamaro.en.Sequences.*;

import java.util.ArrayList;


// a GameState is a singly linked list,
// contains the current map, the previousState, and the rules on the field.
public class GameState {

    private ArrayList<ArrayList<Tile>> map;

    private GameState previousState;

    public ArrayList<ArrayList<Tile>> getMap() {
        return map;
    }

    public void setMap(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
    }

    public GameState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(GameState previousState) {
        this.previousState = previousState;
    }

    // TODO: the rules mapping

    //generate rules from the current map
    public Sequence<Rule> generateRules() {
        assert map.size() >= 2;
        int n = map.size(), m = map.get(0).size();
        Sequence<Rule> rules = empty();
        for (int i = 1; i < n - 1; ++i) {
            assert map.get(i).size() == m;
            for (int j = 1; j < m - 1; ++j) {
                Tile tile = map.get(i).get(j);
                if (!tile.containsIs()) continue;
                //left to right;
                if (j != 1 && j != m - 2) {
                    Tile leftTile = map.get(i).get(j - 1);
                    Tile rightTile = map.get(i).get(j + 1);
                    if (!leftTile.containsObjectText()) continue;
                    if (!rightTile.containsObjectText() && !rightTile.containsStateText()) continue;
                    Rule rule = new Rule(first(leftTile.items()).name(), first(rightTile.items()).name());
                    rules = cons(rule, rules);
                }
                //up to down
                if (i != 1 && i != n - 2) {
                    Tile upTile = map.get(i - 1).get(j);
                    Tile downTile = map.get(i + 1).get(j);
                    if (!upTile.containsObjectText()) continue;
                    if (!downTile.containsObjectText() && !downTile.containsStateText()) continue;
                    Rule rule = new Rule(first(upTile.items()).name(), first(downTile.items()).name());
                    rules = cons(rule, rules);
                }
            }
        }
        return rules;
    }
}
