package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aswit.dto.PodcastDto;
import pl.aswit.rest.services.podcast.PodcastService;

import java.util.List;

@RestController
@RequestMapping( value =  "${app.public.path}/podcast")
@Slf4j
public class PodcastController {

    @Autowired
    private PodcastService podcastService;

    @GetMapping()
    public List<PodcastDto> getAll(){
        return podcastService.getAll();
    }

    @GetMapping("/{podcastId}")
    public PodcastDto getAll(@PathVariable Integer podcastId){
        return podcastService.findById(podcastId);
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<Resource> download(@PathVariable Integer documentId){
        return podcastService.download(documentId);
    }

}
