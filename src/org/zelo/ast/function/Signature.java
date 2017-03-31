package org.zelo.ast.function;

import org.zelo.ast.Node;
import org.zelo.ast.Pattern;

import java.util.List;

public class Signature extends Node {
    private final List<Pattern> patterns;

    public Signature(List<Pattern> patterns) {
        this.patterns = patterns;
    }
}
