package model.grammar;

import model.production.Production;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;
import model.vocabulary.terminal.Terminal;

import java.util.Collection;
import java.util.Objects;

public class GrammarImpl<T extends Production> implements Grammar<T> {
    private final Collection<? extends NonTerminal> nonTerminalCollection;

    private final Collection<? extends Terminal> terminalCollection;


    private final Collection<? extends T> productionCollection;

    private final NonTerminal startNonTerminal;

    public GrammarImpl(Collection<? extends NonTerminal> nonTerminalSet, Collection<? extends Terminal> terminalSet, Collection<? extends T> productionSet, NonTerminal startNonTerminal) {
        Objects.requireNonNull(nonTerminalSet, String.format("The set of %s must not be null", NonTerminal.class.getName()));
        Objects.requireNonNull(terminalSet, String.format("The set of %s must not be null", Terminal.class.getName()));
        Objects.requireNonNull(productionSet, String.format("The set of %s must not be null", Production.class.getName()));
        Objects.requireNonNull(startNonTerminal, String.format("The start %s must not be null", NonTerminal.class.getName()));
        if (!nonTerminalSet.contains(startNonTerminal)) {
            throw new IllegalArgumentException(String.format("The start %s must belong to the %s Collection", NonTerminal.class.getName(), NonTerminal.class.getName()));
        }

        this.nonTerminalCollection = nonTerminalSet;
        this.terminalCollection = terminalSet;
        this.productionCollection = productionSet;
        this.startNonTerminal = startNonTerminal;
    }

    @Override
    public Collection<? extends NonTerminal> nonTerminals() {
        return this.nonTerminalCollection;
    }

    @Override
    public Collection<? extends Terminal> terminals() {
        return this.terminalCollection;
    }

    @Override
    public Collection<? extends T> productions() {
        return this.productionCollection;
    }

    @Override
    public NonTerminal startNonTerminal() {
        return startNonTerminal;
    }

    @Override
    public boolean containsSymbol(Symbol symbol) {
        return this.hasNonTerminal(symbol) || this.hasTerminal(symbol);
    }

    @Override
    public boolean containsSymbol(String symbol) {
        return this.hasNonTerminal(symbol) || this.hasTerminal(symbol);
    }

    @Override
    public boolean containsNonTerminal(Symbol nonTerminal) {
        return this.hasNonTerminal(nonTerminal);
    }

    @Override
    public boolean containsNonTerminal(String symbol) {
        return this.hasNonTerminal(symbol);
    }

    @Override
    public boolean containsTerminal(Symbol terminal) {
        return this.hasTerminal(terminal);
    }

    @Override
    public boolean containsTerminal(String symbol) {
        return this.hasTerminal(symbol);
    }

    @Override
    public boolean containsProduction(Production production) {
        return this.productionCollection.contains(production);
    }

    private boolean hasNonTerminal(String symbol) {
        return this.nonTerminalCollection.stream().anyMatch(el -> symbol.equals(el.value()));
    }

    private boolean hasNonTerminal(Symbol symbol) {
        return symbol instanceof NonTerminal && this.nonTerminalCollection.contains(symbol);
    }

    private boolean hasTerminal(String symbol) {
        return this.terminalCollection.stream().anyMatch(el -> symbol.equals(el.value()));
    }

    private boolean hasTerminal(Symbol symbol) {
        return symbol instanceof Terminal && this.terminalCollection.contains(symbol);
    }
}
