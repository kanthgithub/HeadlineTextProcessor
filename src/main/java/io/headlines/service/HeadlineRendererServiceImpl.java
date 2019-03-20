package io.headlines.service;

import io.headlines.common.FileReaderUtil;
import io.headlines.model.HeadlineTextModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HeadlineRendererServiceImpl implements HeadlineRendererService {

    Logger log = LoggerFactory.getLogger(HeadlineRendererServiceImpl.class);

    public static final String headerFieldsAsCSV = "publish_date,headline_text\n";

    @Override
    public Boolean renderHeadlines(List<HeadlineTextModel> headlineTextModels) {

        log.debug("renderHeadlines for data : {}",headlineTextModels.size());

        Boolean isSuccessful = Boolean.TRUE;

        String recordDataAsCsv = headlineTextModels.stream()
                .map(this::toCsvRow)
                .collect(Collectors.joining(System.getProperty("line.separator")));

        String csvFileData = headerFieldsAsCSV+recordDataAsCsv;

        log.debug("writing csv-data : {}",csvFileData);

        Path renderedFilePath = null;

        try {
            renderedFilePath = FileReaderUtil.createNewFile("/tmp/transformed/abcnews-date-text"+"_"+System.currentTimeMillis()+".csv");
            Files.write(renderedFilePath, csvFileData.getBytes());
        } catch (IOException e) {
            log.error("Error caught while rendering transformed data in: {}",renderedFilePath);
            isSuccessful = Boolean.FALSE;
        }

        log.info("HeadlineTextProcessing Completed Successfully");

        return isSuccessful;
    }


    public String toCsvRow(HeadlineTextModel headlineTextData) {
        return Stream.of(headlineTextData.getTime().toString(), headlineTextData.getHeadlineText())
                .collect(Collectors.joining(","));
    }
}
