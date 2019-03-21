package io.headlines.service;

import io.headlines.model.HeadlineTextModel;
import io.headlines.transformers.CityTransformer;
import io.headlines.transformers.CountryTransformer;
import io.headlines.transformers.FirstWordTransformer;
import io.headlines.transformers.TransformerChain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HeadlineTextProcessingServiceTest {

    Logger log = LoggerFactory.getLogger(HeadlineTextProcessingServiceTest.class);

    private HeadlineTextProcessingService headlineTextProcessingService;

    private TransformerChain transformerChain_FirstWord;
    private TransformerChain transformerChain_Country;
    private TransformerChain transformerChain_City;

    private JsonDataDictionaryService jsonDataDictionaryService;

    private static  String fileDataDirectory = "/tmp/headlines/";

    public static String test_Data_File_1 = "/testdata/abcnews-text.csv";

    public String testFile_1 = null;

    Path testFilePath_1 = null;

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

        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }

    @After
    public void cleanup(){
        transformerChain_FirstWord = null;
        transformerChain_City = null;
        transformerChain_Country = null;
        jsonDataDictionaryService = null;
        headlineTextProcessingService = null;
        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }

    @Test
    public void assert_Transformer_Chain_Wiring() throws Exception{

        //given
        Path source_path_File_1 = Paths.get(getClass().getResource(test_Data_File_1).toURI());

        //when
        List<HeadlineTextModel> headlineTextModelList =
                headlineTextProcessingService.transformHeadlineTextData(source_path_File_1);

        //then
        assertNotNull(headlineTextModelList);
        assertEquals(184,headlineTextModelList.size());
    }

    @Test
    public void assert_transform_HeadlineText() {

        //given
        String inputText = "air nz strike to affect australian travellers";
        Integer time = 20030219;

        HeadlineTextModel headlineTextModel = getAHeadlineTextModel(inputText,time);

        //when
        HeadlineTextModel headlineTextModel_Response_Actual =
                headlineTextProcessingService.transformHeadlineText(headlineTextModel);

        //then
        assertNotNull(headlineTextModel_Response_Actual);
        assertEquals("Air nz strike to affect Australian travellers",headlineTextModel_Response_Actual.getHeadlineText());
    }


    @Test
    public void assert_parse_HeadlineText_File_Data() throws Exception{

        //given
        Path source_path_File_1 = Paths.get(getClass().getResource(test_Data_File_1).toURI());

        //when
        List<HeadlineTextModel> headlineTextModelList =
                headlineTextProcessingService.parseHeadlineTextData(source_path_File_1);

        //then
        assertNotNull(headlineTextModelList);
        assertEquals(184,headlineTextModelList.size());
    }


    public HeadlineTextModel getAHeadlineTextModel(String text,Integer time){
        return HeadlineTextModel.builder().headlineText(text).time(time).build();
    }


}