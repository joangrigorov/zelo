package org.zelo.ast.module;

import org.zelo.ast.name.Name;
import org.zelo.ast.Node;
import org.zelo.ast.type.Type;

public class Function extends Node {
    private final Type type;
    private final Name name;
    private final DeclarationList declarationList;

    public Function(Type type, Name name, DeclarationList declarationList) {
        this.type = type;
        this.name = name;
        this.declarationList = declarationList;
    }

    public Name getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public DeclarationList getDeclarations() {
        return declarationList;
    }
}
