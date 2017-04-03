package org.zelo.ast.expression;

import org.zelo.ast.name.Name;

public class Symbol extends Expression {
    private final Name name;

    public Symbol(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
