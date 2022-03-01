package pl.aswit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PodcastDto {
    private Integer id;
    private String title;
    private String url;
    private String season;
    private String episode;
    private String description;
    private String imgLink;
    private List<PodcastDocumentDto> documentIds;
}
