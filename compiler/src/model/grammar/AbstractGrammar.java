package model.grammar;

import model.production.Production;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;
import model.vocabulary.terminal.Terminal;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractGrammar<T extends Production> implements Grammar<T> {
    protected final Collection<? extends NonTerminal> nonTerminalSet;

    protected final Collection<? extends Terminal> terminalSet;


    protected final Collection<? extends T> productionSet;

    protected final NonTerminal startNonTerminal;

    public AbstractGrammar(Collection<? extends NonTerminal> nonTerminalSet, Collection<? extends Terminal> terminalSet, Collection<? extends T> productionSet, NonTerminal startNonTerminal) {
        Objects.requireNonNull(nonTerminalSet, String.format("The set of %s must not be null", NonTerminal.class.getName()));
        Objects.requireNonNull(terminalSet, String.format("The set of %s must not be null", Terminal.class.getName()));
        Objects.requireNonNull(productionSet, String.format("The set of %s must not be null", Production.class.getName()));
        Objects.requireNonNull(startNonTerminal, String.format("The start %s must not be null", NonTerminal.class.getName()));

        this.nonTerminalSet = Set.copyOf(nonTerminalSet);
        this.terminalSet = Set.copyOf(terminalSet);
        this.productionSet = Set.copyOf(productionSet);
        this.startNonTerminal = startNonTerminal;
    }

    @Override
    public Collection<? extends NonTerminal> nonTerminals() {
        return this.nonTerminalSet;
    }

    @Override
    public Collection<? extends Terminal> terminals() {
        return this.terminalSet;
    }

    @Override
    public Collection<? extends T> productions() {
        return this.productionSet;
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
    public boolean containsNonTerminal(Symbol symbol) {
        return this.hasNonTerminal(symbol);
    }

    @Override
    public boolean containsTerminal(Symbol symbol) {
        return this.hasTerminal(symbol);
    }

    @Override
    public boolean containsProduction(Production production) {
        return this.productionSet.contains(production);
    }

    private boolean hasNonTerminal(final Symbol symbol) {
        return symbol instanceof NonTerminal && this.nonTerminalSet.contains(symbol);
    }

    private boolean hasTerminal(final Symbol symbol) {
        return symbol instanceof Terminal && this.terminalSet.contains(symbol);
    }
}
