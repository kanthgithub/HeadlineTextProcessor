package io.headlines.service;

import io.headlines.model.HeadlineTextModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadlineRendererServiceImpl implements HeadlineRendererService {

    Logger log = LoggerFactory.getLogger(HeadlineRendererServiceImpl.class);

    @Override
    public void renderHeadlines(List<HeadlineTextModel> headlineTextModels) {

        log.info("renderHeadlines for data : {}",headlineTextModels);

    }

}
