package pl.aswit.rest.services.podcast;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.aswit.dto.PodcastDocumentDto;
import pl.aswit.dto.PodcastDto;
import pl.aswit.model.entity.Document;
import pl.aswit.model.entity.Podcast;
import pl.aswit.model.repository.DocumentRepository;
import pl.aswit.model.repository.PodcastRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PodcastService {

    @Autowired
    private PodcastRepository podcastRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public List<PodcastDto> getAll(){
        List<Podcast> podcastList = podcastRepository.findAll();
        return podcastList.stream().map(e-> podcastToPodcastDto(e)).collect(Collectors.toList());
    }

    public PodcastDto findById(Integer id) {
        Podcast podcast = podcastRepository.findById(id).get();
        return podcastToPodcastDto(podcast);
    }

    public ResponseEntity<Resource> download(Integer documentId) {
        Document document = documentRepository.findById(documentId).get();
        ByteArrayResource byteArrayResource = new ByteArrayResource(document.getDocument());
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
            .body(byteArrayResource);
    }

    private PodcastDto podcastToPodcastDto(Podcast podcast){
        return PodcastDto
            .builder()
            .description(podcast.getDescription())
            .id(podcast.getId())
            .episode(podcast.getEpisode())
            .season(podcast.getSeason())
            .title(podcast.getTitle())
            .imgLink(podcast.getImgLink())
            .url(podcast.getUrl())
            .documentIds(podcast.getDocumentList()
                .stream()
                .map(e-> PodcastDocumentDto
                    .builder()
                    .name(e.getName())
                    .documentId(e.getDocumentId())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }
}
