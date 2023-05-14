package model.grammar;

import model.production.Production;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;
import model.vocabulary.terminal.Terminal;

import java.util.Collection;

public interface Grammar<T extends Production> {
    Collection<? extends NonTerminal> nonTerminals();

    Collection<? extends Terminal> terminals();

    Collection<? extends T> productions();

    NonTerminal startNonTerminal();

    boolean containsSymbol(final Symbol symbol);


    boolean containsNonTerminal(final Symbol symbol);

    boolean containsTerminal(final Symbol symbol);

    boolean containsProduction(final Production production);
}
