package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.Newsletter;
import pl.aswit.model.entity.NewsletterMail;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long>{
}
