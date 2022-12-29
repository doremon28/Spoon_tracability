package org.spoon.tracability.processor;

public abstract class Processor<T> {
    /* ATTRIBUTES */
    protected T parser;

    /* CONSTRUCTOR */
    protected Processor(String projectPath) {
        setParser(projectPath);
    }

    /* METHODS */
    public T getParser() {
        return parser;
    }

    public abstract void setParser(String projectPath);
}
