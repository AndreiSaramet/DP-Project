package model.production.context_dependent;

import model.production.Production;
import model.production.unrestricted.UnrestrictedProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.List;

public interface ContextDependentProduction extends UnrestrictedProduction {
    static boolean isInstance(final Production production) {
        return production instanceof ContextDependentProduction;
    }

    static void validateLeftSide(final List<? extends Symbol> leftSide) {
        UnrestrictedProduction.validateLeftSide(leftSide);
        final boolean hasNoNonTerminal = leftSide
                .stream()
                .noneMatch(NonTerminal::isInstance);
        if (hasNoNonTerminal) {
            throw new IllegalArgumentException(String.format("Left side of %s must contain at least one %s symbol", ContextDependentProduction.class.getName(), NonTerminal.class.getName()));
        }
    }

    static void validateRightSide(final List<? extends Symbol> rightSide) {
        UnrestrictedProduction.validateRightSide(rightSide);
    }
}
