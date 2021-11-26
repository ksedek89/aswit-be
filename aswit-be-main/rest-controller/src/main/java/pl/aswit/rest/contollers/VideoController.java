package pl.aswit.rest.contollers;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.Arrays;

import static java.lang.StrictMath.min;

@RestController
@RequestMapping( value =  "/api/video")
@Slf4j
public class VideoController {

    @GetMapping("/{name}")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable String name, @RequestHeader HttpHeaders headers) throws Exception{
        UrlResource video = new UrlResource("file:/u01/aswit/videos/"+name);
        val region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(MediaTypeFactory
                .getMediaType(video)
                .orElse(MediaType.APPLICATION_OCTET_STREAM))
            .body(region);
    }

    private  ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws Exception{
        val contentLength = video.contentLength();
        val range = headers.getRange().get(0);

         if (range != null) {
            val start = range.getRangeStart(contentLength);
            val end = range.getRangeEnd(contentLength);
            val rangeLength = min(1 * 1024 * 1024, end - start + 1);
            return new ResourceRegion(video, start, rangeLength);
        } else {
            val rangeLength = min(1 * 1024 * 1024, contentLength);
            return new ResourceRegion(video, 0, rangeLength);
        }
    }
}
