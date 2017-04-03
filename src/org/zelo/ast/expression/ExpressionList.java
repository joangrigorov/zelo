package org.zelo.ast.expression;

import org.zelo.ast.NodeList;

import java.util.List;

public class ExpressionList extends NodeList<Expression> {
    public ExpressionList(List<Expression> expressionList) {
        super(expressionList);
    }
}