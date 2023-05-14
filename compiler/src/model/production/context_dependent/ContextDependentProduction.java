package model.production.context_dependent;

import model.production.builder.ProductionBuilder;
import model.production.builder.ProductionBuilderImpl;
import model.production.unrestricted.UnrestrictedProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.List;

public class ContextDependentProduction extends UnrestrictedProduction {
    protected ContextDependentProduction(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);

    }

    @Override
    protected void validateLeftSide(final List<? extends Symbol> leftSide) {
        super.validateLeftSide(leftSide);
        final boolean hasNoNonTerminal = leftSide
                .stream()
                .noneMatch(symbol -> symbol instanceof NonTerminal);
        if (hasNoNonTerminal) {
            throw new IllegalArgumentException(String.format("The left side of %s must contain at least one %s symbol", ContextDependentProduction.class.getName(), NonTerminal.class.getName()));
        }
    }

    @Override
    protected void validateRightSide(final List<? extends Symbol> rightSide) {
        super.validateRightSide(rightSide);
    }

    public static ProductionBuilder<? extends ContextDependentProduction> builder() {
        return new ProductionBuilderImpl<>(ContextDependentProduction::new);
    }
}
