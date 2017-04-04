package org.zelo.ast.module;

import org.zelo.ast.Node;
import org.zelo.ast.name.Name;

public class Function extends Node {
    private final Name name;
    private final DeclarationList declarationList;

    public Function(Name name, DeclarationList declarationList) {
        this.declarationList = declarationList;
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public DeclarationList getDeclarations() {
        return declarationList;
    }
}
