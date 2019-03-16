package io.headlines.service;

import io.headlines.model.HeadlineTextModel;

import java.util.List;

public interface HeadlineRendererService {

    public void renderHeadlines(List<HeadlineTextModel> headlineTextModels);
}
