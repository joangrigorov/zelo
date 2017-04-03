package org.zelo.ast.expression;

public class Call extends Expression {
    private final Expression caller;
    private final ExpressionList arguments;

    public Call(Expression caller, ExpressionList arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    public Expression getCaller() {
        return caller;
    }
}
