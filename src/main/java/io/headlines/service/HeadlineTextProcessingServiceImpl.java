package io.headlines.service;

import io.headlines.common.FileReaderUtil;
import io.headlines.model.HeadlineTextModel;
import io.headlines.transformers.TransformerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeadlineTextProcessingServiceImpl implements HeadlineTextProcessingService {

    private TransformerChain transformerChain;

    @Autowired
    public HeadlineTextProcessingServiceImpl(TransformerChain transformerChain) {
        this.transformerChain = transformerChain;
    }

    @Override
    public List<String> transformHeadlineTextData(Path file) {
        return parseHeadlineTextData(file).stream()
                .map(headlineText -> transformHeadlineText(headlineText)).collect(Collectors.toList());
    }

    @Override
    public String transformHeadlineText(HeadlineTextModel headlineText) {
        transformerChain.transform(headlineText);
        return headlineText.getHeadlineText();
    }

    @Override
    public List<HeadlineTextModel> parseHeadlineTextData(Path file) {
        return FileReaderUtil.readFileTextToLines(file).stream()
                .map(entry -> {
                                HeadlineTextModel headlineTextModel = new HeadlineTextModel();
                                String[] entryList = entry.split(",");
                                headlineTextModel.setTime(Integer.valueOf(entryList[0]));
                                headlineTextModel.setHeadlineText(entryList[0]);
                                return headlineTextModel;
                }).collect(Collectors.toList());
    }
}