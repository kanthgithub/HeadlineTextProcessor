package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import io.headlines.service.JsonDataDictionaryService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CountryTransformerTest {

    private JsonDataDictionaryService jsonDataDictionaryService;

    private CountryTransformer countryTransformer;

    private FirstWordTransformer firstWordTransformer;

    @Before
    public void setup() throws Exception{
        firstWordTransformer = new FirstWordTransformer();
        firstWordTransformer.setNext(null);
        jsonDataDictionaryService = new JsonDataDictionaryService();
        jsonDataDictionaryService.init();
        countryTransformer = new CountryTransformer(jsonDataDictionaryService);
    }


    @Test
    public void assert_Country_Mention_Transformation(){

        //given
        String inputText = "air nz strike to affect australian travellers";
        Integer time = 20030219;

        HeadlineTextModel headlineTextModel = getAHeadlineTextModel(inputText,time);

        //when
        countryTransformer.transform(headlineTextModel);

        //then
        assertNotNull(headlineTextModel);
        assertEquals("air nz strike to affect Australian travellers",headlineTextModel.getHeadlineText());
    }

    @Test
    public void assert_NextInChain_Invocation(){

        //given
        String inputText = "air nz strike to affect australian travellers";
        Integer time = 20030219;

        HeadlineTextModel headlineTextModel = getAHeadlineTextModel(inputText,time);

        countryTransformer.setNext(firstWordTransformer);

        //when
        countryTransformer.transform(headlineTextModel);

        //then
        assertNotNull(headlineTextModel);
        assertEquals("Air nz strike to affect Australian travellers",headlineTextModel.getHeadlineText());
    }


    public HeadlineTextModel getAHeadlineTextModel(String text,Integer time){
        return HeadlineTextModel.builder().headlineText(text).time(time).build();
    }


}