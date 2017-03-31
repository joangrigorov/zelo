package org.zelo.ast.expression;

import org.zelo.ast.Expression;

import java.util.List;

public class Call extends Expression {
    private final Expression caller;
    private final List<Expression> arguments;

    public Call(Expression caller, List<Expression> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }
}
