package org.zelo.ast.name;

import org.zelo.ast.NodeList;

import java.util.List;

public class QualifiedNameList extends NodeList<QualifiedName> {
    public QualifiedNameList(List<QualifiedName> qualifiedNameList) {
        super(qualifiedNameList);
    }
}