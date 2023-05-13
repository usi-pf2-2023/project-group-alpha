package src.app.game.state;

import jtamaro.en.Sequence;

import static jtamaro.en.Sequences.*;

import java.util.ArrayList;


// a GameState is a singly linked list,
// contains the current map, the previousState, and the rules on the field.
public record GameState(ArrayList<ArrayList<Tile>> map, GameState previousState) {
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

    // we divide Move operation into multi singleTile Moves
    public GameState singleTileMove(int i, int j, Heading heading) {
        assert !map.isEmpty();
        int n = map.size(), m = map.get(0).size();
        assert i > 0 && i < n - 1;
        assert j > 0 && j < m - 1;
        if (i == 1 || map.get(i - 1).get(j).containsStop()) {
            return this;
        }
        int dx = 0, dy = 0;
        if (heading == Heading.NORTH) {
            dx = -1;
        } else if (heading == Heading.SOUTH) {
            dx = 1;
        } else if (heading == Heading.EAST) {
            dy = 1;
        } else if (heading == Heading.SOUTH) {
            dy = -1;
        }
        // find the first tile does not contain any "Push" item or "You"
        int noPushRow = i, noPushCol = j;
        while (!map.get(noPushRow).get(noPushCol).isBoundary()) {
            if (map.get(noPushRow).get(noPushCol).containsPush() || map.get(noPushRow).get(noPushCol).containsYou()) {
                noPushRow += dx;
                noPushCol += dy;
            } else {
                break;
            }
        }
        // If we reach the boundary then return the original state
        if (map.get(noPushRow).get(noPushCol).isBoundary()) {
            return this;
        }
        // otherwise we do the move
        //swap to make noPushRow <= i and noPushCol <= j
        if (i > noPushRow) {
            int t = i;
            i = noPushRow;
            noPushRow = t;
        }
        if (j > noPushCol) {
            int t = j;
            j = noPushCol;
            noPushCol = t;
        }
        // clone the old map to the newMap
        ArrayList<ArrayList<Tile>> newMap = (ArrayList<ArrayList<Tile>>) map.clone();
        // we clear the tiles that might be effected
        for (int r = 1; r < n - 1; ++r) {
            if (r < noPushRow || r > i) continue;
            for (int c = 1; c < n - 1; ++c) {
                if (c < noPushCol || c > j) continue;
                Tile tile = newMap.get(i).get(j);
                tile = new Tile(empty());
            }
        }
        // now add the element according to whether they are pushed
        for (int r = 1; r < n - 1; ++r) {
            if (r < noPushRow || r > i) continue;
            for (int c = 1; c < n - 1; ++c) {
                if (c < noPushCol || c > j) continue;
                Tile thisTile = newMap.get(r).get(c);
                Tile pushTile = newMap.get(r + dx).get(c + dy);
                Sequence<Item> items = map.get(r).get(c).items();
                for (Item item : items) {
                    if (item.push() || item.you()) {
                        assert r != noPushRow || c != noPushCol;
                        newMap.get(r + dx).set(c + dy, pushTile.add(item));
                        pushTile = newMap.get(r + dx).get(c + dy);
                    } else {
                        newMap.get(r).set(c, thisTile.add(item));
                        thisTile = newMap.get(r).get(c);
                    }
                }
            }
        }
        return new GameState(newMap, null);
    }

    // The whole move operation
    public GameState move(Heading heading) {
        assert !map.isEmpty();
        int n = map.size(), m = map.get(0).size();
        int st_i = 1, dx = 1;
        int st_j = 1, dy = 1;
        if (heading == Heading.SOUTH) {
            st_i = n - 2;
            dx = -1;
        } else if (heading == Heading.EAST) {
            st_j = m - 2;
            dy = -1;
        }
        GameState newState = new GameState(this.map, null);
        for (int i = st_i; i != 0 && i != n - 1; i += dx) {
            for (int j = st_j; j != 0 && j != m - 1; j += dy) {
                newState = newState.singleTileMove(i, j, heading);
            }
        }
        return new GameState(newState.map, this);
    }
}
