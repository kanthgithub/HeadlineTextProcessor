package io.headlines.transformers;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.headlines.service.JsonDataDictionaryService.searchForMentionsAndTransform;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonDataDictionaryTest {

    @Test
    public void test_Assert_Country_Match(){

        //given
        String textToken = "air nz strike australia to affect australiatravellers";
        Set<String> dictionaryTokens = new HashSet<String>();
        dictionaryTokens.add("Australia");

        //when
        String transformedString_Actual =
                searchForMentionsAndTransform(textToken,dictionaryTokens);

        //then
        assertNotNull(transformedString_Actual);
        assertEquals("air nz strike Australia to affect Australiatravellers",transformedString_Actual);
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
}
