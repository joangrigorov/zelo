package org.zelo.ast.expression.literal;

public class StringLiteral extends Literal {
    private final String string;

    public StringLiteral(String string) {
        this.string = string;
    }
}
