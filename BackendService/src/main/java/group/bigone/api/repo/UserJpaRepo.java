package group.bigone.api.repo;

import group.bigone.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String Email);

    Optional<User> findByUseridAndProvider(String userid, String provider);
}
