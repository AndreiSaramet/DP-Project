package model.vocabulary.terminal;

import model.vocabulary.AbstractSymbol;
import model.vocabulary.Symbol;

import java.util.HashMap;
import java.util.Map;

public final class Terminal extends AbstractSymbol {
    private Terminal(final String value) {
        super(value);
    }

    private static final Map<String, Terminal> terminalCache = new HashMap<>();

    public static Terminal from(final String value) {
        if (terminalCache.containsKey(value)) {
            return terminalCache.get(value);
        }
        final Terminal terminal = new Terminal(value);
        terminalCache.put(value, terminal);
        return terminal;
    }
}
