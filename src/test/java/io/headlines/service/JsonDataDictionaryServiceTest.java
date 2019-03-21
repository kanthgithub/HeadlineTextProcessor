package io.headlines.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.headlines.service.JsonDataDictionaryService.searchForMentionsAndTransform;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonDataDictionaryServiceTest {

    private JsonDataDictionaryService jsonDataDictionaryService;

    @Before
    public void setup() throws Exception{
        jsonDataDictionaryService = new JsonDataDictionaryService();
        jsonDataDictionaryService.init();
    }

    @After
    public void teardown(){
        jsonDataDictionaryService = null;
    }

    @Test
    public void test_Assert_Initialization(){

        Set<String> cities = jsonDataDictionaryService.getCities();

        assertNotNull(cities);
        assertEquals(114288,cities.size());

        Set<String> countries = jsonDataDictionaryService.getCountries();

        assertNotNull(countries);
        assertEquals(243,countries.size());
    }

    @Test
    public void test_Assert_Country_Match(){

        //given
        String textToken = "Saudi arabia tells arabs war on iraq inevitable";
        Set<String> dictionaryTokens = new HashSet<String>();
        dictionaryTokens.add("Iraq");

        //when
        String transformedString_Actual =
                searchForMentionsAndTransform(textToken,dictionaryTokens);

        //then
        assertNotNull(transformedString_Actual);
        assertEquals("Saudi arabia tells arabs war on Iraq inevitable",transformedString_Actual);
    }

    @Test
    public void test_Assert_CaseInsensitive_Country_Match() {

        //given
        String textToken = "air nz strike australia and jApAN to affect iNDiaN and australian travellers";
        Set<String> dictionaryTokens = new HashSet<String>();
        dictionaryTokens.add("Australia");
        dictionaryTokens.add("India");
        dictionaryTokens.add("Japan");

        //when
        String transformedString_Actual =
                searchForMentionsAndTransform(textToken, dictionaryTokens);

        //then
        assertNotNull(transformedString_Actual);
        assertEquals("air nz strike Australia and Japan to affect IndiaN and Australian travellers", transformedString_Actual);
    }

    @Test
    public void assert_Transform_Country_Mention_String(){

        //given
        String textToken = "air nz strike australia and jApAN to affect iNDiaN and australian travellers";

        //when
        String transformedString_Actual = jsonDataDictionaryService.transformCountryMentionString(textToken);

        //then
        assertNotNull(transformedString_Actual);
        assertEquals("air nz strike Australia and Japan to affect IndiaN and Australian travellers",transformedString_Actual);
    }

    @Test
    public void assert_Transform_City_Mention_String(){

        //given
        String textToken = "air nz strike in australia and jApAN to affect sydney and tokyo travellers";

        //when
        String transformedString_Actual = jsonDataDictionaryService.transformCityMentionString(textToken);

        //then
        assertNotNull(transformedString_Actual);
        assertEquals("air nz strike in Australia and jApAN to affect Sydney and Tokyo travellers",transformedString_Actual);
    }


}
