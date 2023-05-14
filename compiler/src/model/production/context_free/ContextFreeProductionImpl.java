package model.production.context_free;

import model.production.AbstractProduction;
import model.vocabulary.Symbol;
import model.vocabulary.nonterminal.NonTerminal;

import java.util.List;

public class ContextFreeProductionImpl extends AbstractProduction implements ContextFreeProduction {
    public ContextFreeProductionImpl(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);
        ContextFreeProduction.validateLeftSide(leftSide);
        ContextFreeProduction.validateRightSide(rightSide);
    }

    public ContextFreeProductionImpl(final NonTerminal leftSide, final List<? extends Symbol> rightSide) {
        super(List.of(leftSide), rightSide);
        ContextFreeProduction.validateLeftSide(List.of(leftSide));
        ContextFreeProduction.validateRightSide(rightSide);
    }

    @Override
    public NonTerminal leftSideSymbol() {
        return (NonTerminal) this.leftSide.get(0);
    }
}
