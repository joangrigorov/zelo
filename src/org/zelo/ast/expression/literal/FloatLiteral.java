package org.zelo.ast.expression.literal;

import java.math.BigDecimal;

public class FloatLiteral extends Literal {
    private final BigDecimal decimal;

    public FloatLiteral(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
