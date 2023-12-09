package org.maxkizi.quotes.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class QuoteDto {
    private Long id;
    private long score;
    private String content;
    private String postedBy;
}
