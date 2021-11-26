package pl.aswit.rest.dto.podcast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PodcastDto {
    private String name;
    private String imgName;
    private String description;
    private String path;
}
