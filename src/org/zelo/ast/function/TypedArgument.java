package org.zelo.ast.function;

import org.zelo.ast.Name;
import org.zelo.ast.Pattern;
import org.zelo.ast.type.Type;

public class TypedArgument extends Pattern {
    private final Type visit;
    private final Name name;

    public TypedArgument(Type visit, Name name) {
        this.visit = visit;
        this.name = name;
    }
}
