package org.zelo.ast.module;

import org.zelo.ast.Expression;
import org.zelo.ast.Node;
import org.zelo.ast.function.Signature;

public class Declaration extends Node {
    private final Signature signature;
    private final Expression body;

    public Declaration(Signature signature, Expression body) {
        this.signature = signature;
        this.body = body;
    }
}
