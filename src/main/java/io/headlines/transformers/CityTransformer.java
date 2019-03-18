package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import io.headlines.service.JsonDataDictionaryService;
import org.springframework.util.StringUtils;

public class CityTransformer implements TransformerChain {

    private TransformerChain nextInChain;

    private JsonDataDictionaryService jsonDataDictionaryService;

    @Override
    public void transform(HeadlineTextModel text) {
        if (StringUtils.isEmpty(text.getHeadlineText())) {
            System.out.println("Invalid Request content for CityTransformer");
        } else {

            jsonDataDictionaryService.transformCityMentionString(text.getHeadlineText());

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
