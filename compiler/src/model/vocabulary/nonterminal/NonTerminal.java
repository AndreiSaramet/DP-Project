package model.vocabulary.nonterminal;

import model.vocabulary.Symbol;

public interface NonTerminal extends Symbol {
    static boolean isInstance(final Symbol symbol) {
        return symbol instanceof NonTerminal;
    }
}
