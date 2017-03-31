package org.zelo.ast.expression;

import org.zelo.ast.Expression;

public class Composition extends Expression {
    private final Expression left;
    private final Expression right;

    public Composition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
