package io.headlines.transformers;

import io.headlines.model.HeadlineTextModel;
import io.headlines.service.JsonDataDictionaryService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityTransformerTest {

    private JsonDataDictionaryService jsonDataDictionaryService;

    private CountryTransformer countryTransformer;

    private CityTransformer cityTransformer;

    private FirstWordTransformer firstWordTransformer;

    @Before
    public void setup() throws Exception{
        firstWordTransformer = new FirstWordTransformer();
        firstWordTransformer.setNext(null);
        jsonDataDictionaryService = new JsonDataDictionaryService();
        jsonDataDictionaryService.init();
        countryTransformer = new CountryTransformer(jsonDataDictionaryService);
        cityTransformer = new CityTransformer(jsonDataDictionaryService);
    }


    @Test
    public void assert_City_Mention_Transformation(){

        //given
        String inputText = "air nz strike to affect sydney travellers";
        Integer time = 20030219;

        HeadlineTextModel headlineTextModel = getAHeadlineTextModel(inputText,time);

        //when
        cityTransformer.transform(headlineTextModel);

        //then
        assertNotNull(headlineTextModel);
        assertEquals("air nz strike to affect Sydney travellers",headlineTextModel.getHeadlineText());
    }

    @Test
    public void assert_NextInChain_Invocation(){

        //given
        String inputText = "air nz strike to affect sydney travellers";
        Integer time = 20030219;

        HeadlineTextModel headlineTextModel = getAHeadlineTextModel(inputText,time);

        cityTransformer.setNext(firstWordTransformer);

        //when
        cityTransformer.transform(headlineTextModel);

        //then
        assertNotNull(headlineTextModel);
        assertEquals("Air nz strike to affect Sydney travellers",headlineTextModel.getHeadlineText());
    }


    public HeadlineTextModel getAHeadlineTextModel(String text,Integer time){
        return HeadlineTextModel.builder().headlineText(text).time(time).build();
    }


}