package io.headlines.service;

import io.headlines.model.HeadlineTextModel;

import java.nio.file.Path;
import java.util.List;

public interface HeadlineTextProcessingService {

  public List<HeadlineTextModel> parseHeadlineTextData(Path file);

  public List<String> transformHeadlineTextData(Path file);

  public String transformHeadlineText(HeadlineTextModel headlineText);
}
