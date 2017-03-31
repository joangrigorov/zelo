package org.zelo.ast;

public abstract class Node {

    private SourceLocation sourceLocation;

    public void setSourceLocation(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }
}
