package org.zelo.ast.module;

import org.zelo.ast.expression.Expression;
import org.zelo.ast.Node;
import org.zelo.ast.function.PatternList;

public class Declaration extends Node {
    private final PatternList signature;
    private final Expression body;

    public Declaration(PatternList signature, Expression body) {
        this.signature = signature;
        this.body = body;
    }

    public PatternList getSignature() {
        return signature;
    }

    public Expression getBody() {
        return body;
    }
}
