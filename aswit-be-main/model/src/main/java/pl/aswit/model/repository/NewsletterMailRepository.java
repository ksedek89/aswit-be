package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.NewsletterMail;

public interface NewsletterMailRepository extends JpaRepository<NewsletterMail, Long>{
    NewsletterMail findByEmail(String email);
}
