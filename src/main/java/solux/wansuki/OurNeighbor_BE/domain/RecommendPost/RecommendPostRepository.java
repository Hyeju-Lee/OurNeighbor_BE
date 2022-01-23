package solux.wansuki.OurNeighbor_BE.domain.RecommendPost;

import org.springframework.data.jpa.repository.JpaRepository;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;

import java.util.Optional;

public interface RecommendPostRepository  extends JpaRepository <RecommendPost, Long>{
}

