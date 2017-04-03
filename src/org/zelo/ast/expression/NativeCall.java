package org.zelo.ast.expression;

import org.zelo.ast.function.essential.Essential;

public class NativeCall extends Expression {
    private final Essential nativeFunction;
    private final ExpressionList arguments;

    public NativeCall(Essential nativeFunction, ExpressionList arguments) {
        this.nativeFunction = nativeFunction;
        this.arguments = arguments;
    }

    public Essential getNativeFunction() {
        return nativeFunction;
    }

    public ExpressionList getArguments() {
        return arguments;
    }
}
