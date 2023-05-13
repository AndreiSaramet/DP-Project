package model.production.unrestricted;

import model.production.Production;
import model.vocabulary.Symbol;

import java.util.List;

public interface UnrestrictedProduction extends Production {
    static boolean isInstance(final Production production) {
        return production instanceof UnrestrictedProduction;
    }

    static void validateLeftSide(final List<? extends Symbol> leftSide) {
        Production.validateSide(leftSide);
        if (leftSide.isEmpty()) {
            throw new IllegalArgumentException(String.format("Left side of %s must not be empty", UnrestrictedProduction.class.getName()));
        }
    }

    static void validateRightSide(final List<? extends Symbol> rightSide) {
        Production.validateSide(rightSide);
    }
}
