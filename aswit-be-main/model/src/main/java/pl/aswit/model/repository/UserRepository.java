package pl.aswit.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.aswit.model.entity.User;

public interface  UserRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
    User findByEmail(String email);
}
