package org.zelo.ast.name;

import org.zelo.ast.Node;

import java.util.List;

public class QualifiedName extends Node {
    private final Names orderedNames;
    private final Name name;

    public QualifiedName(Names namespace, Name name) {
        this.orderedNames = namespace;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        orderedNames.forEach(name -> stringBuilder.append(name).append("::"));
        stringBuilder.append(name.toString());

        return stringBuilder.toString();
    }
}
