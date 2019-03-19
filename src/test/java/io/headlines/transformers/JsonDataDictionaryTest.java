package io.headlines.transformers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static io.headlines.service.JsonDataDictionaryService.searchForMentionsAndTransform;
import static org.junit.Assert.assertNotNull;

public class JsonDataDictionaryTest {

    Logger log = LoggerFactory.getLogger(JsonDataDictionaryTest.class);

    @Test
    public void test_Assert_Country_Match(){

        //given
        String textToken = "air nz strike australia to affect australiatravellers";
        Set<String> dictionaryTokens = new HashSet<String>();
        dictionaryTokens.add("Australia");

        //when
        String transformedString_Actual =
                searchForMentionsAndTransform(textToken,dictionaryTokens);

        log.info("transformedString_Actual is: {}",transformedString_Actual);

        //then
        assertNotNull(transformedString_Actual);
    }

    @Test
    public void test_Assert_CaseInsensitive_Country_Match(){

        //given
        String textToken = "air nz strike australia and jApAN to affect iNDiaN and australian travellers";
        Set<String> dictionaryTokens = new HashSet<String>();
        dictionaryTokens.add("Australia");
        dictionaryTokens.add("India");
        dictionaryTokens.add("Japan");

        //when
        String transformedString_Actual =
                searchForMentionsAndTransform(textToken,dictionaryTokens);

        log.info("transformedString_Actual is: {}",transformedString_Actual);

        //then
        assertNotNull(transformedString_Actual);
    }

    //

}
