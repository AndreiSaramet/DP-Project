package model.production;

import model.vocabulary.Symbol;

import java.util.List;
import java.util.Objects;

public abstract class AbstractProduction implements Production {
    protected final List<? extends Symbol> leftSide;

    protected final List<? extends Symbol> rightSide;

    protected AbstractProduction(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        this.validateLeftSide(leftSide);
        this.validateRightSide(rightSide);

        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    protected void validateLeftSide(final List<? extends Symbol> leftSide) {
        Objects.requireNonNull(leftSide, String.format("The left side of a %s must not be null", Production.class.getName()));
    }

    protected void validateRightSide(final List<? extends Symbol> rightSide) {
        Objects.requireNonNull(rightSide, String.format("The right side of a %s must not be null", Production.class.getName()));
    }

    @Override
    public List<? extends Symbol> leftSide() {
        return this.leftSide;
    }

    @Override
    public List<? extends Symbol> rightSide() {
        return this.rightSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        AbstractProduction other = (AbstractProduction) o;
        return Objects.equals(leftSide, other.leftSide) && Objects.equals(rightSide, other.rightSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftSide, rightSide);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", sideToString(this.leftSide), Production.SIDES_SEPARATOR, sideToString(this.rightSide));
    }

    private static String sideToString(final List<? extends Symbol> side) {
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
}
