package model.production.builder;

import model.production.Production;
import model.vocabulary.Symbol;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class ProductionBuilderImpl<T extends Production> implements ProductionBuilder<T> {
    private List<? extends Symbol> leftSide;

    private List<? extends Symbol> rightSide;

    private final BiFunction<List<? extends Symbol>, List<? extends Symbol>, ? extends T> buildFunction;

    public ProductionBuilderImpl(final BiFunction<List<? extends Symbol>, List<? extends Symbol>, ? extends T> buildFunction) {
        this.leftSide = List.of();
        this.rightSide = List.of();
        this.buildFunction = buildFunction;
    }

    @Override
    public ProductionBuilder<T> leftSide(Symbol... leftSide) {
        Objects.requireNonNull(leftSide, "The left side of a production must not be null");
        this.leftSide = List.of(leftSide);
        return this;
    }

    @Override
    public ProductionBuilder<T> leftSide(List<? extends Symbol> leftSide) {
        Objects.requireNonNull(leftSide, "The left side of a production must not be null");
        this.leftSide = List.copyOf(leftSide);
        return this;
    }

    @Override
    public ProductionBuilder<T> rightSide(Symbol... rightSide) {
        Objects.requireNonNull(rightSide, "The right side of a production must not be null");
        this.rightSide = List.of(rightSide);

        return this;
    }

    @Override
    public ProductionBuilder<T> rightSide(List<? extends Symbol> rightSide) {
        Objects.requireNonNull(rightSide, "The right side of a production must not be null");
        this.rightSide = List.copyOf(rightSide);

        return this;
    }

    @Override
    public ProductionBuilder<T> production(final Production production) {
        Objects.requireNonNull(production, "A production must not be null");
        this.leftSide = List.copyOf(leftSide);
        this.rightSide = List.copyOf(rightSide);

        return this;
    }

    @Override
    public T build() {
        return this.buildFunction.apply(this.leftSide, this.rightSide);
    }
}
