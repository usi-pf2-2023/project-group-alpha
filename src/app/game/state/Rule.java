package src.app.game.state;

// a `Rule` is an edge, from one Kind to another Kind.
public record Rule(Kind from, Kind to) {
    // Three items can be linked into one rule,
    // but we can ignore the connector text
    // so two items build a `Rule`
    public Rule getRule(Item first, Item second) {
        return new Rule(first.name(), second.name());
    }
}