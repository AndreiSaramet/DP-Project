package model.vocabulary.terminal;

import model.vocabulary.Symbol;

public interface Terminal extends Symbol {
    static boolean isInstance(final Symbol symbol) {
        return symbol instanceof Terminal;
    }
}
