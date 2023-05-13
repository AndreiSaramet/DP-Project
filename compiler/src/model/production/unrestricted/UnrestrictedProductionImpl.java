package model.production.unrestricted;

import model.production.AbstractProduction;
import model.vocabulary.Symbol;

import java.util.List;

public final class UnrestrictedProductionImpl extends AbstractProduction implements UnrestrictedProduction {
    public UnrestrictedProductionImpl(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);
        UnrestrictedProduction.validateLeftSide(leftSide);
        UnrestrictedProduction.validateRightSide(rightSide);
    }
}
