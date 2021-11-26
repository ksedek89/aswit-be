package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aswit.rest.dto.podcast.PodcastDto;
import pl.aswit.rest.dto.podcast.PodcastsDto;

import java.util.Arrays;

@RestController
@RequestMapping( value =  "${app.public.path}/podcast")
@Slf4j
public class PodcastController {

    @GetMapping()
    public PodcastsDto getAll(){
        return PodcastsDto
            .builder()
            .podcastList(Arrays.asList(
                PodcastDto
                    .builder()
                    .name("nazwa")
                    .description("description")
                    .imgName("nazwa")
                    .path("path")
                    .build(),
                PodcastDto
                    .builder()
                    .name("imgName1")
                    .description("description1")
                    .imgName("imgName1")
                    .path("path")
                    .build()
            ))
            .build();
    }

}
