package solux.wansuki.OurNeighbor_BE.domain.RecommendPost;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendPostRepository  extends JpaRepository <RecommendPost, Long>{
    List<RecommendPost> findByApartmentIdOrderByIdDesc(Long apartId, Pageable pageable);
}

