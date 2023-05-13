package model.production.context_dependent;

import model.production.AbstractProduction;
import model.vocabulary.Symbol;

import java.util.List;

public final class ContextDependentProductionImpl extends AbstractProduction implements ContextDependentProduction {
    public ContextDependentProductionImpl(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);
        ContextDependentProduction.validateLeftSide(leftSide);
        ContextDependentProduction.validateRightSide(rightSide);
    }
}
