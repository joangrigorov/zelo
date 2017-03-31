package org.zelo.ast;

import java.util.List;

public class QualifiedName extends Node {
    private final List<Name> orderedNames;
    private final Name name;

    public QualifiedName(List<Name> namespace, Name name) {
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
