package io.headlines.service;

import io.headlines.transformers.CityTransformer;
import io.headlines.transformers.CountryTransformer;
import io.headlines.transformers.FirstWordTransformer;
import io.headlines.transformers.TransformerChain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeadlineTextProcessingServiceTest {

    private HeadlineTextProcessingService headlineTextProcessingService;

    private TransformerChain transformerChain_FirstWord;
    private TransformerChain transformerChain_Country;
    private TransformerChain transformerChain_City;

    private JsonDataDictionaryService jsonDataDictionaryService;

    @Before
    public void setup() throws Exception{
        jsonDataDictionaryService = new JsonDataDictionaryService();
        jsonDataDictionaryService.init();
        transformerChain_FirstWord = new FirstWordTransformer();
        transformerChain_Country = new CountryTransformer(jsonDataDictionaryService);
        transformerChain_City = new CityTransformer(jsonDataDictionaryService);
        transformerChain_FirstWord.setNext(transformerChain_Country);
        transformerChain_Country.setNext(transformerChain_City);
        headlineTextProcessingService = new HeadlineTextProcessingServiceImpl(transformerChain_FirstWord);
    }

    @After
    public void cleanup(){
        transformerChain_FirstWord = null;
        transformerChain_City = null;
        transformerChain_Country = null;
        jsonDataDictionaryService = null;
        headlineTextProcessingService = null;
    }


    @Test
    public void assert_Transformer_Chain_Wiring(){

        //given


        //when


        //then


    }

}
