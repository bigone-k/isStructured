package group.bigone.api.repo;

import group.bigone.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String Email);

    Optional<User> findByUserIdAndProvider(String userid, String provider);
}
