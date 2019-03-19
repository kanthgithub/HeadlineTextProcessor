package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FirstWordTransformer implements TransformerChain {

    private static final Logger log = LoggerFactory.getLogger(FirstWordTransformer.class);

    private TransformerChain nextInChain;

    @Override
    public void transform(HeadlineTextModel text) {
        if (StringUtils.isEmpty(text.getHeadlineText())) {
            log.error("Invalid Request content for FirstWordTransformer");
        } else {
            //capitalize first word
            String headlineText = text.getHeadlineText();

            String newStr = headlineText.substring(0, 1).toUpperCase() + headlineText.substring(1);

            text.setHeadlineText(newStr);

            if(nextInChain!=null) {
                nextInChain.transform(text);
            }
        }
    }


    @Override
    public void setNext(TransformerChain nextInTransformerChain) {
        nextInChain = nextInTransformerChain;
    }
}
