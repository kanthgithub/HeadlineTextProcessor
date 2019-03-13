package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;

public interface TransformerChain {
    public abstract void setNext(TransformerChain nextInTransformerChain);

    void transform(HeadlineTextModel text);
}
