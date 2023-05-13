package model.production;

import model.vocabulary.Symbol;

import java.util.List;
import java.util.Objects;

public abstract class AbstractProduction implements Production {
    private final List<? extends Symbol> leftSide;

    private final List<? extends Symbol> rightSide;

    public AbstractProduction(final List<? extends Symbol> leftSide, final List<? extends Symbol> rightSide) {
        Objects.requireNonNull(leftSide);
        Objects.requireNonNull(rightSide);

        this.leftSide = leftSide;
        this.rightSide = rightSide;
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
        return String.format("%s %s %s", Production.sideToString(this.leftSide), Production.SIDES_SEPARATOR, Production.sideToString(this.rightSide));
    }
}
