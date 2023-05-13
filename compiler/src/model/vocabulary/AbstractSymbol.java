package model.vocabulary;

import java.util.Objects;

public abstract class AbstractSymbol implements Symbol {
    private final String value;

    public AbstractSymbol(final String value) {
        Objects.requireNonNull(value, "The symbol cannot be null");
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        AbstractSymbol other = (AbstractSymbol) o;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
