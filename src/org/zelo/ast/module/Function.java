package org.zelo.ast.module;

import org.zelo.ast.Name;
import org.zelo.ast.Node;
import org.zelo.ast.type.Type;

import java.util.List;

public class Function extends Node {
    private final Type type;
    private final Name name;
    private final List<Declaration> declarations;

    public Function(Type type, Name name, List<Declaration> declarations) {
        this.type = type;
        this.name = name;
        this.declarations = declarations;
    }

    public Name getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }
}
