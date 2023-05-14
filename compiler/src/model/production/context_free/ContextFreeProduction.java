package model.production.context_free;

import model.production.Production;
import model.production.context_dependent.ContextDependentProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.List;

public interface ContextFreeProduction extends ContextDependentProduction {
    NonTerminal leftSideSymbol();

    static boolean isInstance(final Production production) {
        return production instanceof ContextFreeProduction;
    }

    static void validateLeftSide(final List<? extends Symbol> leftSide) {
        ContextDependentProduction.validateLeftSide(leftSide);
        if (leftSide.size() != 1) {
            throw new IllegalArgumentException(String.format("The left side of a %s must contain exactly one %s symbol", ContextFreeProduction.class.getName(), NonTerminal.class.getName()));
        }
    }

    static void validateRightSide(final List<? extends Symbol> rightSide) {
        ContextDependentProduction.validateRightSide(rightSide);
    }
}
