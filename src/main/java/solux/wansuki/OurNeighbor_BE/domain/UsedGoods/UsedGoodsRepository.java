package solux.wansuki.OurNeighbor_BE.domain.UsedGoods;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsedGoodsRepository extends JpaRepository<UsedGoods, Long> {
    List<UsedGoods> findByApartmentIdOrderByIdDesc(Long apartId, Pageable pageable);
}
