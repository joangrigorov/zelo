package org.zelo.ast;

import org.zelo.ast.module.Function;

import java.util.List;

public class Module extends Node {
    private final QualifiedName name;
    private final List<Name> exported;
    private final List<QualifiedName> imported;
    private final List<Function> functions;

    public Module(QualifiedName name, List<Name> exported, List<QualifiedName> imported, List<Function> functions) {
        this.name = name;
        this.exported = exported;
        this.imported = imported;
        this.functions = functions;
    }

    public QualifiedName getName() {
        return name;
    }

    public List<Name> getExported() {
        return exported;
    }

    public List<QualifiedName> getImported() {
        return imported;
    }

    public List<Function> getFunctions() {
        return functions;
    }
}
