package org.zelo.ast.function;

import org.zelo.ast.Pattern;
import org.zelo.ast.expression.literal.Literal;

public class LiteralPattern extends Pattern {
    private final Literal literal;

    public LiteralPattern(Literal literal) {
        this.literal = literal;
    }

    public Literal getLiteral() {
        return literal;
    }
}
