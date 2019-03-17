package io.headlines.configuration;

import io.headlines.service.JsonDataDictionaryService;
import io.headlines.service.HeadlineRendererService;
import io.headlines.service.HeadlineRendererServiceImpl;
import io.headlines.service.HeadlineTextProcessingService;
import io.headlines.service.HeadlineTextProcessingServiceImpl;
import io.headlines.transformers.CityTransformer;
import io.headlines.transformers.CountryTransformer;
import io.headlines.transformers.FirstWordTransformer;
import io.headlines.transformers.TransformerChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadlineTextProcessorConfiguration {

    @Bean
    public TransformerChain transformerChain(){
        //configure Chain of Responsibility
        TransformerChain transformerChain_1 = new FirstWordTransformer();

        TransformerChain transformerChain_2 = new CityTransformer();
        transformerChain_1.setNext(transformerChain_2);

        TransformerChain transformerChain_3 = new CountryTransformer();
        transformerChain_2.setNext(transformerChain_3);

        return transformerChain_1;
    }

    @Bean
    public HeadlineTextProcessingService headlineTextProcessingService(TransformerChain transformerChain){
        return new HeadlineTextProcessingServiceImpl(transformerChain);
    }

    @Bean
    public HeadlineRendererService headlineRendererService(){
        return  new HeadlineRendererServiceImpl();
    }

    @Bean
    public JsonDataDictionaryService cityJsonDataDictionaryService(){
        return new JsonDataDictionaryService();
    }
}
