package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;

public interface TransformerChain {
    void setNext(TransformerChain nextInTransformerChain);

    void transform(HeadlineTextModel text);
}
