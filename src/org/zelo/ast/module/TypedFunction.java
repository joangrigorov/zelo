package org.zelo.ast.module;

import org.zelo.ast.name.Name;
import org.zelo.ast.Node;
import org.zelo.ast.type.Type;

public class TypedFunction extends Function {
    private final Type type;

    public TypedFunction(Type type, Name name, DeclarationList declarationList) {
        super(name, declarationList);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
