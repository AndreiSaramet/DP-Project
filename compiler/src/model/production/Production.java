package model.production;

import model.vocabulary.Symbol;

import java.util.List;
import java.util.Objects;

public interface Production {
    String EPSILON = "ε";

    String SIDES_SEPARATOR = "->";

    List<? extends Symbol> leftSide();

    List<? extends Symbol> rightSide();

    static String sideToString(final List<? extends Symbol> side) {
        Objects.requireNonNull(side, String.format("A side of a %s must not be null", Production.class.getName()));
        final StringBuilder builder = new StringBuilder();
        if (side.isEmpty()) {
            builder.append(EPSILON);
        } else {
            side.stream()
                    .map(Symbol::value)
                    .forEach(builder::append);
        }
        return builder.toString();
    }

    static void validateLeftSide(final List<? extends Symbol> leftSide) {
        Objects.requireNonNull(leftSide, String.format("The left side of a %s must not be null", Production.class.getName()));
    }

    static void validateRightSide(final List<? extends Symbol> rightSide) {
        Objects.requireNonNull(rightSide, String.format("The right side of a %s must not be null", Production.class.getName()));
    }
}
