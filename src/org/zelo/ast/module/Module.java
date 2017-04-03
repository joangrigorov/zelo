package org.zelo.ast.module;

import org.zelo.ast.name.Names;
import org.zelo.ast.Node;
import org.zelo.ast.name.QualifiedName;
import org.zelo.ast.name.QualifiedNameList;

public class Module extends Node {
    private final QualifiedName name;
    private final Names exported;
    private final QualifiedNameList imported;
    private final FunctionList functionList;

    public Module(QualifiedName name, Names exported, QualifiedNameList imported, FunctionList functionList) {
        this.name = name;
        this.exported = exported;
        this.imported = imported;
        this.functionList = functionList;
    }

    public QualifiedName getName() {
        return name;
    }

    public Names getExported() {
        return exported;
    }

    public QualifiedNameList getImported() {
        return imported;
    }

    public FunctionList getFunctions() {
        return functionList;
    }
}
