package org.zelo.ast.name;

import org.zelo.ast.Node;

public class Name extends Node {
    private final String id;

    @Override
    public String toString() {
        return id;
    }

    public Name(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
