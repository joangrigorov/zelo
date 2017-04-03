package org.zelo.ast.util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.zelo.ast.Node;

public class SourceLocationHydrator {
    public static <N extends Node> N hydrateSourceLocation(N astNode, ParserRuleContext context) {
        astNode.setSourceLocation(new SourceLocation(context.start.getLine(), context.start.getCharPositionInLine()));

        return astNode;
    }
    public static <N extends Node> N hydrateSourceLocation(N astNode, Token token) {
        astNode.setSourceLocation(new SourceLocation(token.getLine(), token.getCharPositionInLine()));

        return astNode;
    }
}
