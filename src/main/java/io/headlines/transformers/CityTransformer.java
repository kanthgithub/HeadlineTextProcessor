package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import io.headlines.service.JsonDataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CityTransformer implements TransformerChain {

    private TransformerChain nextInChain;

    private JsonDataDictionaryService jsonDataDictionaryService;

    @Autowired
    public CityTransformer(JsonDataDictionaryService jsonDataDictionaryService) {
        this.jsonDataDictionaryService = jsonDataDictionaryService;
    }

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
