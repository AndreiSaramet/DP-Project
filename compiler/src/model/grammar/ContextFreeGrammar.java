package model.grammar;

import model.production.context_free.ContextFreeProduction;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.Collection;

public class ContextFreeGrammar<T extends ContextFreeProduction> extends GrammarImpl<T> {
    private final Grammar<? extends T> grammar;

    public ContextFreeGrammar(final Grammar<? extends T> grammar) {
        super(grammar.nonTerminals(), grammar.terminals(), grammar.productions(), grammar.startNonTerminal());
        this.grammar = grammar;
    }

    public Collection<? extends T> productionsOf(final NonTerminal nonTerminal) {
        if (this.grammar.containsNonTerminal(nonTerminal)) {
            return this.grammar.productions().stream().filter(el -> nonTerminal.equals(el.leftSideSymbol())).toList();
        } else {
            throw new RuntimeException(String.format("The Non-Terminal %s does not belong to the grammar", nonTerminal.toString()));
        }
    }

    public Collection<? extends T> productionsOf(final String nonTerminal) {
        if (this.grammar.containsNonTerminal(nonTerminal)) {
            return this.grammar.productions().stream().filter(el -> nonTerminal.equals(el.leftSideSymbol().value())).toList();
        } else {
            throw new RuntimeException(String.format("The Non-Terminal %s does not belong to the grammar", nonTerminal));
        }
    }
}
