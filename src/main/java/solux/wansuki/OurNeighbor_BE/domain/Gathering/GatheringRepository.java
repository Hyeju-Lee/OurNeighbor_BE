package solux.wansuki.OurNeighbor_BE.domain.Gathering;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GatheringRepository extends JpaRepository <Gathering, Long>{
    List<Gathering> findByApartmentIdOrderByIdDesc(Long apartId, Pageable pageable);
}
