package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.Document;
import pl.aswit.model.entity.Podcast;

public interface DocumentRepository extends JpaRepository<Document, Integer>{
}
