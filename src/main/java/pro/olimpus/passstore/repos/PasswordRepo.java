package pro.olimpus.passstore.repos;

import org.springframework.data.repository.CrudRepository;
import pro.olimpus.passstore.domain.Password;

import java.util.List;

public interface PasswordRepo extends CrudRepository<Password, Long> {
    Password getPasswordByResourceAndLogin(String resource, String login);
    List<Password> findByResource(String resource);
}
