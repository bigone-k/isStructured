package group.bigone.api.repo;

import group.bigone.api.entity.Board;
import group.bigone.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepo extends JpaRepository<Post, Long> {
    List<Post> findByBoard(Board board);
}
