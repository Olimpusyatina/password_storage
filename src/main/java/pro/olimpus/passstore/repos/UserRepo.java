package pro.olimpus.passstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.olimpus.passstore.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
