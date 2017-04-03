package org.zelo.ast.module;

import org.zelo.ast.NodeList;
import org.zelo.ast.module.Function;

import java.util.List;

public class FunctionList extends NodeList<Function> {
    public FunctionList(List<Function> functionList) {
        super(functionList);
    }
}