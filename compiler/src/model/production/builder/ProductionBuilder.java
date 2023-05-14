package model.production.builder;

import model.production.Production;
import model.vocabulary.Symbol;

import java.util.List;

public interface ProductionBuilder<T extends Production> {
    ProductionBuilder<T> leftSide(final Symbol... leftSide);

    ProductionBuilder<T> leftSide(final List<? extends Symbol> leftSide);

    ProductionBuilder<T> rightSide(final Symbol... rightSide);

    ProductionBuilder<T> rightSide(final List<? extends Symbol> rightSide);

    ProductionBuilder<T> production(final Production production);

    T build();
}
