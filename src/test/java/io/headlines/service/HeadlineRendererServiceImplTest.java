package io.headlines.service;

import io.headlines.model.HeadlineTextModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HeadlineRendererServiceImplTest {


    private static  String fileDataDirectory = "/tmp/headlines/output/";

    public static String test_Data_File_1 = "/testdata/abcnews-text.csv";

    public String testFile_1 = null;

    Path testFilePath_1 = null;

    private HeadlineRendererService headlineRendererService;

    @Before
    public void setup(){
        headlineRendererService = new HeadlineRendererServiceImpl();

        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }

    @After
    public void cleanup(){
        headlineRendererService = null;
        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }


    @Test
    public void assert_Render_Headlines() throws Exception{

        //given
        List<HeadlineTextModel> headlineTextModelList = Arrays.asList(getAHeadlineTextModel("air nz strike to affect australian travellers",20030219),
                                                                      getAHeadlineTextModel("big plan to boost paroo water supplies",20030429));

        //when
        Boolean isRenderSuccessful =
                headlineRendererService.renderHeadlines(headlineTextModelList);

        //then
        assertNotNull(isRenderSuccessful);
        assertTrue(isRenderSuccessful);
    }

    public HeadlineTextModel getAHeadlineTextModel(String text,Integer time){
        return HeadlineTextModel.builder().headlineText(text).time(time).build();
    }



}
