package model.production.context_free;

import model.production.builder.ProductionBuilder;
import model.production.builder.ProductionBuilderImpl;
import model.production.context_dependent.ContextDependentProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.List;

public class ContextFreeProduction extends ContextDependentProduction {
    protected ContextFreeProduction(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);
    }

    protected ContextFreeProduction(final NonTerminal leftSide, final List<? extends Symbol> rightSide) {
        super(List.of(leftSide), rightSide);
    }

    public NonTerminal leftSideSymbol() {
        return (NonTerminal) this.leftSide.get(0);
    }

    @Override
    protected void validateLeftSide(final List<? extends Symbol> leftSide) {
        super.validateLeftSide(leftSide);
        if (leftSide.size() != 1) {
            throw new IllegalArgumentException(String.format("The left side of a %s must contain exactly one %s symbol", ContextFreeProduction.class.getName(), NonTerminal.class.getName()));
        }
    }

    @Override
    protected void validateRightSide(final List<? extends Symbol> rightSide) {
        super.validateRightSide(rightSide);
    }

    public static ProductionBuilder<? extends ContextFreeProduction> builder() {
        return new ProductionBuilderImpl<>(ContextFreeProduction::new);
    }
}
