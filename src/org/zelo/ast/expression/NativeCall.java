package org.zelo.ast.expression;

import org.zelo.ast.expression.essential_functions.Essential;

public class NativeCall extends Call {
    public NativeCall(Essential caller, ExpressionList arguments) {
        super(caller, arguments);
    }
}
