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
        Objects.requireNonNull(side, "A side of a production must not be null");
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
}
