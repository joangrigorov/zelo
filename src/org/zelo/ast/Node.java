package org.zelo.ast;

import org.zelo.ast.util.SourceLocation;

public abstract class Node {

    private SourceLocation sourceLocation;

    public void setSourceLocation(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }
}
