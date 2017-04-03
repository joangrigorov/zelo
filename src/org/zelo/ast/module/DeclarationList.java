package org.zelo.ast.module;

import org.zelo.ast.NodeList;

import java.util.List;

public class DeclarationList extends NodeList<Declaration> {
    public DeclarationList(List<Declaration> declarationList) {
        super(declarationList);
    }
}