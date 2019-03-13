package io.headlines.transformers;

public interface TransformerChain {
    public abstract void setNext(TransformerChain nextInTransformerChain);
    public abstract void process(String request);
}
