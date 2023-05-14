package model.production.unrestricted;

import model.production.Production;
import model.production.builder.ProductionBuilder;
import model.production.builder.ProductionBuilderImpl;
import model.vocabulary.Symbol;

import java.util.List;

public interface UnrestrictedProduction extends Production {
    static boolean isInstance(final Production production) {
        return production instanceof UnrestrictedProduction;
    }

    static void validateLeftSide(final List<? extends Symbol> leftSide) {
        Production.validateLeftSide(leftSide);
        if (leftSide.isEmpty()) {
            throw new IllegalArgumentException(String.format("The left side of %s must not be empty", UnrestrictedProduction.class.getName()));
        }
    }

    static void validateRightSide(final List<? extends Symbol> rightSide) {
        Production.validateRightSide(rightSide);
    }

    static ProductionBuilder<? extends UnrestrictedProduction> builder() {
        return new ProductionBuilderImpl<>(UnrestrictedProductionImpl::new);
    }
}
