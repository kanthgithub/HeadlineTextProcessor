package io.headlines.service;

import io.headlines.common.FileReaderUtil;
import io.headlines.model.HeadlineTextModel;
import io.headlines.transformers.TransformerChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeadlineTextProcessingServiceImpl implements HeadlineTextProcessingService {

    Logger log = LoggerFactory.getLogger(HeadlineTextProcessingServiceImpl.class);

    private TransformerChain transformerChain;

    @Autowired
    public HeadlineTextProcessingServiceImpl(TransformerChain transformerChain) {
        this.transformerChain = transformerChain;
    }

    @Override
    public List<HeadlineTextModel> transformHeadlineTextData(Path file) {

        log.debug("parsing file: {}",file.getFileName());

        return parseHeadlineTextData(file).parallelStream()
                .map(headlineText -> transformHeadlineText(headlineText)).collect(Collectors.toList());
    }

    @Override
    public HeadlineTextModel transformHeadlineText(HeadlineTextModel headlineText) {
        transformerChain.transform(headlineText);
        return headlineText;
    }

    @Override
    public List<HeadlineTextModel> parseHeadlineTextData(Path file) {
        log.debug("parseHeadlineTextData called for file: {}",file.getFileName());
        List<HeadlineTextModel>  headlineTextModels = FileReaderUtil.readFileTextToLines(file).parallelStream()
                .map(entry -> {
                                HeadlineTextModel headlineTextModel = new HeadlineTextModel();
                                String[] entryList = entry.split(",");
                                headlineTextModel.setTime(Integer.valueOf(entryList[0]));
                                headlineTextModel.setHeadlineText(entryList[1]);
                                return headlineTextModel;
                }).collect(Collectors.toList());

        log.debug("parsed to data: headlineTextModels {} - called for file: {}",headlineTextModels,file.getFileName());

        return headlineTextModels;
    }
}