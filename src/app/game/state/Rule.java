package src.app.game.state;

// a `Rule` is an edge, from one Kind to another Kind.
public record Rule(Kind from, Kind to) {
    // get a rule from two Tiles
    public static Rule getRule(Tile first, Tile second) {
        return new Rule(getText(first), getText(second));
    }
    // return a Text from a Tile if exists
    public static Kind getText(Tile tile) {
        int count = 0;
        Kind kind = null;
        for(Item item : tile.items()) {
            if(item.name().isObjectText() || item.name().isStateText()) {
                count++;
                kind = item.name();
            }
        }
        assert count > 0 && count <= 1;
        return kind;
    }
}