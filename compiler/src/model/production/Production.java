package model.production;

import model.vocabulary.Symbol;

import java.util.List;

public interface Production {
    String EPSILON = "ε";

    String SIDES_SEPARATOR = "->";

    List<? extends Symbol> leftSide();

    List<? extends Symbol> rightSide();
}
