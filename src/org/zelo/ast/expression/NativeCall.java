package org.zelo.ast.expression;

import org.zelo.ast.Expression;
import org.zelo.ast.function.essential.Essential;

import java.util.List;

public class NativeCall extends Expression {
    private final Essential nativeFunction;
    private final List<Expression> arguments;

    public NativeCall(Essential nativeFunction, List<Expression> arguments) {
        this.nativeFunction = nativeFunction;
        this.arguments = arguments;
    }
}
