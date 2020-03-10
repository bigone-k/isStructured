package group.bigone.api.repo;

import group.bigone.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String Email);

    Optional<User> findByUidAndProvider(String uid, String provider);
}
