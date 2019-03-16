package io.headlines.service;

import io.headlines.model.HeadlineTextModel;

import java.nio.file.Path;
import java.util.List;

public interface HeadlineTextProcessingService {

  public List<HeadlineTextModel> parseHeadlineTextData(Path file);

  public List<HeadlineTextModel> transformHeadlineTextData(Path file);

  public HeadlineTextModel transformHeadlineText(HeadlineTextModel headlineText);
}
