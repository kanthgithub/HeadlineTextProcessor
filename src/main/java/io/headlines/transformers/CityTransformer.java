package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import io.headlines.service.JsonDataDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CityTransformer implements TransformerChain {

    private static final Logger log = LoggerFactory.getLogger(CityTransformer.class);

    private TransformerChain nextInChain;

    private JsonDataDictionaryService jsonDataDictionaryService;

    @Autowired
    public CityTransformer(JsonDataDictionaryService jsonDataDictionaryService) {
        this.jsonDataDictionaryService = jsonDataDictionaryService;
    }

    @Override
    public void transform(HeadlineTextModel text) {

        if (StringUtils.isEmpty(text.getHeadlineText())) {
            log.error("Invalid Request content for CityTransformer");
        } else {

            String transformedText = jsonDataDictionaryService.transformCityMentionString(text.getHeadlineText());
            text.setHeadlineText(transformedText);

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
