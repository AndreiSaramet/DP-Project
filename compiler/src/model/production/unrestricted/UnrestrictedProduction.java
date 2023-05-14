package model.production.unrestricted;

import model.production.AbstractProduction;
import model.production.builder.ProductionBuilder;
import model.production.builder.ProductionBuilderImpl;
import model.vocabulary.Symbol;

import java.util.List;

public class UnrestrictedProduction extends AbstractProduction {
    protected UnrestrictedProduction(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    protected void validateLeftSide(List<? extends Symbol> leftSide) {
        super.validateLeftSide(leftSide);
        if (leftSide.isEmpty()) {
            throw new IllegalArgumentException(String.format("The left side of %s must not be empty", UnrestrictedProduction.class.getName()));
        }
    }

    @Override
    protected void validateRightSide(List<? extends Symbol> rightSide) {
        super.validateRightSide(rightSide);
    }

    public static ProductionBuilder<? extends UnrestrictedProduction> builder() {
        return new ProductionBuilderImpl<>(UnrestrictedProduction::new);
    }
}
