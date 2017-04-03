package org.zelo.ast;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class NodeList<T extends Node> extends Node implements Iterable<T> {

    private final List<T> nodes;

    public NodeList(List<T> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Iterator<T> iterator() {
        return nodes.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        nodes.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return nodes.spliterator();
    }

    public int size() {
        return nodes.size();
    }
}
