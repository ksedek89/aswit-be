package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.ActivationLink;

public interface ActivationLinkRepository extends JpaRepository<ActivationLink, Long>{
}
