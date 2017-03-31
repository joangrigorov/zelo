package org.zelo.ast.function;

import org.zelo.ast.Name;
import org.zelo.ast.Pattern;
import org.zelo.ast.type.Type;

public class TypedArgument extends Pattern {
    private final Type type;
    private final Name name;

    public TypedArgument(Type visit, Name name) {
        this.type = visit;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }
}
