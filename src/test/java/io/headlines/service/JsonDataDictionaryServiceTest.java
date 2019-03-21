package io.headlines.service;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static io.headlines.service.JsonDataDictionaryService.searchForMentionsAndTransform;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonDataDictionaryServiceTest {

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
}
