package io.headlines.transformers;

//chain of responsibility pattern used to capitalize words
public interface HeadlineTextTransformer extends TransformerChain {

     String transform(String text);
}
