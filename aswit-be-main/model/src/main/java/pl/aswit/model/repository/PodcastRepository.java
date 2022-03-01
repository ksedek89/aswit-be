package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.Podcast;

public interface PodcastRepository extends JpaRepository<Podcast, Integer>{
}
