package src.app.game.state;

import jtamaro.en.Sequence;
import src.app.game.Settings;

import static jtamaro.en.Sequences.*;
import static src.app.game.controller.GameController.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The {@code GameState} class holds every information about the current state of the application. <br>
 * @param gameMap is a 2D {@code ArrayList}. Each {@code Tile} of the map contains (or not) some {@code Item}s. <br>
 *               As we often need to fetch the k-th element when checking {@code Tile}s during the move operations and
 *               the generating of {@code Rule}s, using an {@code ArrayList} is much more efficient
 *               than using a {@code Sequence}. <br>
 *               However, unlike a regular {@code Array}, its size can vary. We need this feature to be able to add
 *               and remove {@code Item}s as the game advances.
 * @param previousState holds the previous {@code GameState}. This allows for undo operations. Because of this storage
 *                      of previous states, the {@code GameState} is a singly linked list.
 * @param level is the current level the game is in. The menu is considered as a level.
 */
public record GameState(ArrayList<ArrayList<Tile>> gameMap, GameState previousState, int level) {
    //generate rules from the current gameMap
    public Sequence<Rule> generateRules() {
        assert gameMap.size() >= 2;
        int n = gameMap.size(), m = gameMap.get(0).size();
        Sequence<Rule> rules = empty();
        for (int i = 1; i < n - 1; ++i) {
            assert gameMap.get(i).size() == m;
            for (int j = 1; j < m - 1; ++j) {
                Tile tile = gameMap.get(i).get(j);
                if (!tile.containsIs()) continue;
                //left to right;
                if (j != 1 && j != m - 2) {
                    Tile leftTile = gameMap.get(i).get(j - 1);
                    Tile rightTile = gameMap.get(i).get(j + 1);
                    if (leftTile.containsObjectText() &&
                        (rightTile.containsObjectText() || rightTile.containsStateText())) {
                        Rule rule = Rule.getRule(leftTile, rightTile);
                        rules = cons(rule, rules);
                    }
                }
                //up to down
                if (i != 1 && i != n - 2) {
                    Tile upTile = gameMap.get(i - 1).get(j);
                    Tile downTile = gameMap.get(i + 1).get(j);
                    if (upTile.containsObjectText() &&
                        (downTile.containsObjectText() || downTile.containsStateText())) {
                        Rule rule = Rule.getRule(upTile, downTile);
                        rules = cons(rule, rules);
                    }
                }
            }
        }
        return rules;
    }

    // we divide Move operation into multi singleTile Moves
    public GameState singleTileMove(int i, int j, Heading heading) {
        assert !gameMap.isEmpty();
        int n = gameMap.size(), m = gameMap.get(0).size();
        if (!gameMap.get(i).get(j).containsYou()) {
            return this;
        }
        assert i > 0 && i < n - 1;
        assert j > 0 && j < m - 1;
        int dx = heading.newXCoordinate(), dy = heading.newYCoordinate();

        if (gameMap.get(i + dx).get(j + dy).isBoundary()) {
            return this;
        }
        // find the first tile does not contain any "Push" item or "You"
        int noPushRow = i, noPushCol = j;
        while (!gameMap.get(noPushRow).get(noPushCol).isBoundary()) {
            if (gameMap.get(noPushRow).get(noPushCol).containsPush() ||
                gameMap.get(noPushRow).get(noPushCol).containsYou()) {
                noPushRow += dx;
                noPushCol += dy;
            } else {
                break;
            }
        }
        // If we reach the boundary then return the original state
        if (gameMap.get(noPushRow).get(noPushCol).isBoundary() ||
            gameMap.get(noPushRow).get(noPushCol).containsStop()) {
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
        // clone the old gameMap to the newGameMap
        ArrayList<ArrayList<Tile>> newGameMap = new ArrayList<>();
        for (int r = 0; r < n; ++r) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int c = 0; c < m; ++c) {
                row.add(gameMap.get(r).get(c));
            }
            newGameMap.add(row);
        }
        // we clear the tiles that might be effected
        for (int r = 1; r < n - 1; ++r) {
            if (r < i || r > noPushRow) continue;
            for (int c = 1; c < m - 1; ++c) {
                if (c < j || c > noPushCol) continue;
                newGameMap.get(r).set(c, new Tile(empty()));
            }
        }
        // now add the element that are not pushed
        for (int r = 1; r < n - 1; ++r) {
            if (r < i || r > noPushRow) continue;
            for (int c = 1; c < m - 1; ++c) {
                if (c < j || c > noPushCol) continue;
                Tile thisTile = newGameMap.get(r).get(c);
                Sequence<Item> items = gameMap.get(r).get(c).items();
                for (Item item : items) {
                    if (item.push() || item.you()) {
                        continue;
                    } else {
                        newGameMap.get(r).set(c, thisTile.add(item));
                        thisTile = newGameMap.get(r).get(c);
                    }
                }
            }
        }
        // now add the element that are pushed
        for (int r = 1; r < n - 1; ++r) {
            if (r < i || r > noPushRow) continue;
            for (int c = 1; c < m - 1; ++c) {
                if (c < j || c > noPushCol) continue;
                Tile pushTile = newGameMap.get(r + dx).get(c + dy);
                Sequence<Item> items = gameMap.get(r).get(c).items();
                for (Item item : items) {
                    if (item.push() || item.you()) {
                        assert r != noPushRow || c != noPushCol;
                        newGameMap.get(r + dx).set(c + dy, pushTile.add(item));
                        pushTile = newGameMap.get(r + dx).get(c + dy);
                    } else {
                        continue;
                    }
                }
            }
        }
        return new GameState(newGameMap, null, level);
    }

    // The whole move operation
    public GameState move(Heading heading) {
        assert !gameMap.isEmpty();
        int n = gameMap.size(), m = gameMap.get(0).size();
        int st_i = 1, dx = 1;
        int st_j = 1, dy = 1;
        if (heading instanceof South) {
            st_i = n - 2;
            dx = -1;
        } else if (heading instanceof East) {
            st_j = m - 2;
            dy = -1;
        }
        GameState newState = new GameState(this.gameMap, null, level);
        for (int i = st_i; i != 0 && i != n - 1; i += dx) {
            for (int j = st_j; j != 0 && j != m - 1; j += dy) {
                newState = newState.singleTileMove(i, j, heading);
            }
        }
        GameState ret = new GameState(newState.gameMap, this, level);
        ret = ret.updateHeadings(heading);
        return ret;
    }

    public static ArrayList<ArrayList<Tile>> fromString(String mapDescription) {
        ArrayList<ArrayList<Tile>> gameMap = new ArrayList<>();
        Sequence<Sequence<Tile>> sequenceMap = stringToSequence(mapDescription);
        for (Sequence<Tile> seq : sequenceMap) {
            ArrayList<Tile> arr = new ArrayList<>();
            for (Tile tile : seq) {
                arr.add(tile);
            }
            gameMap.add(arr);
        }
        return gameMap;
    }

    public static Sequence<Sequence<Tile>> stringToSequence(String mapDescription) {
        return map(line -> map(Tile::fromChar, ofStringCharacters(line)), ofStringLines(mapDescription));
    }

    public GameState applyRules() {
        Sequence<Rule> rules = this.generateRules();
        HashSet<Kind> selfLoop = new HashSet<>();
        for (Rule rule : rules) {
            Kind from = rule.from();
            Kind to = rule.to();
            if (from == to) {
                selfLoop.add(from);
            }
        }

        HashSet<Rule> unWorkRules = new HashSet<>();
        HashMap<Kind, ArrayList<Kind>> objectMap = new HashMap<>();
        HashMap<Kind, ArrayList<Kind>> stateMap = new HashMap<>();

        for (Rule rule : rules) {
            Kind from = rule.from();
            Kind to = rule.to();
            if (from == to) continue;
            if (selfLoop.contains(from)) {
                unWorkRules.add(rule);
            } else {
                Kind key = from.textToIcon();
                if (to.isObjectText()) {
                    Kind value = to.textToIcon();
                    if (!objectMap.containsKey(key)) {
                        objectMap.put(key, new ArrayList<>());
                    }
                    objectMap.get(key).add(value);
                } else if (to.isStateText()) {
                    Kind value = to;
                    if (!stateMap.containsKey(key)) {
                        stateMap.put(key, new ArrayList<>());
                    }
                    stateMap.get(key).add(value);
                } else {
                    assert false;
                }
            }
        }

        assert !gameMap.isEmpty();
        int n = gameMap.size(), m = gameMap.get(0).size();
        ArrayList<ArrayList<Tile>> newGameMap = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < m; ++j) {
                row.add(new Tile(empty()));
            }
            newGameMap.add(row);
        }

        // transform every object and apply new rules.
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Tile tile = gameMap.get(i).get(j);
                Sequence<Item> items = tile.items();
                Sequence<Item> newItems = empty();
                for (Item item : items) {
                    if (objectMap.containsKey(item.name())) {
                        for (Kind kind : objectMap.get(item.name())) {
                            newItems = concat(newItems, of(item.setName(kind).applyRules(stateMap)));
                    }
                    } else {
                        newItems = concat(newItems, of(item.applyRules(stateMap)));
                    }
                }
                newGameMap.get(i).set(j, new Tile(newItems));
            }
        }

        // Following operations will also set texts 'push' and 'stop'
        // make all texts to dark,
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Tile tile = newGameMap.get(i).get(j);
                if (!tile.containsStateText() && !tile.containsObjectText() && !tile.containsIs()) {
                    continue;
                }
                newGameMap.get(i).set(j, tile.setToDark());
            }
        }

        // make un-working texts canceled
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < m - 1; ++j) {
                Tile tile = newGameMap.get(i).get(j);
                if (!tile.containsIs()) continue;
                //left to right;
                if (j != 1 && j != m - 2) {
                    Tile leftTile = newGameMap.get(i).get(j - 1);
                    Tile rightTile = newGameMap.get(i).get(j + 1);
                    if (leftTile.containsObjectText() && rightTile.containsObjectText()) {
                        if (unWorkRules.contains(Rule.getRule(leftTile, rightTile))) {
                            newGameMap.get(i).set(j - 1, leftTile.setToCancel());
                            newGameMap.get(i).set(j, tile.setToCancel());
                            newGameMap.get(i).set(j + 1, rightTile.setToCancel());
                        }
                    }
                }
                //up to down
                if (i != 1 && i != n - 2) {
                    Tile upTile = gameMap.get(i - 1).get(j);
                    Tile downTile = gameMap.get(i + 1).get(j);
                    if (upTile.containsObjectText() && downTile.containsObjectText()) {
                        if (unWorkRules.contains(Rule.getRule(upTile, downTile))) {
                            newGameMap.get(i - 1).set(j, upTile.setToCancel());
                            newGameMap.get(i).set(j, tile.setToCancel());
                            newGameMap.get(i + 1).set(j, downTile.setToCancel());
                        }
                    }
                }
            }
        }

        // make working texts light
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < m - 1; ++j) {
                Tile tile = newGameMap.get(i).get(j);
                if (!tile.containsIs()) continue;
                //left to right;
                if (j != 1 && j != m - 2) {
                    Tile leftTile = newGameMap.get(i).get(j - 1);
                    Tile rightTile = newGameMap.get(i).get(j + 1);
                    if (leftTile.containsObjectText() &&
                        (rightTile.containsObjectText() || rightTile.containsStateText())) {
                        if (!unWorkRules.contains(Rule.getRule(leftTile, rightTile))) {
                            newGameMap.get(i).set(j - 1, leftTile.setToReactive());
                            newGameMap.get(i).set(j, tile.setToReactive());
                            newGameMap.get(i).set(j + 1, rightTile.setToReactive());
                        }
                    }
                }
                //up to down
                if (i != 1 && i != n - 2) {
                    Tile upTile = gameMap.get(i - 1).get(j);
                    Tile downTile = gameMap.get(i + 1).get(j);
                    if (upTile.containsObjectText() &&
                        (downTile.containsObjectText() || downTile.containsStateText())) {
                        if (!unWorkRules.contains(Rule.getRule(upTile, downTile))) {
                            newGameMap.get(i - 1).set(j, upTile.setToReactive());
                            newGameMap.get(i).set(j, tile.setToReactive());
                            newGameMap.get(i + 1).set(j, downTile.setToReactive());
                        }
                    }
                }
            }
        }
        // Now that the move is finished
        return new GameState(newGameMap, this.previousState, level);
    }

    public GameState updateHeadings(Heading heading) {
        ArrayList<ArrayList<Tile>> newGameMap = new ArrayList<>();
        for (int i = 0; i < gameMap.size(); ++i) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < gameMap.get(i).size(); ++j) {
                row.add(new Tile(Item.withHeadings(heading, gameMap.get(i).get(j).items())));
            }
            newGameMap.add(row);
        }
        return new GameState(newGameMap, this.previousState, level);
    }

    public GameState updateLevel(int newLevel) {
        try {
            return
                new GameState(GameState.fromString(
                    Files.readString(Path.of("game" + String.valueOf(newLevel) + ".txt"))), null, newLevel)
                    .applyRules();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
