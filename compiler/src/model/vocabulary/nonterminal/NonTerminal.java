package model.vocabulary.nonterminal;

import model.vocabulary.AbstractSymbol;

import java.util.HashMap;
import java.util.Map;

public final class NonTerminal extends AbstractSymbol {
    private NonTerminal(final String value) {
        super(value);
    }

    private static final Map<String, NonTerminal> nonTerminalCache = new HashMap<>();

    public static NonTerminal from(final String value) {
        if (nonTerminalCache.containsKey(value)) {
            return nonTerminalCache.get(value);
        }
        final NonTerminal nonTerminal = new NonTerminal(value);
        nonTerminalCache.put(value, nonTerminal);
        return nonTerminal;
    }
}
