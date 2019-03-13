package io.headlines.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class HeadlineTextModel {

    private Integer time;
    private String headlineText;

}
