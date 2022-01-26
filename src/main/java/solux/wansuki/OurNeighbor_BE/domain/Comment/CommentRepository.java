package solux.wansuki.OurNeighbor_BE.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByGatheringId(Long gatheringId);
}
