package it.unicam.cs.mp.robotapplication.api.model;

import java.util.Objects;

/**
 * Represents a condition identified by a label.
 */
public class Condition {

    private final String label;

    public Condition(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(label, condition.label);
    }

    public String getLabel() {
        return label;
    }
}
